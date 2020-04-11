package com.takechee.qrcodereader.ui.common.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent

interface Navigator {
    val navDirections: LiveData<Event<NavDirections>>
}

interface NavigateHelper : Navigator {
    fun navigateTo(factory: () -> NavDirections)
}

class DefaultNavigateHelper : NavigateHelper {
    private val _navDirections = MutableLiveData<Event<NavDirections>>()
    override val navDirections: LiveData<Event<NavDirections>>
        get() = _navDirections

    override fun navigateTo(factory: () -> NavDirections) {
        _navDirections.fireEvent(factory)
    }
}