package com.takechee.qrcodereader.ui.feature.capture

import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface CaptureNavigator : Navigator {
    fun navigateToDetail(contentId: ContentId)
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class CaptureNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : CaptureNavigator, NavigateHelper by navigateHelper {

    override fun navigateToDetail(contentId: ContentId) {
        navigateTo {
            CaptureFragmentDirections.toDetail(contentId)
        }
    }
}