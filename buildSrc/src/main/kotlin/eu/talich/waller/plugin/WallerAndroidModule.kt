package eu.talich.waller.plugin

import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class WallerAndroidModule : Plugin<Project> {
    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureDependencies()

        project.configureAndroid()
    }
}

private fun Project.configurePlugins() {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-android-extensions")
}

private fun Project.configureAndroid() {
    extensions.getByType<LibraryExtension>().run {
        buildToolsVersion(AndroidSdk.buildToolsVersion)
        compileSdkVersion(AndroidSdk.compileSdkVersion)

        defaultConfig {
            minSdkVersion(AndroidSdk.minSdkVersion)
            targetSdk = AndroidSdk.targetSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures.apply {
            viewBinding = true
            compose = true
        }

        composeOptions {
            kotlinCompilerVersion = "1.5.10"
            kotlinCompilerExtensionVersion = AndroidCompose.version
        }

        tasks.withType(KotlinJvmCompile::class.java) {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                useIR = true
                freeCompilerArgs = listOf(
                    "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xuse-experimental=kotlinx.coroutines.InternalCoroutinesApi",
                    "-Xallow-jvm-ir-dependencies"
                )
            }
        }

        buildTypes {
            getByName("debug") {
                isTestCoverageEnabled = false
                isMinifyEnabled = false
            }

            getByName("release") {
                isTestCoverageEnabled = false
                isMinifyEnabled = false

                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }
    }
}

private fun Project.configureDependencies() {
    dependencies.apply {
        // Kotlin
        add("implementation", Kotlin.kotlinStbLib)
        add("implementation", Kotlin.KotlinX.coroutinesCore)
        add("implementation", Kotlin.KotlinX.coroutinesAndroid)

        // Koin
        add("implementation", Koin.core)
        add("implementation", Koin.coreExt)
        add("implementation", Koin.AndroidX.scope)
        add("implementation", Koin.AndroidX.viewmodel)
        add("implementation", Koin.AndroidX.fragment)
        add("implementation", Koin.AndroidX.ext)
        add("testImplementation", Koin.test)

        // Android
        add("implementation", AndroidX.core)
        add("implementation", AndroidX.appcompat)
        add("implementation", AndroidX.constraintLayout)
        add("implementation", AndroidX.recyclerView)
        add("implementation", AndroidX.lifecycle)
        add("implementation", AndroidX.legacySupport)
        add("implementation", AndroidX.fragment)
        add("implementation", AndroidX.Navigation.fragment)
        add("implementation", AndroidX.Navigation.ui)
        add("implementation", AndroidMaterial.material)
        add("testImplementation", JUnit.jUnit)
        add("androidTestImplementation", AndroidX.Test.jUnitExt)
        add("androidTestImplementation", AndroidX.Test.espressoCore)

        // Android Compose
        add("implementation", AndroidX.Compose.runtime)
        add("implementation", AndroidCompose.runtime)
        add("implementation", AndroidCompose.compiler)
        add("implementation", AndroidCompose.ui)
        add("implementation", AndroidCompose.foundation)
        add("implementation", AndroidCompose.foundationLayout)
        add("implementation", AndroidCompose.constraintLayout)
        add("implementation", AndroidCompose.material)
        add("implementation", AndroidCompose.runtimeLivedata)
        add("implementation", AndroidCompose.uiTooling)
        add("implementation", AndroidCompose.uiTest)
        add("implementation", AndroidCompose.composeThemeAdapter)

        // Retrofit + Moshi
        add("implementation", Retrofit.retrofit)
        add("implementation", Retrofit.converterMoshi)
        add("implementation", Moshi.moshiKotlin)
        add("implementation", Moshi.moshiAdapters)

        // Glide
        add("implementation", Glide.glide)
        add("annotationProcessor", Glide.glideCompiler)

        // Coil
        add("implementation", Coil.coil)

        // Accompanist
        add("implementation", Accompanist.accompanist)

        // BlurHash
        add("implementation", BlurHash.blurHash)

        // PhotoView
        add("implementation", PhotoView.photoView)

        // Flipper
        add("debugImplementation", Flipper.flipper)
        add("debugImplementation", Flipper.networkPlugin)
        add("debugImplementation", Flipper.soloader)
        add("releaseImplementation", Flipper.noop)
    }
}