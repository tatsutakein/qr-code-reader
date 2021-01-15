package com.takechee.qrcodereader.legacy.ui.feature.capture

import android.os.Bundle
import android.view.View
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseActivity
import dagger.Module

class DirectCaptureActivity : BaseActivity(R.layout.activity_direct_capture) {
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
abstract class DirectCaptureActivityModule
