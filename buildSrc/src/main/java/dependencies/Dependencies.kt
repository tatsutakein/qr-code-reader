package dependencies

@Suppress("unused")
object Dependencies {

    object GradlePlugin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val playServices = "com.google.gms:google-services:4.3.3"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.0.0"
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.Navigation.version}"
        const val licenses = "com.google.android.gms:oss-licenses-plugin:0.10.2"
    }

    object Kotlin {
        internal const val version = "1.3.72"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.2.4"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha02"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        const val browser = "androidx.browser:browser:1.0.0"
        const val preferenceKtx = "androidx.preference:preference-ktx:1.1.0"
        const val material = "com.google.android.material:material:1.2.0-alpha05"

        object Lifecycle {
            private const val version = "2.2.0"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
        }

        object Navigation {
            internal const val version = "2.2.1"
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
            private const val version = "0.1.0-dev09"
            const val framework = "androidx.ui:ui-framework:$version"
            const val tooling = "androidx.ui:ui-tooling:$version"
            const val layout = "androidx.ui:ui-layout:$version"
            const val material = "androidx.ui:ui-material:$version"
        }

        object Licenses {
            const val gradlePlugin = "com.google.android.gms:oss-licenses-plugin:0.10.2"
            const val core = "com.google.android.gms:play-services-oss-licenses:17.0.0"
        }
    }

    object Play {
        const val core = "com.google.android.play:core:1.7.2"
    }

    object Firebase {
        const val analytics = "com.google.firebase:firebase-analytics:17.4.0"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:17.0.0"
    }

    object Dagger {
        private const val version = "2.27"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val processor = "com.google.dagger:dagger-android-processor:$version"

        private const val assistedVersion = "0.5.2"
        const val assistedAnnotations =
            "com.squareup.inject:assisted-inject-annotations-dagger2:$assistedVersion"
        const val assistedProcessor =
            "com.squareup.inject:assisted-inject-processor-dagger2:$assistedVersion"
    }

    object ZXing {
        const val core = "com.google.zxing:core:3.4.0"
        const val embedded = "com.journeyapps:zxing-android-embedded:4.0.0"
    }

    object Groupie {
        private const val version = "2.8.0"
        const val core = "com.xwray:groupie:$version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$version"
    }

    object Permission {
        private const val version = "4.7.0"
        const val dispatcher = "org.permissionsdispatcher:permissionsdispatcher:$version"
        const val processor = "org.permissionsdispatcher:permissionsdispatcher-processor:$version"
    }

    object Threeten {
        const val core = "org.threeten:threetenbp:1.4.4"
        const val notzdb = "org.threeten:threetenbp:1.4.4:no-tzdb"
        const val androidBackport = "com.jakewharton.threetenabp:threetenabp:1.2.3"
    }

    object Test {
        const val junit4 = "junit:junit:4.12"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.5"
        const val mockk = "io.mockk:mockk:1.9.3"
        const val kotlinTestAssertionsJvm = "io.kotest:kotest-assertions-jvm:4.0.5"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
        const val liveDataTestingKtx = "com.jraska.livedata:testing-ktx:1.1.2"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.1"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    }

    const val insetterDbx = "dev.chrisbanes:insetter-dbx:0.2.1"
    const val insetterKtx = "dev.chrisbanes:insetter-ktx:0.2.1"
    const val jsourp = "org.jsoup:jsoup:1.13.1"
    const val lottie = "com.airbnb.android:lottie:3.4.0"
    const val indicator = "com.romandanylyk:pageindicatorview:1.0.3"
}