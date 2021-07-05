plugins {
    id("waller-android-module")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(":common:ui"))
    implementation(project(":common:navigation"))
    implementation(project(":library:search"))
    implementation(project(":library:unsplash"))
    implementation(project(":library:navigation"))
    implementation(project(":library:internet-observer"))
    implementation(project(":library:internet-observer"))
}