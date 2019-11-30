package com.takechee.qrcodereader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentIntegrator = IntentIntegrator(this).apply {
            captureActivity = CustomCaptureActivity::class.java
        }

        intentIntegrator.initiateScan()
    }
}
