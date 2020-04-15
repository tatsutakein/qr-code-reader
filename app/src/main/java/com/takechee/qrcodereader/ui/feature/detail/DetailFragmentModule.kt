package com.takechee.qrcodereader.ui.feature.detail

import androidx.lifecycle.ViewModel
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.takechee.qrcodereader.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class DetailFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Module
    companion object {
        @DetailFragmentScoped
        @JvmStatic
        @Provides
        fun provideDetailFragmentArguments(fragment: DetailFragment): DetailFragmentArguments {
            return DetailFragmentArguments.of(fragment.args)
        }

        @DetailFragmentScoped
        @JvmStatic
        @Provides
        fun provideBarcodeEncoder(): BarcodeEncoder = BarcodeEncoder()
    }
}