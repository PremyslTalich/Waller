plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
}

gradlePlugin {
    plugins {
        register("waller-kotlin-module") {
            id = "waller-kotlin-module"
            implementationClass = "eu.talich.waller.plugin.WallerKotlinModule"
        }
        register("waller-android-module") {
            id = "waller-android-module"
            implementationClass = "eu.talich.waller.plugin.WallerAndroidModule"
        }
    }
}
