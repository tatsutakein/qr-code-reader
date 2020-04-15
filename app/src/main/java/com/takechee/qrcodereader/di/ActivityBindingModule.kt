package com.takechee.qrcodereader.di

import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.MainActivityModule
import com.takechee.qrcodereader.ui.feature.capture.CaptureActivity
import com.takechee.qrcodereader.ui.feature.capture.CaptureActivityModule
import com.takechee.qrcodereader.ui.feature.capture.CaptureModule
import com.takechee.qrcodereader.ui.feature.detail.DetailActivity
import com.takechee.qrcodereader.ui.feature.detail.DetailActivityIntentFactoryModule
import com.takechee.qrcodereader.ui.feature.detail.DetailActivityModule
import com.takechee.qrcodereader.ui.feature.history.HistoryModule
import com.takechee.qrcodereader.ui.feature.home.HomeModule
import com.takechee.qrcodereader.ui.feature.detail.DetailModule
import com.takechee.qrcodereader.ui.feature.misc.MiscModule
import com.takechee.qrcodereader.ui.feature.setting.SettingsModule
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
            DetailActivityIntentFactoryModule::class,
            // fragments
            HomeModule::class,
            CaptureModule::class,
            HistoryModule::class,
            DetailModule::class,
            SettingsModule::class,
            MiscModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            CaptureActivityModule::class,
            DetailActivityIntentFactoryModule::class,
            // fragments
            CaptureModule::class
        ]
    )
    internal abstract fun captureActivity(): CaptureActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            DetailActivityModule::class,
            // fragments
            DetailModule::class
        ]
    )
    internal abstract fun detailActivity(): DetailActivity
}
