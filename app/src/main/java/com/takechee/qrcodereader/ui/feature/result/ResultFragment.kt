package com.takechee.qrcodereader.ui.feature.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.takechee.qrcodereader.databinding.FragmentResultBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class ResultFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val resultViewModel: ResultViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentResultBinding

    internal val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false).apply {
            viewModel = resultViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        resultViewModel.showIntent.receiveEvent(viewLifecycleOwner) { intent ->
            startActivity(intent)
        }
    }
}


@Module
@Suppress("UNUSED")
abstract class ResultModule {
    @ResultFragmentScoped
    @ContributesAndroidInjector(modules = [ResultFragmentModule::class])
    internal abstract fun contributeResultFragment(): ResultFragment
}
