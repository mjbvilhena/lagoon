plugins {
    id("com.android.application")
}

android {
    namespace = "com.mjbvilhena.lagoon"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.mjbvilhena.lagoon"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":data"))
    testImplementation("junit:junit:4.13.2")
}

tasks.withType<Test>().configureEach {
    val sdk =
        System.getenv("ANDROID_HOME")
            ?: System.getenv("ANDROID_SDK_ROOT")
            ?: sdkDirFromLocalProperties()
    dependsOn("assembleDebug", "assembleRelease")
    systemProperty("androidSdk", sdk)
    systemProperty("repoRoot", rootProject.projectDir.absolutePath)
    systemProperty("releaseApkDir", apkDir("release"))
    systemProperty("debugApkDir", apkDir("debug"))
}

fun apkDir(variant: String): String {
    val directory =
        layout.buildDirectory
            .dir("outputs/apk/$variant")
            .get()
            .asFile
    return directory.absolutePath
}

fun sdkDirFromLocalProperties(): String {
    val file = rootProject.file("local.properties")
    if (!file.isFile) {
        return ""
    }
    val lines = file.readLines()
    val line = lines.firstOrNull { it.startsWith("sdk.dir=") } ?: return ""
    val raw = line.substringAfter("sdk.dir=")
    return raw.trim()
}
