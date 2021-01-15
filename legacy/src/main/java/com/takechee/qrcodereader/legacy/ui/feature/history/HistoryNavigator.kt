package com.takechee.qrcodereader.legacy.ui.feature.history

import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.legacy.ui.NavigateHelper
import com.takechee.qrcodereader.legacy.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface HistoryNavigator : Navigator {
    fun navigateToDetail(contentId: ContentId)
    fun navigateToSearch()
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

    override fun navigateToSearch() {
        navigateTo {
            HistoryFragmentDirections.toSearch()
        }
    }
}