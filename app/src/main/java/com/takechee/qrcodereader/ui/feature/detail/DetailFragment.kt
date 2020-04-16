package com.takechee.qrcodereader.ui.feature.detail

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.doOnLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentDetailBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject
import kotlin.math.min

class DetailFragment : MainNavigationFragment(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    internal val args: DetailFragmentArgs by navArgs()


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        binding.qrCodeImageView.doOnLayout {
            val requestSize = min(it.width, it.height)
            viewModel.onQRImageViewLayout(requestSize)
        }

        val customTabsIntent = CustomTabsIntent.Builder().build()
        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is DetailEvent.OpenIntent -> event.action(this)
                is DetailEvent.OpenUrl -> event.action(requireContext(), customTabsIntent)
            }
        }

        viewModel.qrImage.observe(viewLifecycleOwner) { bitmap ->
            bitmap?.let {
                binding.qrCodeImageView.setImageBitmap(it)
            }
        }
    }
}


@Module
@Suppress("UNUSED")
abstract class DetailModule {
    @DetailFragmentScoped
    @ContributesAndroidInjector(modules = [DetailFragmentModule::class])
    internal abstract fun contributeDetailFragment(): DetailFragment
}
