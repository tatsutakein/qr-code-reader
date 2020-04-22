package com.takechee.qrcodereader.ui.feature.detail

import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface DetailNavigator : Navigator {
    fun navigatePopBack()
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class DetailNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : DetailNavigator, NavigateHelper by navigateHelper