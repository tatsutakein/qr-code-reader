package com.takechee.qrcodereader.ui.feature.history

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
abstract class HistoryFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel


    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideIntentIntegrator(fragment: HistoryFragment): IntentIntegrator {
            return IntentIntegrator.forSupportFragment(fragment).apply {
                captureActivity = CustomCaptureActivity::class.java
            }
        }
    }
}
