package com.takechee.qrcodereader.ui.feature.capture

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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
import permissions.dispatcher.*
import javax.inject.Inject

@RuntimePermissions
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

        showCameraWithPermissionCheck()

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


    // =============================================================================================
    //
    // Permission
    //
    // =============================================================================================
    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        barcodeView?.decodeContinuous(viewModel)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        request.proceed()
//        showRationaleDialog(R.string.permission_camera_rationale, request)
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
//        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
//        Toast.makeText(this, R.string.permission_camera_never_askagain, Toast.LENGTH_SHORT).show()
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
