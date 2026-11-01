plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "id.hutanceria.pixel"
    compileSdk = 35

    defaultConfig {
        applicationId = "id.hutanceria.pixel"
        minSdk = 23
        targetSdk = 35
        versionCode = 8
        versionName = "1.8"
    }
}
