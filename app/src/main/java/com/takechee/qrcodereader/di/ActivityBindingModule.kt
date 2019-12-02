package com.takechee.qrcodereader.di

import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.MainActivityModule
import com.takechee.qrcodereader.ui.feature.home.HomeModule
import com.takechee.qrcodereader.ui.feature.result.ResultModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            MainActivityModule::class,
            // fragments
            HomeModule::class,
            ResultModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity
}
