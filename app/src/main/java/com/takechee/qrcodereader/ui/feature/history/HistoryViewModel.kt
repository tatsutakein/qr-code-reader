package com.takechee.qrcodereader.ui.feature.history

import androidx.lifecycle.*
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.ui.Navigator
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val navigator: HistoryNavigator,
    repository: ContentRepository
) : BaseViewModel(), HistoryEventListener, Navigator by navigator {

    val contents: LiveData<List<Content>>
    private val _favoriteFilterEnabled = MutableLiveData(false)
    val favoriteFilterEnabled: LiveData<Boolean>
        get() = _favoriteFilterEnabled.distinctUntilChanged()


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        val contentsLiveData = repository.getContentsAllFlow()
            .asLiveData(viewModelScope.coroutineContext)
            .distinctUntilChanged()
        val favoriteFilterEnabledLiveData = favoriteFilterEnabled

        contents = MediatorLiveData<List<Content>>().apply {
            fun update() {
                val contents = contentsLiveData.value.orEmpty()
                val favoriteFilterEnabled = favoriteFilterEnabledLiveData.value ?: false
                value = if (favoriteFilterEnabled) {
                    contents.filter { content -> content.isFavorite }
                } else {
                    contents
                }
            }
            listOf(
                contentsLiveData,
                favoriteFilterEnabledLiveData
            ).forEach { source ->
                addSource(source) { update() }
            }
        }.distinctUntilChanged()
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onHistoryItemClick(content: Content) {
        navigator.navigateToDetail(content.id)
    }

    override fun onFilterEnableChangeClick() {
        _favoriteFilterEnabled.value = _favoriteFilterEnabled.value?.not() ?: false
    }

    override fun onSearchClick() {
        navigator.navigateToSearch()
    }
}