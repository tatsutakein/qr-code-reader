package com.takechee.qrcodereader.ui.feature.search

import androidx.core.text.trimmedLength
import androidx.lifecycle.*
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.util.extension.addSources
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val navigator: SearchNavigator,
    repository: ContentRepository
) : BaseViewModel(), SearchEventListener, Navigator by navigator {

    val contents: LiveData<SearchState>

    private val query = MutableLiveData("")


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        val queryLiveData = query.distinctUntilChanged()

        contents = MediatorLiveData<SearchState>().apply {
            fun update() {
                val queryText = queryLiveData.value?.toString() ?: ""
                if (queryText.trimmedLength() < 2) {
                    value = SearchState.empty()
                    return
                }
                viewModelScope.launch {
                    val contents = repository.searchFromText(queryText)
                    value = if (contents.isNotEmpty()) {
                        SearchState.success(contents)
                    } else {
                        SearchState.noResult()
                    }
                }
            }
            addSources(queryLiveData, onChanged = ::update)
        }.distinctUntilChanged()
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onSearchResultItemClick(content: Content) {
        navigator.navigateToDetail(content.id)
    }

    override fun onSearchQueryChanged(newText: String) {
        query.value = newText
    }
}