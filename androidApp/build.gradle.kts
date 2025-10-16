plugins {
    kotlin("android")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.compose.sandbox.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.compose.sandbox.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.material3)
    implementation(compose.ui)
    implementation(compose.preview)
    debugImplementation(compose.uiTooling)
}