package com.takechee.qrcodereader.ui.feature.detail

import androidx.lifecycle.ViewModel
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.takechee.qrcodereader.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class DetailFragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeEditNicknameDialogFragment(): EditNicknameDialogFragment

    @Binds
    abstract fun bindNavigator(helper: DetailNavigateHelper): DetailNavigator

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Module
    companion object {
        @DetailFragmentScoped
        @JvmStatic
        @Provides
        fun provideDetailFragmentArguments(fragment: DetailFragment): DetailArgs {
            return DetailArgs.of(fragment.args)
        }

        @DetailFragmentScoped
        @JvmStatic
        @Provides
        fun provideBarcodeEncoder(): BarcodeEncoder = BarcodeEncoder()
    }
}
