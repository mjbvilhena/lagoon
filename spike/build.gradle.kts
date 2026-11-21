plugins {
    id("com.android.application")
}

android {
    namespace = "com.mjbvilhena.lagoon.spike"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.mjbvilhena.lagoon.spike"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("net.zetetic:sqlcipher-android:4.6.1")
    implementation("androidx.sqlite:sqlite:2.5.1")
    androidTestImplementation("androidx.sqlite:sqlite:2.5.1")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
}
