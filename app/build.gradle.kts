plugins {
    id("com.android.application")
    id("kotlin-android")
//    id("com.google.firebase.firebase-perf")
    id("com.google.firebase.crashlytics")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(AndroidSdk.compileSdkVersion)
    buildToolsVersion(AndroidSdk.buildToolsVersion)

    defaultConfig {
        applicationId = "eu.talich.waller"
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            useIR = true
            freeCompilerArgs = listOf(
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xuse-experimental=kotlinx.coroutines.InternalCoroutinesApi"
            )
        }

        packagingOptions {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.5.10"
        kotlinCompilerExtensionVersion = AndroidCompose.version
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":library:navigation"))
    implementation(project(":library:internet-observer"))
    implementation(project(":library:search"))
    implementation(project(":library:unsplash"))

    implementation(project(":common:navigation"))

    implementation(project(":feature:main"))
    implementation(project(":feature:search"))
    implementation(project(":feature:photos"))
    implementation(project(":feature:photo-detail"))
    implementation(project(":feature:collections"))
    implementation(project(":feature:collection-detail"))

    implementation(Kotlin.kotlinStbLib)
    implementation(Kotlin.kotlinJdk7)
    implementation(Kotlin.kotlinJdk8)
    implementation(Kotlin.kotlinReflect)

    implementation(AndroidX.appcompat)
    implementation(AndroidX.Navigation.ui)
    implementation(AndroidX.Navigation.fragment)

    implementation(Koin.AndroidX.ext)

    implementation(AndroidCompose.compiler)
    implementation(AndroidCompose.runtime)

    debugImplementation(Flipper.flipper)
    debugImplementation(Flipper.networkPlugin)
    debugImplementation(Flipper.soloader)
    releaseImplementation(Flipper.noop)

    implementation(platform(Firebase.bom))
    implementation(Firebase.analytics)
    implementation(Firebase.crashlytics)
    implementation(Firebase.perfMonitoring)
}
