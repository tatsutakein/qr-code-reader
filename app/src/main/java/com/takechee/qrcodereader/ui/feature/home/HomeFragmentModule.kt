package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.corecomponent.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindsHomeNavigator(helper: HomeNavigateHelper): HomeNavigator

    @Module
    companion object {
    }
}
