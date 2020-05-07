package versions

@Suppress("unused")
object Versions {
    const val compileSdkVersion = 29
    const val minSdkVersion = 24
    const val targetSdkVersion = 29

    const val versionCode: Int = AppVersion.code
    const val versionName: String = AppVersion.name
}

private object AppVersion {
    private const val major: Int = 1
    private const val minor: Int = 3
    private const val patch: Int = 0
    private const val build: Int = 0

    internal const val code: Int = (major * 10000 + minor * 100 + patch) * 100 + build
    internal const val name: String = "$major.$minor.$patch"
}