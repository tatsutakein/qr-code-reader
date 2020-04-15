package com.takechee.qrcodereader.ui.feature.capture

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentCaptureBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import permissions.dispatcher.ktx.withPermissionsCheck
import javax.inject.Inject

class CaptureFragment : MainNavigationFragment(R.layout.fragment_capture) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaptureViewModel by viewModels { viewModelFactory }

    private var barcodeView: DecoratedBarcodeView? = null


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCaptureBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        setupNavigation(viewModel)

        barcodeView = binding.zxingBarcodeScanner

        withPermissionsCheck(Manifest.permission.CAMERA,
            onShowRationale = { request ->
                request.proceed()
            },
            onPermissionDenied = {

            },
            onNeverAskAgain = {

            },
            requiresPermission = {
                binding.zxingBarcodeScanner.decodeContinuous(viewModel)
            }
        )

        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is CaptureEvent.OpenDetail -> startActivity(event.intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateScreen(ScreenType.FULLSCREEN)
        barcodeView?.resume()
    }

    override fun onPause() {
        barcodeView?.pause()
        updateScreen(ScreenType.NORMAL)
        super.onPause()
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun updateScreen(type: ScreenType) {
        val window = activity?.window ?: return

        when (type) {
            ScreenType.FULLSCREEN -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
            ScreenType.NORMAL -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }
    }

    private enum class ScreenType {
        FULLSCREEN,
        NORMAL
    }
}


// =================================================================================================
//
// Module
//
// =================================================================================================
@Module
@Suppress("UNUSED")
abstract class CaptureModule {
    @CapturePageScoped
    @ContributesAndroidInjector(modules = [CaptureFragmentModule::class])
    internal abstract fun contributeCaptureFragment(): CaptureFragment
}
