package com.takechee.qrcodereader.ui.feature.capture

import android.os.Bundle
import android.view.View
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import dagger.Module

class CaptureActivity : BaseActivity(R.layout.activity_capture) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(R.id.capture_fragment_container).apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    // **レイアウトの領域をStatusBarとNavigationBarの領域も含むようにする**
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
    }
}

@Module
@Suppress("UNUSED")
abstract class CaptureActivityModule
