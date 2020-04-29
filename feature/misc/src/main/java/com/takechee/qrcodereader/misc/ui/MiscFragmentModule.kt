package com.takechee.qrcodereader.misc.ui

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.corecomponent.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class MiscFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MiscViewModel::class)
    abstract fun bindMiscListViewModel(viewModel: MiscViewModel): ViewModel
    
    @Module
    companion object {
    }
}
