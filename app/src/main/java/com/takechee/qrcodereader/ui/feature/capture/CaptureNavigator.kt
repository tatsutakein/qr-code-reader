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
    fun navigateToDetail(text: String)
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class CaptureNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : CaptureNavigator, NavigateHelper by navigateHelper {

    override fun navigateToDetail(text: String) {
        navigateTo {
            CaptureFragmentDirections.toDetail(text)
        }
    }
}