package com.takechee.qrcodereader.corecomponent.data.prefs

import kotlinx.coroutines.flow.Flow

/**
 * Storage for app and user preferences.
 */
interface PreferenceStorage {
    val shortcutGuideVisibleFlow: Flow<Boolean>
    val useBrowserAppFlow: Flow<Boolean>

    var onboardingCompleted: Boolean
    var shortcutGuideVisible: Boolean
    var useBrowserApp: Boolean
}