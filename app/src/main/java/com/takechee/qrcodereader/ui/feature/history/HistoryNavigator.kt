package com.takechee.qrcodereader.ui.feature.history

import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface HistoryNavigator : Navigator {
    fun navigateToDetail(contentId: ContentId)
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class HistoryNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : HistoryNavigator, NavigateHelper by navigateHelper {
    override fun navigateToDetail(contentId: ContentId) {
        navigateTo {
            HistoryFragmentDirections.toDetail(contentId)
        }
    }
}