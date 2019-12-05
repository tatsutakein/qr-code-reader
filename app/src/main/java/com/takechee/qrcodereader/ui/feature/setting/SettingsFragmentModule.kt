package com.takechee.qrcodereader.ui.feature.setting

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class SettingsFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel


    @Module
    companion object {
    }
}
