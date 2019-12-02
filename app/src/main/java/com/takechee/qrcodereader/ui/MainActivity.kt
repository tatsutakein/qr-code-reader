package com.takechee.qrcodereader.ui

import android.os.Bundle
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import dagger.Module

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
