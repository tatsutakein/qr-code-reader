package com.takechee.qrcodereader.ui.feature.home

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
abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindsHomeNavigator(helper: HomeNavigateHelper): HomeNavigator

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideIntentIntegrator(fragment: HomeFragment): IntentIntegrator {
            return IntentIntegrator.forSupportFragment(fragment).apply {
                captureActivity = CustomCaptureActivity::class.java
            }
        }
    }
}
