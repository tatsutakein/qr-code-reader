package com.takechee.qrcodereader.legacy.ui.feature.capture

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.corecomponent.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class CaptureFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(CaptureViewModel::class)
    abstract fun bindCaptureViewModel(viewModel: CaptureViewModel): ViewModel

    @Binds
    abstract fun bindCaptureNavigator(helper: CaptureNavigateHelper): CaptureNavigator

    @Module
    companion object {
    }
}
