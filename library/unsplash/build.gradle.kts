plugins {
    id("waller-android-module")
}

android {
    defaultConfig {
        buildConfigField(
            "String",
            "WALLER_ACCESS_KEY",
            "\"${project.findProperty("WALLER_ACCESS_KEY")}\"")
    }
}
