package com.takechee.qrcodereader.ui.feature.result

import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class ResultFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    abstract fun bindResultViewModel(viewModel: ResultViewModel): ViewModel

    @Module
    companion object {
        @ResultFragmentScoped
        @JvmStatic
        @Provides
        fun provideResultFragmentArguments(fragment: ResultFragment): ResultFragmentArguments {
            return ResultFragmentArguments.of(fragment.args)
        }
    }
}
