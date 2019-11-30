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
        const val coreKtx = "androidx.core:core-ktx:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val material = "com.google.android.material:material:1.2.0-alpha01"

        object Lifecycle {
            private const val version = "2.1.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Navigation {
            private const val version = "2.1.0"
            const val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }
    }

    object Dagger {
        private const val version = "2.24"
        const val android = "com.google.dagger:dagger-android:$version"
        const val androidSupport =  "com.google.dagger:dagger-android-support:$version"
        const val compiler =  "com.google.dagger:dagger-compiler:$version"
        const val processor =  "com.google.dagger:dagger-android-processor:$version"
    }

    const val zxing = "com.journeyapps:zxing-android-embedded:4.0.0"
}