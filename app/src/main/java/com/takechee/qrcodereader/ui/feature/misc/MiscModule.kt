package com.takechee.qrcodereader.ui.feature.misc

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class MiscModule {
    @MiscListPageScoped
    @ContributesAndroidInjector(modules = [MiscListFragmentModule::class])
    internal abstract fun contributeMiscListFragment(): MiscListFragment
}
