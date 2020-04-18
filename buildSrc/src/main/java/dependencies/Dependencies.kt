package dependencies

@Suppress("unused")
object Dependencies {
    object Kotlin {
        private const val version = "1.3.72"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.2.4"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta4"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.2.0-alpha02"
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
            private const val version = "2.2.1"
            const val safeArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
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

        object Licenses {
            const val gradlePlugin = "com.google.android.gms:oss-licenses-plugin:0.10.2"
            const val core = "com.google.android.gms:play-services-oss-licenses:17.0.0"
        }
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
        const val dataBinding = "com.xwray:groupie-databinding:$version"
        const val viewBinding = "com.xwray:groupie-viewbinding:$version"
    }

    const val permissionsdispatcher = "org.permissionsdispatcher:permissionsdispatcher-ktx:1.0.0-alpha2"
    const val threeten = "com.jakewharton.threetenabp:threetenabp:1.2.3"
    const val insetterDbx = "dev.chrisbanes:insetter-dbx:0.2.1"
    const val insetterKtx = "dev.chrisbanes:insetter-ktx:0.2.1"
    const val jsourp = "org.jsoup:jsoup:1.13.1"
}