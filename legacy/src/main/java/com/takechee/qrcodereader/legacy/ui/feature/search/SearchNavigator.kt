package com.takechee.qrcodereader.legacy.ui.feature.search

import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.legacy.ui.NavigateHelper
import com.takechee.qrcodereader.legacy.ui.Navigator
import javax.inject.Inject

// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface SearchNavigator : Navigator {
    fun navigateToDetail(contentId: ContentId)
}


// =============================================================================================
//
// NavigateHelper
//
// =============================================================================================
class SearchNavigateHelper @Inject constructor(
    navigateHelper: NavigateHelper
) : SearchNavigator, NavigateHelper by navigateHelper {
    override fun navigateToDetail(contentId: ContentId) {
        navigateTo {
            SearchFragmentDirections.toDetail(contentId)
        }
    }
}