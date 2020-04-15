package com.takechee.qrcodereader.ui.feature.misc

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class MiscListFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MiscListViewModel::class)
    abstract fun bindMiscListViewModel(viewModel: MiscListViewModel): ViewModel
    
    @Module
    companion object {
    }
}
