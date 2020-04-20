package com.takechee.qrcodereader.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
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

    override val shortcutGuideVisibleFlow: Flow<Boolean>
        get() = shortcutGuideVisibleChannel.asFlow()

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (Preference.of(key)) {
            Preference.SHORTCUT_GUIDE_VISIBLE -> shortcutGuideVisibleChannel.offer(
                shortcutGuideVisible
            )
            else -> {
            }
        }
    }

    override var openReaderWhenAppStarts by booleanPreference(
        prefs,
        Preference.OPEN_READER_WHEN_APP_STARTS,
        false
    )

    override var shortcutGuideVisible by booleanPreference(
        prefs,
        Preference.SHORTCUT_GUIDE_VISIBLE,
        true
    )

    companion object {
        const val PREFS_NAME = "qrcodereader"
    }

    fun registerOnPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.value.registerOnSharedPreferenceChangeListener(listener)
    }
}

private enum class Preference {
    OPEN_READER_WHEN_APP_STARTS,
    SHORTCUT_GUIDE_VISIBLE;

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