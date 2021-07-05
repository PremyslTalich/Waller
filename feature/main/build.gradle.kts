plugins {
    id("waller-android-module")
}

dependencies {
    implementation(project(":common:ui"))
    implementation(project(":common:navigation"))
    implementation(project(":library:search"))
    implementation(project(":library:navigation"))
}