package com.mjbvilhena.lagoon.domain.share

import java.net.URI

private val TOKEN_NAMES =
    setOf(
        "token",
        "access_token",
        "id_token",
        "auth",
        "key",
        "api_key",
        "sig",
        "signature",
        "code",
        "otp",
        "session",
        "sid",
        "secret",
        "password",
        "x-amz-signature",
        "x-amz-credential",
        "x-goog-signature",
    )

private val TOKEN_PATHS =
    setOf(
        "reset",
        "verify",
        "magic",
        "confirm",
        "unsubscribe",
        "login",
        "invite",
        "activate",
        "signin",
        "callback",
        "oauth",
    )

/** The US-79 rule, used by the shared title order. This does not fetch a preview. */
fun isTokenLooking(url: String): Boolean {
    val parsed =
        try {
            URI(url)
        } catch (_: Exception) {
            return false
        }
    val userInfo = parsed.userInfo
    if (userInfo != null && userInfo.contains(':') && userInfo.substringBefore(':').isNotEmpty()) {
        return true
    }
    if (parameterLooksToken(parsed.rawQuery) || parameterLooksToken(parsed.rawFragment)) {
        return true
    }
    val segments =
        parsed.path
            .orEmpty()
            .split('/')
            .filter { it.isNotEmpty() }
    return segments.any { segment ->
        val decoded = segment.lowercase()
        decoded in TOKEN_PATHS || mixedLongToken(decoded)
    }
}

private fun parameterLooksToken(raw: String?): Boolean {
    if (raw.isNullOrEmpty()) {
        return false
    }
    return raw.split('&').any { pair ->
        val name = pair.substringBefore('=').lowercase()
        val value = pair.substringAfter('=', "")
        name in TOKEN_NAMES || value.contains("eyJ") || mixedLongToken(value)
    }
}

private fun mixedLongToken(value: String): Boolean {
    if (value.length < 24 || value.contains('-')) {
        return false
    }
    val hasLetter = value.any { it.isLetter() }
    val hasDigit = value.any { it.isDigit() }
    return hasLetter && hasDigit
}
