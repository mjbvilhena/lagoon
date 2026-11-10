buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    configurations.classpath {
        // The scan reads this classpath. These are the first fixed releases.
        // There is no approved vulnerability exception.
        resolutionStrategy.force(
            "org.bouncycastle:bcpkix-jdk18on:1.86",
            "org.bouncycastle:bcprov-jdk18on:1.86",
            "org.bouncycastle:bcutil-jdk18on:1.86",
            "org.bitbucket.b_c:jose4j:0.9.6",
            "org.jdom:jdom2:2.0.6.1",
        )
    }
}

plugins {
    id("com.android.application") version "9.1.1" apply false
    id("com.android.library") version "9.1.1" apply false
    id("org.jetbrains.kotlin.jvm") version "2.2.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
