package com.takechee.qrcodereader

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentIntegrator = IntentIntegrator(this).apply {
            captureActivity = CustomCaptureActivity::class.java
        }

        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)) {
            null -> super.onActivityResult(requestCode, resultCode, data)
            else -> when (val contents = result.contents) {
                null -> Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                else -> {
                    val intent = Intent(Intent.ACTION_VIEW, contents.toUri())
                    startActivity(intent)
                }
            }
        }
    }
}
