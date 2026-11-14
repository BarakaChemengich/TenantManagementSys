// Top-level build file where you can add configuration options common to all subprojects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
// Safe Args: generates classes for passing data between screens
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false

    // KSP generates Room's database code while building
    id("com.google.devtools.ksp") version "2.3.13" apply false
}