package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface HomeNavigator : Navigator {
    fun navigateToResult(url: String)
    fun navigateToHistory()
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class HomeNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : HomeNavigator, NavigateHelper by navigateHelper {

    override fun navigateToResult(url: String) {
        navigateTo {
            HomeFragmentDirections.toResult(url)
        }
    }

    override fun navigateToHistory() {
        navigateTo {
            HomeFragmentDirections.toHistory()
        }
    }
}