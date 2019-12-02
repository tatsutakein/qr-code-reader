package com.takechee.qrcodereader.ui.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.takechee.qrcodereader.ui.CustomCaptureActivity
import com.takechee.qrcodereader.databinding.FragmentHomeBinding
import com.takechee.qrcodereader.di.ViewModelKey
import com.takechee.qrcodereader.ui.common.BaseFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

class HomeFragment : BaseFragment() {

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
                    findNavController().navigate(HomeFragmentDirections.toResult(contents))
                }
            }
        }
    }
}


// =================================================================================================
//
// Module
//
// =================================================================================================
@Module
@Suppress("UNUSED")
abstract class HomeModule {

    @HomePageScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMainViewModel(viewModel: HomeViewModel): ViewModel
}
