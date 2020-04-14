package com.takechee.qrcodereader.ui.feature.capture

import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface CaptureNavigator : Navigator {
    fun navigateToResult(url: String)
    fun navigateToHistory()
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class CaptureNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : CaptureNavigator, NavigateHelper by navigateHelper {

    override fun navigateToResult(url: String) {
//        navigateTo {
//            HomeFragmentDirections.toResult(url)
//        }
    }

    override fun navigateToHistory() {
//        navigateTo {
//            HomeFragmentDirections.toHistory()
//        }
    }
}