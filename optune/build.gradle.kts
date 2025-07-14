// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt.android.gradle) apply false
    alias(libs.plugins.google.gms.services) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false // Added for serialization support
}

allprojects {
    repositories {
        //google() // Added for Google Maven
       // mavenCentral() // Added for central Maven
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}