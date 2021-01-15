package com.takechee.qrcodereader.legacy.ui.feature.detail

import com.takechee.qrcodereader.legacy.ui.NavigateHelper
import com.takechee.qrcodereader.legacy.ui.Navigator
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