// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            setUrl("https://jitpack.io")
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")

        classpath("com.google.gms:google-services:4.3.8")
        classpath("com.google.firebase:perf-plugin:1.4.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.7.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            setUrl("https://oss.sonatype.org/content/repositories/snapshots")
        }
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

task("clean") {
    delete(project.buildDir)
}
