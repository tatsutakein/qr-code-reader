package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface HomeNavigator : Navigator {
    fun navigateToDetail(contentId: ContentId)
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

    override fun navigateToDetail(contentId: ContentId) {
        navigateTo {
            HomeFragmentDirections.toDetail(contentId)
        }
    }

    override fun navigateToCapture() {
        navigateTo {
            HomeFragmentDirections.toCapture()
        }
    }
}