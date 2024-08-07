plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.kekulta.events"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kekulta.events"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    /**
     * Modules
     */
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":data"))

    /**
     * AndroidX
     */
    implementation(libs.androidx.core.splashscreen)

    /**
     * Compose
     */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.telephoto.zoomable)
    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)

    /**
     * KotlinX
     */
    implementation(platform(libs.kotlinx.coroutines.bom))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    /**
     * DI
     */
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.compose)

    /**
     * Logging
     */
    implementation(libs.logcat)

    /**
     * Tests
     */
    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
}
