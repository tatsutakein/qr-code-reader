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
    fun navigateToDetail(url: String)
    fun navigateToCapture()
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class HomeNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : HomeNavigator, NavigateHelper by navigateHelper {

    override fun navigateToDetail(url: String) {
        navigateTo {
            HomeFragmentDirections.toDetail(url)
        }
    }

    override fun navigateToCapture() {
        navigateTo {
            HomeFragmentDirections.toCapture()
        }
    }
}