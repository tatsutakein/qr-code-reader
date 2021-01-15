package com.takechee.qrcodereader

import com.jakewharton.threetenabp.AndroidThreeTen
import com.takechee.qrcodereader.di.DaggerAppComponent
import com.takechee.qrcodereader.legacy.util.shortcut.ShortcutController
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MainApp : DaggerApplication() {

    @Inject
    lateinit var shortcutController: ShortcutController

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        AndroidThreeTen.init(this)
        super.onCreate()

        shortcutController.upsertShortcuts()
    }
}