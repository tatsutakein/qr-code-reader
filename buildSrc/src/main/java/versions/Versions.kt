package versions

@Suppress("unused")
object Versions {
    const val compileSdkVersion = 31
    const val minSdkVersion = 24
    const val targetSdkVersion = 31

    const val versionCode: Int = AppVersion.code
    const val versionName: String = AppVersion.name
}

private object AppVersion {
    private const val major: Int = 1
    private const val minor: Int = 6
    private const val patch: Int = 0
    private const val build: Int = 0

    const val code: Int = (major * 10000 + minor * 100 + patch) * 100 + build
    const val name: String = "$major.$minor.$patch"
}