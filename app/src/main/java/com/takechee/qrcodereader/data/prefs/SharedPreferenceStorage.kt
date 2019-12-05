package com.takechee.qrcodereader.data.prefs

import android.content.Context
import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty

/**
 * [PreferenceStorage] impl backed by [android.content.SharedPreferences].
 */
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

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
    }

    override var openReaderWhenAppStarts by booleanPreference(
        prefs,
        Preference.OPEN_READER_WHEN_APP_STARTS,
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
    OPEN_READER_WHEN_APP_STARTS;

    val prefName: String = "pref_${name.toLowerCase(Locale.JAPAN)}"
}

private fun booleanPreference(
    preferences: Lazy<SharedPreferences>,
    preference: Preference,
    defaultValue: Boolean
): ReadWriteProperty<Any, Boolean> {
    return BooleanPreference(preferences, preference.prefName, defaultValue)
}