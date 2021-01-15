package com.takechee.qrcodereader.di

import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ShortcutManager
import androidx.core.content.getSystemService
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.takechee.qrcodereader.BuildConfig
import com.takechee.qrcodereader.MainApp
import com.takechee.qrcodereader.corecomponent.EnvVar
import com.takechee.qrcodereader.legacy.data.db.AppDatabase
import com.takechee.qrcodereader.legacy.data.db.ContentDatabase
import com.takechee.qrcodereader.legacy.data.db.ContentRoomDatabase
import com.takechee.qrcodereader.corecomponent.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.corecomponent.di.MainActivityIntentFactory
import com.takechee.qrcodereader.legacy.data.prefs.SharedPreferenceStorage
import com.takechee.qrcodereader.legacy.data.repository.ContentDataRepository
import com.takechee.qrcodereader.legacy.data.repository.ContentRepository
import com.takechee.qrcodereader.legacy.ui.DefaultNavigateHelper
import com.takechee.qrcodereader.legacy.ui.NavigateHelper
import com.takechee.qrcodereader.legacy.util.shortcut.DefaultShortcutController
import com.takechee.qrcodereader.legacy.util.shortcut.ShortcutController
import com.takechee.qrcodereader.ui.MainActivityIntentFactoryInternal
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
    fun providesInAppUpdateManager(context: Context): AppUpdateManager {
        return AppUpdateManagerFactory.create(context)
    }

    @Provides
    fun provideClipboardManager(context: Context): ClipboardManager = requireNotNull(
        context.applicationContext.getSystemService()
    )

    @Provides
    fun provideShortcutManager(context: Context): ShortcutManager = requireNotNull(
        context.applicationContext.getSystemService()
    )

    @Provides
    fun provideShortcutController(context: Context): ShortcutController {
        return DefaultShortcutController(context)
    }

    @Singleton
    @Provides
    fun providePreferenceStorage(context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }

    @Singleton
    @Provides
    fun provideEnvVar(): EnvVar = object : EnvVar {
        override val DEBUG: Boolean = BuildConfig.DEBUG
        override val APPLICATION_ID: String = BuildConfig.APPLICATION_ID
        override val BUILD_TYPE: String = BuildConfig.BUILD_TYPE
        override val VERSION_CODE: Int = BuildConfig.VERSION_CODE
        override val VERSION_NAME: String = BuildConfig.VERSION_NAME
        override val GIT_COMMIT_HASH: String = BuildConfig.GIT_COMMIT_HASH
    }

    @Provides
    fun provideNavigateHelper(): NavigateHelper = DefaultNavigateHelper()

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.buildDatabase(context)

    @Singleton
    @Provides
    fun provideContentDatabase(database: AppDatabase): ContentDatabase {
        return ContentRoomDatabase(database, database.contentDao())
    }

    @Singleton
    @Provides
    fun provideContentRepository(dataSource: ContentDataRepository): ContentRepository = dataSource

    @Singleton
    @Provides
    fun provideMainActivityIntentFactory(context: Context): MainActivityIntentFactory {
        return MainActivityIntentFactoryInternal(context = context)
    }
}
