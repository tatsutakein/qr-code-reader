package dependencies

@Suppress("unused")
object Dependencies {

    object GradlePlugin {
        const val playServices = "com.google.gms:google-services:4.3.10"
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Jetpack.Navigation.version}"
        const val licenses = "com.google.android.gms:oss-licenses-plugin:0.10.4"
    }

    object Kotlin {
        private const val version = "1.5.31"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"

        object Coroutines {
            internal const val version = "1.5.2"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Jetpack {
        const val coreKtx = "androidx.core:core-ktx:1.7.0-rc01"
        const val appcompat = "androidx.appcompat:appcompat:1.4.0-beta01"
        const val activity = "androidx.activity:activity-ktx:1.4.0-rc01"
        const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.1.0-beta01"
        const val browser = "androidx.browser:browser:1.4.0-rc01"
        const val preferenceKtx = "androidx.preference:preference-ktx:1.1.1"
        const val material = "com.google.android.material:material:1.4.0"

        object Lifecycle {
            private const val version = "2.4.0-rc01"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        object Navigation {
            internal const val version = "2.3.5"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgsGradlePlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.4.0-beta01"
            const val ktx = "androidx.room:room-ktx:${version}"
            const val runtime = "androidx.room:room-runtime:${version}"
            const val compiler = "androidx.room:room-compiler:${version}"
            const val testing = "androidx.room:room-testing:${version}"
        }

        object Licenses {
            const val core = "com.google.android.gms:play-services-oss-licenses:17.0.0"
        }
    }

    object Play {
        const val core = "com.google.android.play:core:1.10.2"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:28.4.2"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"

        object Crashlytics {
            const val core = "com.google.firebase:firebase-crashlytics-ktx"
            const val plugin = "com.google.firebase:firebase-crashlytics-gradle:2.7.1"
        }
    }

    object Dagger {
        private const val version = "2.39.1"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val processor = "com.google.dagger:dagger-android-processor:$version"
    }

    object ZXing {
        const val core = "com.google.zxing:core:3.4.1"
        const val embedded = "com.journeyapps:zxing-android-embedded:4.2.0"
    }

    object Groupie {
        private const val version = "2.10.0"
        const val core = "com.github.lisawray.groupie:groupie:$version"
        const val viewBinding = "com.github.lisawray.groupie:groupie-viewbinding:$version"
    }

    object Permission {
        private const val version = "4.8.0"
        const val dispatcher = "org.permissionsdispatcher:permissionsdispatcher:$version"
        const val processor = "org.permissionsdispatcher:permissionsdispatcher-processor:$version"
    }

    object Threeten {
        private const val version = "1.5.1"
        const val core = "org.threeten:threetenbp:$version"
        const val notzdb = "org.threeten:threetenbp:$version:no-tzdb"
        const val androidBackport = "com.jakewharton.threetenabp:threetenabp:1.3.1"
    }

    object Test {
        const val junit4 = "junit:junit:4.13.2"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.Coroutines.version}"
        const val mockk = "io.mockk:mockk:1.12.0"
        const val kotlinTestAssertionsJvm = "io.kotest:kotest-assertions-jvm:4.6.3"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val liveDataTestingKtx = "com.jraska.livedata:testing-ktx:1.2.0"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Insetter {
        private const val version = "0.6.0"
        const val core = "dev.chrisbanes.insetter:insetter:$version"
        const val dbx = "dev.chrisbanes.insetter:insetter-dbx:$version"
    }

    const val jsourp = "org.jsoup:jsoup:1.14.3"
    const val lottie = "com.airbnb.android:lottie:4.1.0"
    const val indicator = "com.romandanylyk:pageindicatorview:1.0.3"
}