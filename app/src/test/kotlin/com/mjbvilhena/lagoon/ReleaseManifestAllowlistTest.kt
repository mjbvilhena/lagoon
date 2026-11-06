package com.mjbvilhena.lagoon

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.util.zip.ZipFile

class ReleaseManifestAllowlistTest {
    @Test
    fun releaseManifestMatchesTheAllowlist() {
        val apk = apkIn(requiredProperty("releaseApkDir"))
        val dump = dumpManifest(apk)
        val permissions = permissionsIn(dump)

        assertEquals(ALLOWED, permissions.keys)
        assertEquals("28", permissions.getValue("android.permission.USE_FINGERPRINT"))
        ABSENT.forEach { name ->
            assertFalse("permission must be absent: $name\n$dump", permissions.containsKey(name))
        }
        assertFalse(
            "an ACCESS_ADSERVICES_ permission is present\n$dump",
            permissions.keys.any { it.contains("ACCESS_ADSERVICES_") },
        )

        val exported = exportedComponents(dump)
        val allowedNames =
            setOf(
                "com.mjbvilhena.lagoon.ui.LauncherActivity",
                "com.mjbvilhena.lagoon.ui.ShareTargetActivity",
            )
        exported.forEach { component ->
            val permitted =
                component.name in allowedNames ||
                    component.permission in PROTECTED_PERMISSIONS ||
                    component.permission.endsWith("DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION")
            assertTrue(
                "unexpected exported component ${component.name} permission=${component.permission}\n$dump",
                permitted,
            )
        }
        assertTrue(exported.any { it.name.endsWith("LauncherActivity") })
        assertTrue(exported.any { it.name.endsWith("ShareTargetActivity") })
        assertTrue("MAIN action missing\n$dump", dump.contains("android.intent.action.MAIN"))
        assertTrue("LAUNCHER category missing\n$dump", dump.contains("android.intent.category.LAUNCHER"))
        assertTrue("SEND action missing\n$dump", dump.contains("android.intent.action.SEND"))
        assertTrue(dump.contains("text/plain"))
        assertTrue(dump.contains("image/*"))
        assertTrue(dump.contains("application/pdf"))

        assertTrue("allowBackup is not false\n$dump", dump.contains("android:allowBackup"))
        assertTrue(
            "allowBackup value is not false\n$dump",
            Regex("""android:allowBackup\(0x[0-9a-fA-F]+\)=false""").containsMatchIn(dump),
        )
        assertTrue("dataExtractionRules missing\n$dump", dump.contains("android:dataExtractionRules"))
        assertTrue("fullBackupContent missing\n$dump", dump.contains("android:fullBackupContent"))
        assertSdk(dump, "android:minSdkVersion", 26)
        assertSdk(dump, "android:targetSdkVersion", 37)
    }

    @Test
    fun dataExtractionRulesExcludeAppStorage() {
        val rules =
            File(requiredProperty("repoRoot"), "app/src/main/res/xml/data_extraction_rules.xml")
                .readText()
        listOf("cloud-backup", "device-transfer").forEach { section ->
            assertTrue(rules.contains("<$section>"))
        }
        listOf("root", "file", "database", "sharedpref", "external").forEach { domain ->
            val occurrences = Regex("""domain="$domain"""").findAll(rules).count()
            assertEquals(domain, 2, occurrences)
        }
        val backupRules =
            File(requiredProperty("repoRoot"), "app/src/main/res/xml/backup_rules.xml")
                .readText()
        listOf("root", "file", "database", "sharedpref", "external").forEach { domain ->
            assertTrue(backupRules, backupRules.contains("""domain="$domain""""))
        }
    }

    @Test
    fun releaseStripsLogAndDebugKeepsIt() {
        val sentinel = "LOG_PROBE_SENTINEL".toByteArray()
        assertFalse(zipContains(apkIn(requiredProperty("releaseApkDir")), sentinel))
        assertTrue(zipContains(apkIn(requiredProperty("debugApkDir")), sentinel))
    }

    @Test
    fun launchSourcesDoNotRequestPostNotifications() {
        val root = File(requiredProperty("repoRoot"))
        val sources =
            listOf(File(root, "ui/src/main"), File(root, "app/src/main"))
                .flatMap { dir ->
                    dir.walkTopDown().filter { it.extension == "kt" }.toList()
                }
        sources.forEach { file ->
            val text = file.readText()
            assertFalse(file.path, text.contains("requestPermissions"))
            assertFalse(file.path, text.contains("RequestPermission"))
            assertFalse(file.path, text.contains("POST_NOTIFICATIONS"))
        }
    }

    @Test
    fun debugApplicationIdEndsWithDebug() {
        val name = packageName(apkIn(requiredProperty("debugApkDir")))
        assertTrue(name, name.endsWith(".debug"))
        assertEquals("com.mjbvilhena.lagoon.debug", name)
    }

    @Test
    fun moduleGraphMatchesTheApprovedLayers() {
        val root = File(requiredProperty("repoRoot"))
        val settings = File(root, "settings.gradle.kts").readText()
        listOf(":domain", ":data", ":ui", ":app").forEach { module ->
            assertTrue(settings, settings.contains("\"$module\""))
        }
        val domain = File(root, "domain/build.gradle.kts").readText()
        assertFalse(domain, domain.contains("com.android"))
        assertFalse(domain, domain.contains("project(\":ui\")"))
        assertFalse(domain, domain.contains("project(\":data\")"))
        assertFalse(domain, domain.contains("project(\":app\")"))

        val data = File(root, "data/build.gradle.kts").readText()
        assertTrue(data.contains("project(\":domain\")"))
        assertFalse(data.contains("project(\":ui\")"))
        assertFalse(data.contains("com.mjbvilhena.lagoon.ui"))

        val ui = File(root, "ui/build.gradle.kts").readText()
        assertTrue(ui.contains("project(\":domain\")"))
        assertFalse(ui.contains("project(\":data\")"))
        assertFalse(ui.contains("com.mjbvilhena.lagoon.data"))

        val app = File(root, "app/build.gradle.kts").readText()
        assertTrue(app.contains("project(\":data\")"))
        assertTrue(app.contains("project(\":ui\")"))
    }

    private fun assertSdk(
        dump: String,
        attribute: String,
        expected: Int,
    ) {
        val decimal =
            Regex("""$attribute\(0x[0-9a-fA-F]+\)=(\d+)""")
                .find(dump)
        val hex =
            Regex("""$attribute\(0x[0-9a-fA-F]+\)=\(type 0x10\)0x([0-9a-fA-F]+)""")
                .find(dump)
        val actual =
            when {
                decimal != null -> decimal.groupValues[1].toInt()
                hex != null -> hex.groupValues[1].toInt(16)
                else -> error("$attribute missing\n$dump")
            }
        assertEquals(expected, actual)
    }

    private fun permissionsIn(dump: String): Map<String, String?> {
        val result = linkedMapOf<String, String?>()
        val blocks = dump.split(Regex("""\n\s*E: uses-permission"""))
        blocks.drop(1).forEach { block ->
            val name =
                Regex("""android:name\(0x[0-9a-fA-F]+\)="(android\.permission\.[^"]+)"""")
                    .find(block)
                    ?.groupValues
                    ?.get(1)
                    ?: return@forEach
            val decimalMax =
                Regex("""android:maxSdkVersion\(0x[0-9a-fA-F]+\)=(\d+)""")
                    .find(block)
            val hexMax =
                Regex("""android:maxSdkVersion\(0x[0-9a-fA-F]+\)=\(type 0x10\)0x([0-9a-fA-F]+)""")
                    .find(block)
            val max =
                when {
                    decimalMax != null -> decimalMax.groupValues[1]
                    hexMax != null -> hexMax.groupValues[1].toInt(16).toString()
                    else -> null
                }
            result[name] = max
        }
        return result
    }

    private fun exportedComponents(dump: String): List<Component> {
        val result = mutableListOf<Component>()
        val pattern =
            Regex(
                """E: (activity|activity-alias|service|receiver|provider) \(line=\d+\)(.*?)(?=\n\s*E: (activity|activity-alias|service|receiver|provider) \(line=|\n\s*E: uses-|\z)""",
                RegexOption.DOT_MATCHES_ALL,
            )
        pattern.findAll(dump).forEach { match ->
            val body = match.groupValues[2]
            val exported =
                body.contains("(type 0x12)0xffffffff") ||
                    Regex("""android:exported\(0x[0-9a-fA-F]+\)=true""").containsMatchIn(body)
            if (!exported) {
                return@forEach
            }
            val name =
                Regex("""android:name\(0x[0-9a-fA-F]+\)="([^"]+)"""")
                    .find(body)
                    ?.groupValues
                    ?.get(1)
                    ?: return@forEach
            val permission =
                Regex("""android:permission\(0x[0-9a-fA-F]+\)="([^"]+)"""")
                    .find(body)
                    ?.groupValues
                    ?.get(1)
                    ?: ""
            result.add(Component(name, permission))
        }
        return result
    }

    private fun dumpManifest(apk: File): String {
        val process =
            ProcessBuilder(
                aapt2().absolutePath,
                "dump",
                "xmltree",
                apk.absolutePath,
                "--file",
                "AndroidManifest.xml",
            ).redirectErrorStream(true).start()
        val text = process.inputStream.bufferedReader().readText()
        val code = process.waitFor()
        check(code == 0) { "aapt2 dump failed ($code)\n$text" }
        return text
    }

    private fun packageName(apk: File): String {
        val process =
            ProcessBuilder(aapt2().absolutePath, "dump", "packagename", apk.absolutePath)
                .redirectErrorStream(true)
                .start()
        val text =
            process.inputStream
                .bufferedReader()
                .readText()
                .trim()
        val code = process.waitFor()
        check(code == 0) { "aapt2 packagename failed ($code)\n$text" }
        return text
    }

    private fun aapt2(): File {
        val sdk = File(requiredProperty("androidSdk"))
        val tools = File(sdk, "build-tools")
        val exe =
            tools
                .listFiles()
                ?.filter { it.isDirectory }
                ?.sortedBy { it.name }
                ?.lastOrNull()
                ?.resolve("aapt2")
        check(exe != null && exe.canExecute()) { "aapt2 missing under $tools" }
        return exe
    }

    private fun requiredProperty(name: String): String = System.getProperty(name) ?: error("missing $name")

    private fun apkIn(dirPath: String): File {
        val dir = File(dirPath)
        val apk =
            dir
                .listFiles()
                ?.filter { it.isFile && it.extension == "apk" }
                ?.sortedBy { it.name }
                ?.firstOrNull()
        check(apk != null) { "no apk in $dirPath" }
        return apk
    }

    private fun zipContains(
        apk: File,
        needle: ByteArray,
    ): Boolean {
        ZipFile(apk).use { zip ->
            val entries = zip.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (entry.isDirectory) {
                    continue
                }
                val bytes = zip.getInputStream(entry).readBytes()
                if (indexOf(bytes, needle) >= 0) {
                    return true
                }
            }
        }
        return false
    }

    private fun indexOf(
        haystack: ByteArray,
        needle: ByteArray,
    ): Int {
        if (needle.isEmpty() || haystack.size < needle.size) {
            return -1
        }
        for (index in 0..haystack.size - needle.size) {
            var matches = true
            for (offset in needle.indices) {
                if (haystack[index + offset] != needle[offset]) {
                    matches = false
                    break
                }
            }
            if (matches) {
                return index
            }
        }
        return -1
    }

    private data class Component(
        val name: String,
        val permission: String,
    )

    companion object {
        private val ALLOWED =
            setOf(
                "android.permission.INTERNET",
                "android.permission.ACCESS_NETWORK_STATE",
                "android.permission.POST_NOTIFICATIONS",
                "android.permission.RECEIVE_BOOT_COMPLETED",
                "android.permission.USE_BIOMETRIC",
                "android.permission.WAKE_LOCK",
                "android.permission.USE_FINGERPRINT",
            )

        private val ABSENT =
            listOf(
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_MEDIA_IMAGES",
                "android.permission.READ_MEDIA_VIDEO",
                "android.permission.READ_MEDIA_AUDIO",
                "android.permission.MANAGE_EXTERNAL_STORAGE",
                "android.permission.CAMERA",
                "android.permission.SCHEDULE_EXACT_ALARM",
                "android.permission.USE_EXACT_ALARM",
                "android.permission.QUERY_ALL_PACKAGES",
                "android.permission.GET_ACCOUNTS",
                "android.permission.AD_ID",
                "android.permission.ACCESS_ADSERVICES_AD_ID",
                "android.permission.ACCESS_ADSERVICES_ATTRIBUTION",
                "android.permission.ACCESS_ADSERVICES_TOPICS",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.ACCESS_BACKGROUND_LOCATION",
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_CONTACTS",
                "android.permission.WRITE_SETTINGS",
                "android.permission.FOREGROUND_SERVICE",
                "android.permission.FOREGROUND_SERVICE_DATA_SYNC",
            )

        private val PROTECTED_PERMISSIONS =
            setOf(
                "android.permission.BIND_JOB_SERVICE",
                "android.permission.DUMP",
            )
    }
}
