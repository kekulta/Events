plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    /**
     * Modules
     */
    implementation(project(":common"))

    /**
     * KotlinX
     */
    implementation(platform(libs.kotlinx.coroutines.bom))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    /**
     * DI
     */
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    /**
     * Tests
     */
    testImplementation(libs.junit)
    testImplementation(libs.koin.test)
    testImplementation(libs.kotlinx.coroutines.test)
}
