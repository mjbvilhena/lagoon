package com.mjbvilhena.lagoon.domain.item

import java.time.LocalDate
import java.time.ZoneId

/** The approved default reminder time is 09:00 in the phone's current local zone. */
const val DEFAULT_SCHEDULE_HOUR = 9

const val DEFAULT_SCHEDULE_MINUTE = 0

/**
 * A row from the schema before these identity fields existed.
 * Dates are `YYYY-MM-DD`. A null time means the 09:00 default.
 */
data class LegacyItemRow(
    val id: String,
    val type: String,
    val title: String,
    val body: String,
    val sourceUrl: String?,
    val createdAt: Long?,
    val updatedAt: Long?,
    val lastSavedAt: Long?,
    val inTrash: Boolean,
    val trashedAt: Long?,
    val trashChangedAt: Long?,
    val reminderDate: String?,
    val expiryDate: String?,
    val timeHour: Int?,
    val timeMinute: Int?,
)

data class MigratedIdentity(
    val createdAt: Long,
    val updatedAt: Long,
    val userEditedAt: Long?,
    val lastSavedAt: Long,
    val inTrash: Boolean,
    val trashedAt: Long?,
    val trashChangedAt: Long?,
    val firedOccurrences: Set<String>,
    val reminder: LocalSchedule?,
    val expiry: LocalSchedule?,
)

/**
 * Fills the US-56 fields. [migrationAt] is the migration instant, epoch milliseconds.
 * [zone] is the phone's current local zone, used only to decide whether an occurrence is past.
 */
fun migrateLegacyItem(
    row: LegacyItemRow,
    migrationAt: Long,
    zone: ZoneId,
): MigratedIdentity {
    val createdAt = row.createdAt ?: migrationAt
    val lastSavedAt = row.lastSavedAt ?: createdAt
    val hour = row.timeHour ?: DEFAULT_SCHEDULE_HOUR
    val minute = if (row.timeHour == null) DEFAULT_SCHEDULE_MINUTE else row.timeMinute ?: 0
    val inTrash = row.inTrash
    return MigratedIdentity(
        createdAt = createdAt,
        updatedAt = monotonicUpdatedAt(row.updatedAt, migrationAt),
        userEditedAt = null,
        lastSavedAt = lastSavedAt,
        inTrash = inTrash,
        trashedAt = if (inTrash) row.trashedAt else null,
        trashChangedAt = if (inTrash) row.trashChangedAt else null,
        firedOccurrences = firedAtMigration(row, migrationAt, zone, hour, minute),
        reminder = row.reminderDate?.let { LocalSchedule(it, hour, minute) },
        expiry = row.expiryDate?.let { LocalSchedule(it, hour, minute) },
    )
}

fun reminderOccurrenceKey(
    date: String,
    hour: Int,
    minute: Int,
): String = "reminder:$date" + "T" + clock(hour, minute)

fun expiryNoticeKeys(
    expiryDate: String,
    hour: Int,
    minute: Int,
): List<String> {
    val day = LocalDate.parse(expiryDate)
    val sevenDaysBefore = day.minusDays(7).toString()
    val clock = clock(hour, minute)
    return listOf(
        "expiry-notice:7-day:$sevenDaysBefore" + "T$clock",
        "expiry-notice:day-of:$expiryDate" + "T$clock",
    )
}

internal fun firedAtMigration(
    row: LegacyItemRow,
    migrationAt: Long,
    zone: ZoneId,
    hour: Int,
    minute: Int,
): Set<String> {
    val fired = linkedSetOf<String>()
    val reminderDate = row.reminderDate
    if (reminderDate != null && occurrenceIsPast(reminderDate, hour, minute, migrationAt, zone)) {
        fired.add(reminderOccurrenceKey(reminderDate, hour, minute))
    }
    val expiryDate = row.expiryDate
    if (expiryDate != null) {
        val day = LocalDate.parse(expiryDate)
        val keys = expiryNoticeKeys(expiryDate, hour, minute)
        val notices =
            listOf(
                day.minusDays(7).toString() to keys[0],
                expiryDate to keys[1],
            )
        notices.forEach { (date, key) ->
            if (occurrenceIsPast(date, hour, minute, migrationAt, zone)) {
                fired.add(key)
            }
        }
    }
    return fired
}

internal fun occurrenceIsPast(
    date: String,
    hour: Int,
    minute: Int,
    migrationAt: Long,
    zone: ZoneId,
): Boolean {
    val occurrence =
        LocalDate
            .parse(date)
            .atTime(hour, minute)
            .atZone(zone)
            .toInstant()
            .toEpochMilli()
    return occurrence < migrationAt
}

private fun clock(
    hour: Int,
    minute: Int,
): String = "%02d:%02d".format(hour, minute)
