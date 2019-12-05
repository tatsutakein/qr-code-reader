package dependencies

@Suppress("unused")
object Dependencies {
    object Kotlin {
        private const val version = "1.3.60"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val fragment = "androidx.fragment:fragment:1.2.0-rc02"
        const val coreKtx = "androidx.core:core-ktx:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val preferenceKtx = "androidx.preference:preference-ktx:1.1.0"
        const val material = "com.google.android.material:material:1.2.0-alpha01"

        object Lifecycle {
            private const val version = "2.1.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:1.0.0-rc02"
        }

        object Navigation {
            private const val version = "2.1.0"
            const val safeArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }
    }

    object Dagger {
        private const val version = "2.24"
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
        private const val version = "2.7.2"
        const val core = "com.xwray:groupie:$version"
        const val dataBinding = "com.xwray:groupie-databinding:$version"
    }
}