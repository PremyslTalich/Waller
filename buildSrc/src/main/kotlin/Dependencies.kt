object Kotlin {
    const val version = "1.5.10"

    const val kotlinStbLib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    const val kotlinJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    const val kotlinJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

    object KotlinX {
        private const val version = "1.5.0"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
    }
}

object JUnit {
    private const val version = "4.13.2"

    const val jUnit = "junit:junit:$version"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.5.0"
    const val appcompat = "androidx.appcompat:appcompat:1.3.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
    const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
    const val fragment = "androidx.fragment:fragment-ktx:1.4.0-alpha02"

    object Navigation {
        const val version = "2.3.5"

        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui = "androidx.navigation:navigation-ui-ktx:$version"
    }

    object Test {
        const val jUnitExt = "androidx.test.ext:junit:1.1.2"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    }

    object Compose {
        const val runtime = "androidx.compose:compose-runtime:0.1.0-dev14"
    }
}

object AndroidCompose {
    const val version = "1.0.0-rc01"

    const val compiler = "androidx.compose.compiler:compiler:$version"
    const val runtime = "androidx.compose.runtime:runtime:$version"
    const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
    const val foundation = "androidx.compose.foundation:foundation:$version"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:$version"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha08"
    const val ui = "androidx.compose.ui:ui:$version"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
    const val uiTest = "androidx.compose.ui:ui-test:$version"
    const val material = "androidx.compose.material:material:$version"
    const val composeThemeAdapter = "com.google.android.material:compose-theme-adapter:$version"
}

object AndroidMaterial {
    private const val version = "1.3.0"

    const val material = "com.google.android.material:material:$version"
}

object Koin {
    private const val version = "2.2.2"

    const val core = "org.koin:koin-core:$version"
    const val coreExt = "org.koin:koin-core-ext:$version"
    const val test = "org.koin:koin-test:$version"

    object AndroidX {
        const val scope = "org.koin:koin-androidx-scope:$version"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"
        const val fragment = "org.koin:koin-androidx-fragment:$version"
        const val ext = "org.koin:koin-androidx-ext:$version"
    }
}

object Firebase {
    private const val version = "26.4.0"

    const val bom = "com.google.firebase:firebase-bom:$version"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val perfMonitoring = "com.google.firebase:firebase-perf-ktx:20.0.1"
}

object Flipper {
    private const val version = "0.51.0"

    const val flipper = "com.facebook.flipper:flipper:$version"
    const val networkPlugin = "com.facebook.flipper:flipper-network-plugin:$version"
    const val noop = "com.facebook.flipper:flipper-noop:$version"

    const val soloader = "com.facebook.soloader:soloader:0.10.1"
}

object Retrofit {
    private const val version = "2.9.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$version"
}

object Moshi {
    private const val version = "1.11.0"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$version"
}

object Coil {
    private const val version = "1.1.1"

    const val coil = "io.coil-kt:coil:$version"
}

object Accompanist {
    private const val version = "0.14.0-SNAPSHOT"

    const val accompanist = "com.google.accompanist:accompanist-coil:$version"
}

object Glide {
    private const val version = "4.12.0"

    const val glide = "com.github.bumptech.glide:glide:$version"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$version"
}

object BlurHash {
    private const val version = "539cf8a5425d766227b52e1d487a231bc5c7b72e"

    const val blurHash = "com.github.woltapp:blurhash:$version"
}

object PhotoView {
    private const val version = "2.3.0"

    const val photoView = "com.github.chrisbanes:PhotoView:$version"
}
