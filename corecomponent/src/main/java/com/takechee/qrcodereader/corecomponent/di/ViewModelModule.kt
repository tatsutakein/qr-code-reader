package com.takechee.qrcodereader.corecomponent.di

import androidx.lifecycle.ViewModelProvider
import com.takechee.qrcodereader.corecomponent.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}