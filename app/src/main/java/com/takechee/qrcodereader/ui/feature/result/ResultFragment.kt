package com.takechee.qrcodereader.ui.feature.result

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs
import com.takechee.qrcodereader.databinding.FragmentResultBinding
import com.takechee.qrcodereader.ui.common.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

class ResultFragment : BaseFragment() {

    private lateinit var binding: FragmentResultBinding

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.resultUrl = args.url
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intent = Intent(Intent.ACTION_VIEW, args.url.toUri())

        binding.openUrl.setOnClickListener {
            startActivity(intent)
        }
    }
}


@Module
@Suppress("UNUSED")
abstract class ResultModule {

    //    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeResultFragment(): ResultFragment

}
