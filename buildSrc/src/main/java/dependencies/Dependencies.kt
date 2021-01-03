package dependencies

@Suppress("unused")
object Dependencies {

    object GradlePlugin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val playServices = "com.google.gms:google-services:4.3.4"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.3.0"
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.Navigation.version}"
        const val licenses = "com.google.android.gms:oss-licenses-plugin:0.10.2"
    }

    object Kotlin {
        internal const val version = "1.4.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Coroutines {
            private const val version = "1.4.2"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.2.5"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.3"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha06"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        const val browser = "androidx.browser:browser:1.2.0"
        const val preferenceKtx = "androidx.preference:preference-ktx:1.1.1"
        const val material = "com.google.android.material:material:1.2.1"

        object Lifecycle {
            private const val version = "2.2.0"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        object Navigation {
            internal const val version = "2.3.1"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object Room {
            private const val version = "2.2.5"
            const val ktx = "androidx.room:room-ktx:${version}"
            const val runtime = "androidx.room:room-runtime:${version}"
            const val compiler = "androidx.room:room-compiler:${version}"
            const val testing = "androidx.room:room-testing:${version}"
        }

        object Compose {
            private const val version = "1.0.0-alpha06"
            const val framework = "androidx.ui:ui-framework:$version"
            const val tooling = "androidx.ui:ui-tooling:$version"
            const val layout = "androidx.ui:ui-layout:$version"
            const val material = "androidx.ui:ui-material:$version"
        }

        object Licenses {
            const val core = "com.google.android.gms:play-services-oss-licenses:17.0.0"
        }
    }

    object Play {
        const val core = "com.google.android.play:core:1.8.3"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:26.0.0"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    }

    object Dagger {
        private const val version = "2.29.1"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val processor = "com.google.dagger:dagger-android-processor:$version"

        private const val assistedVersion = "0.6.0"
        const val assistedAnnotations =
            "com.squareup.inject:assisted-inject-annotations-dagger2:$assistedVersion"
        const val assistedProcessor =
            "com.squareup.inject:assisted-inject-processor-dagger2:$assistedVersion"
    }

    object ZXing {
        const val core = "com.google.zxing:core:3.4.1"
        const val embedded = "com.journeyapps:zxing-android-embedded:4.1.0"
    }

    object Groupie {
        private const val version = "2.8.1"
        const val core = "com.xwray:groupie:$version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$version"
    }

    object Permission {
        private const val version = "4.8.0"
        const val dispatcher = "org.permissionsdispatcher:permissionsdispatcher:$version"
        const val processor = "org.permissionsdispatcher:permissionsdispatcher-processor:$version"
    }

    object Threeten {
        private const val version = "1.5.0"
        const val core = "org.threeten:threetenbp:$version"
        const val notzdb = "org.threeten:threetenbp:$version:no-tzdb"
        const val androidBackport = "com.jakewharton.threetenabp:threetenabp:1.3.0"
    }

    object Test {
        const val junit4 = "junit:junit:4.13.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.0"
        const val mockk = "io.mockk:mockk:1.10.0"
        const val kotlinTestAssertionsJvm = "io.kotest:kotest-assertions-jvm:4.3.0"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val liveDataTestingKtx = "com.jraska.livedata:testing-ktx:1.1.2"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    }

    const val insetterDbx = "dev.chrisbanes:insetter-dbx:0.3.1"
    const val insetterKtx = "dev.chrisbanes:insetter-ktx:0.3.1"
    const val jsourp = "org.jsoup:jsoup:1.13.1"
    const val lottie = "com.airbnb.android:lottie:3.4.4"
    const val indicator = "com.romandanylyk:pageindicatorview:1.0.3"
}