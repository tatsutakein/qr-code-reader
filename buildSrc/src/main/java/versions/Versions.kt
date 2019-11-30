package versions

@Suppress("unused")
object Versions {
    const val compileSdkVersion = 29
    const val minSdkVersion = 24
    const val targetSdkVersion = 29
    
    const val versionCode = AppVersion.major * 10000 + AppVersion.minor * 100 + AppVersion.patch
    const val versionName = "${AppVersion.major}.${AppVersion.minor}.${AppVersion.patch}"
}

private object AppVersion {
    const val major = 1
    const val minor = 0
    const val patch = 0
}