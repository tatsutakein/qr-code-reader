package com.takechee.qrcodereader.misc.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class MiscModule {
    @MiscPageScoped
    @ContributesAndroidInjector(modules = [MiscFragmentModule::class])
    abstract fun contributeMiscFragment(): MiscFragment
}
