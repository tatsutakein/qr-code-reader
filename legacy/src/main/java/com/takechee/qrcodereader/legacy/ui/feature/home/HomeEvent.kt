package com.takechee.qrcodereader.legacy.ui.feature.home

import com.takechee.qrcodereader.legacy.ui.NavigationHost

sealed class HomeEvent {
    object SwitchingHistory : HomeEvent() {
        val value = NavigationHost.BottomNavigationMenu.HISTORY
    }
}