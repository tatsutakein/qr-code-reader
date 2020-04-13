package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.ui.NavigationHost

sealed class HomeEvent {
    object SwitchingHistory : HomeEvent() {
        val value = NavigationHost.BottomNavigationMenu.HISTORY
    }
}