package com.takechee.qrcodereader.ui.feature.home

import android.content.Intent
import com.takechee.qrcodereader.ui.NavigationHost

sealed class HomeEvent {
    object SwitchingHistory : HomeEvent() {
        val value = NavigationHost.BottomNavigationMenu.HISTORY
    }

    class OpenDetail(val intent: Intent) : HomeEvent()
}