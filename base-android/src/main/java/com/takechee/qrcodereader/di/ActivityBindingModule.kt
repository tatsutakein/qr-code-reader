package com.takechee.qrcodereader.di

import com.takechee.qrcodereader.corecomponent.di.ActivityScoped
import com.takechee.qrcodereader.misc.ui.MiscModule
import com.takechee.qrcodereader.legacy.ui.launcher.LauncherActivity
import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.MainActivityModule
import com.takechee.qrcodereader.legacy.ui.feature.capture.CaptureModule
import com.takechee.qrcodereader.legacy.ui.feature.capture.DirectCaptureActivity
import com.takechee.qrcodereader.legacy.ui.feature.capture.DirectCaptureActivityModule
import com.takechee.qrcodereader.legacy.ui.feature.detail.DetailModule
import com.takechee.qrcodereader.legacy.ui.feature.history.HistoryModule
import com.takechee.qrcodereader.legacy.ui.feature.home.HomeModule
import com.takechee.qrcodereader.legacy.ui.feature.onboading.OnboadingActivity
import com.takechee.qrcodereader.legacy.ui.feature.onboading.OnboadingActivityModule
import com.takechee.qrcodereader.legacy.ui.feature.search.SearchModule
import com.takechee.qrcodereader.legacy.ui.launcher.LauncherActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            LauncherActivityModule::class
        ]
    )
    internal abstract fun launcherActivity(): LauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            OnboadingActivityModule::class
        ]
    )
    internal abstract fun onboadingActivity(): OnboadingActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            MainActivityModule::class,
            // fragments
            HomeModule::class,
            CaptureModule::class,
            HistoryModule::class,
            SearchModule::class,
            DetailModule::class,
            MiscModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            // activity
            DirectCaptureActivityModule::class,
            // fragments
            CaptureModule::class,
            DetailModule::class
        ]
    )
    internal abstract fun directCaptureActivity(): DirectCaptureActivity
}
