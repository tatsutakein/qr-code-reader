package com.takechee.qrcodereader.legacy.ui.feature.capture

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.legacy.databinding.FragmentCaptureBinding
import com.takechee.qrcodereader.corecomponent.result.receiveEvent
import com.takechee.qrcodereader.legacy.ui.MainNavigationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import permissions.dispatcher.*
import javax.inject.Inject

@RuntimePermissions
class CaptureFragment : MainNavigationFragment(R.layout.fragment_capture) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CaptureViewModel by viewModels { viewModelFactory }

    private var barcodeView: DecoratedBarcodeView? = null

    private var hasPermission = false


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

        hasPermission = PermissionUtils.hasSelfPermissions(
            requireActivity(),
            Manifest.permission.CAMERA
        )
        showCameraWithPermissionCheck()

        binding.zxingBarcodeScanner.setOnApplyWindowInsetsListener { scanner, insets ->
            scanner.updatePadding(
                left = insets.systemWindowInsetLeft,
                right = insets.systemWindowInsetRight,
                bottom = insets.systemWindowInsetBottom,
            )
            return@setOnApplyWindowInsetsListener insets
        }

        binding.toolbar.setOnApplyWindowInsetsListener { toolbar, insets ->
            toolbar.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.systemWindowInsetTop
            }
            return@setOnApplyWindowInsetsListener insets
        }

        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is CaptureEvent.OpenDetail -> startActivity(event.intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (hasPermission) barcodeView?.resume()
    }

    override fun onPause() {
        if (hasPermission) barcodeView?.pause()
        super.onPause()
    }


    // =============================================================================================
    //
    // Permission
    //
    // =============================================================================================
    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        hasPermission = true
        barcodeView?.decodeSingle(viewModel)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(requestCode, grantResults)
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
