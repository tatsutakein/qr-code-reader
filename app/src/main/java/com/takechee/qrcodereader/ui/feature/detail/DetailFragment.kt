package com.takechee.qrcodereader.ui.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.doOnLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.takechee.qrcodereader.databinding.FragmentDetailBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject
import kotlin.math.min

class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val resultViewModel: DetailViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentDetailBinding

    internal val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            viewModel = resultViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.qrCodeImageView.doOnLayout {
            val requestSize = min(it.width, it.height)
            resultViewModel.onQRImageViewLayout(requestSize)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        resultViewModel.showIntent.receiveEvent(viewLifecycleOwner) { intent ->
            startActivity(intent)
        }

        val customTabsIntent = CustomTabsIntent.Builder().build()
        resultViewModel.startUri.receiveEvent(viewLifecycleOwner) { uri ->
            customTabsIntent.launchUrl(requireActivity(), uri)
        }

        resultViewModel.qrImage.observe(viewLifecycleOwner) { bitmap ->
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
