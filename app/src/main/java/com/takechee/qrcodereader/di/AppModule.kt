package com.takechee.qrcodereader.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.takechee.qrcodereader.MainApp
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.data.prefs.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }
}
