package com.takechee.qrcodereader.ui.feature.capture

import androidx.lifecycle.ViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.takechee.qrcodereader.di.ViewModelKey
import com.takechee.qrcodereader.ui.CustomCaptureActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
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
