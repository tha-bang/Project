// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.3") // Google Services Plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1") // Hilt Gradle Plugin
    }
}

allprojects {
    repositories {
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}