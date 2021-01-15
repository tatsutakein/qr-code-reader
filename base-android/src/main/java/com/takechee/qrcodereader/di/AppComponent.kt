package com.takechee.qrcodereader.di

import com.takechee.qrcodereader.MainApp
import com.takechee.qrcodereader.corecomponent.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApp): AppComponent
    }
}
