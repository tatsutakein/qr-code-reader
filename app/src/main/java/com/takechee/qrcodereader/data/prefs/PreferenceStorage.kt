package com.takechee.qrcodereader.data.prefs

import kotlinx.coroutines.flow.Flow

/**
 * Storage for app and user preferences.
 */
interface PreferenceStorage {
    val shortcutGuideVisibleFlow: Flow<Boolean>

    var openReaderWhenAppStarts: Boolean
    var shortcutGuideVisible: Boolean
}