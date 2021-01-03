package com.takechee.qrcodereader.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.takechee.qrcodereader.corecomponent.data.prefs.PreferenceStorage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
@FlowPreview
@ExperimentalCoroutinesApi
@Singleton
class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    private val prefs: Lazy<SharedPreferences> = lazy {
        // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        ).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val shortcutGuideVisibleChannel: ConflatedBroadcastChannel<Boolean> by lazy {
        ConflatedBroadcastChannel<Boolean>().also { channel ->
            channel.offer(shortcutGuideVisible)
        }
    }

    private val useBrowserAppChannel: ConflatedBroadcastChannel<Boolean> by lazy {
        ConflatedBroadcastChannel<Boolean>().also { channel ->
            channel.offer(useBrowserApp)
        }
    }

    private val autoLoadNicknameChannel: ConflatedBroadcastChannel<Boolean> by lazy {
        ConflatedBroadcastChannel<Boolean>().also { channel ->
            channel.offer(useBrowserApp)
        }
    }

    override val shortcutGuideVisibleFlow: Flow<Boolean>
        get() = shortcutGuideVisibleChannel.asFlow()

    override val useBrowserAppFlow: Flow<Boolean>
        get() = useBrowserAppChannel.asFlow()

    override val autoLoadNicknameFlow: Flow<Boolean>
        get() = autoLoadNicknameChannel.asFlow()

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (Preference.of(key)) {
            Preference.SHORTCUT_GUIDE_VISIBLE -> shortcutGuideVisibleChannel.offer(
                shortcutGuideVisible
            )
            Preference.USE_BROWSER_APP -> useBrowserAppChannel.offer(useBrowserApp)
            Preference.AUTO_LOAD_NICKNAME -> autoLoadNicknameChannel.offer(autoLoadNickname)
            else -> {
            }
        }
    }

    override var onboardingCompleted by booleanPreference(
        prefs,
        Preference.ONBOADING_V1,
        false
    )

    override var shortcutGuideVisible by booleanPreference(
        prefs,
        Preference.SHORTCUT_GUIDE_VISIBLE,
        true
    )

    override var useBrowserApp by booleanPreference(
        prefs,
        Preference.USE_BROWSER_APP,
        false
    )

    override var autoLoadNickname by booleanPreference(
        prefs,
        Preference.AUTO_LOAD_NICKNAME,
        false
    )

    companion object {
        const val PREFS_NAME = "qrcodereader"
    }

    fun registerOnPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.value.registerOnSharedPreferenceChangeListener(listener)
    }
}

private enum class Preference {
    ONBOADING_V1,
    SHORTCUT_GUIDE_VISIBLE,
    USE_BROWSER_APP,
    AUTO_LOAD_NICKNAME;

    val prefName: String = "pref_${name.toLowerCase(Locale.JAPAN)}"

    companion object {
        fun of(prefName: String): Preference? {
            return values().firstOrNull { it.prefName == prefName }
        }
    }
}

private fun booleanPreference(
    preferences: Lazy<SharedPreferences>,
    preference: Preference,
    defaultValue: Boolean
): ReadWriteProperty<Any, Boolean> {
    return BooleanPreference(preferences, preference.prefName, defaultValue)
}