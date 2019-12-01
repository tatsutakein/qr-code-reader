package com.takechee.qrcodereader.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.takechee.qrcodereader.MainApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApp): Context {
        return application.applicationContext
    }

    @Provides
    fun providesConnectivityManager(context: Context): ConnectivityManager = requireNotNull(
        context.applicationContext.getSystemService()
    )
}
