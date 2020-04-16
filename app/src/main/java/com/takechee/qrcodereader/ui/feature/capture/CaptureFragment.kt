package com.takechee.qrcodereader.ui.feature.capture

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentCaptureBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.chrisbanes.insetter.doOnApplyWindowInsets
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

        binding.zxingBarcodeScanner.doOnApplyWindowInsets { scanner, insets, initialState ->
            scanner.updatePadding(
                left = initialState.paddings.left + insets.systemWindowInsetLeft,
//                top = initialState.paddings.top + insets.systemWindowInsetTop,
                right = initialState.paddings.right + insets.systemWindowInsetRight,
                bottom = initialState.paddings.bottom + insets.systemWindowInsetBottom
            )
        }

        binding.toolbar.doOnApplyWindowInsets { toolbar, insets, initialState ->
            toolbar.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = initialState.margins.top + insets.systemWindowInsetTop
            }
        }

        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is CaptureEvent.OpenDetail -> startActivity(event.intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView?.resume()
    }

    override fun onPause() {
        barcodeView?.pause()
        super.onPause()
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
