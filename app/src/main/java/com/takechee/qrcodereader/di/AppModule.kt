package com.takechee.qrcodereader.di

import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ShortcutManager
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import com.takechee.qrcodereader.MainApp
import com.takechee.qrcodereader.data.db.*
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.data.prefs.SharedPreferenceStorage
import com.takechee.qrcodereader.data.repository.ContentDataRepository
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.ui.DefaultNavigateHelper
import com.takechee.qrcodereader.ui.NavigateHelper
import com.takechee.qrcodereader.util.shortcut.DefaultShortcutController
import com.takechee.qrcodereader.util.shortcut.ShortcutController
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

}
