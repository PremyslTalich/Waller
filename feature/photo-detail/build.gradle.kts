plugins {
    id("waller-android-module")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(":library:navigation"))
    implementation(project(":common:navigation"))
    implementation(project(":common:ui"))
    implementation(project(":library:unsplash"))
    implementation(project(":library:search"))
}