package com.takechee.qrcodereader.legacy.ui.feature.history

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.corecomponent.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class HistoryFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @Binds
    abstract fun bindNavigator(helper: HistoryNavigateHelper): HistoryNavigator

    @Module
    companion object {
    }
}
