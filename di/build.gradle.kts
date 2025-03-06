plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "br.com.hellodev.di"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Domain
    implementation(project(":domain"))

    // Core
    implementation(project(":core"))

    // Common


    implementation(project(":onboarding"))
    implementation(project(":authentication"))
    implementation(project(":setup"))
    implementation(project(":main"))
    implementation(project(":job-search"))
    implementation(project(":job-details"))

    // Features - Profile
    implementation(project(":features:profile"))

    // Koin
    implementation(libs.koin.compose)
}