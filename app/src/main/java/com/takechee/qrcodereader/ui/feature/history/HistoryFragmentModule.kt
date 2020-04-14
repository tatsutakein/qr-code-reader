package com.takechee.qrcodereader.ui.feature.history

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.di.ViewModelKey
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
    
    @Module
    companion object {
    }
}
