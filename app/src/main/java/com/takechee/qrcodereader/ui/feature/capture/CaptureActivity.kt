package com.takechee.qrcodereader.ui.feature.capture

import android.os.Bundle
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import dagger.Module

class CaptureActivity : BaseActivity() {

    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture)
    }
}

@Module
@Suppress("UNUSED")
abstract class CaptureActivityModule {

}
