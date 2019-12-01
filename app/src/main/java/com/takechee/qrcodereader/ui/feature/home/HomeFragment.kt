package com.takechee.qrcodereader.ui.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.takechee.qrcodereader.ui.CustomCaptureActivity
import com.takechee.qrcodereader.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val intentIntegrator: IntentIntegrator by lazy {
        IntentIntegrator.forSupportFragment(this).apply {
            captureActivity = CustomCaptureActivity::class.java
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        intentIntegrator.initiateScan()

        binding.openReaderButton.setOnClickListener {
            intentIntegrator.initiateScan()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)) {
            null -> super.onActivityResult(requestCode, resultCode, data)
            else -> when (val contents = result.contents) {
                null -> Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show()
                else -> {
                    val intent = Intent(Intent.ACTION_VIEW, contents.toUri())
                    startActivity(intent)
                }
            }
        }
    }
}