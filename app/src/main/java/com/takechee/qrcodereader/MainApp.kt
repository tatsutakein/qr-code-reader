package com.takechee.qrcodereader

import com.jakewharton.threetenabp.AndroidThreeTen
import com.takechee.qrcodereader.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        AndroidThreeTen.init(this)
        super.onCreate()
    }
}