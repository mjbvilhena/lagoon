plugins {
    id("com.android.library")
}

android {
    namespace = "com.mjbvilhena.lagoon.ui"
    compileSdk = 37

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":domain"))
    implementation("androidx.biometric:biometric:1.1.0")
}
