package com.takechee.qrcodereader.ui.feature.search

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.corecomponent.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class SearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindNavigator(helper: SearchNavigateHelper): SearchNavigator

    @Module
    companion object {
    }
}
