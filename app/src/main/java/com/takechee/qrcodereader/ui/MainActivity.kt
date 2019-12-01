package com.takechee.qrcodereader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.ui.common.BaseActivity
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

@Module
@Suppress("UNUSED")
abstract class MainActivityModule {

}
