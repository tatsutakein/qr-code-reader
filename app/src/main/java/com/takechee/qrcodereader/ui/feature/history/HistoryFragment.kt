package com.takechee.qrcodereader.ui.feature.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.databinding.FragmentHistoryBinding
import com.takechee.qrcodereader.databinding.FragmentHomeBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class HistoryFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val historyViewModel: HistoryViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false).apply {
            viewModel = historyViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewLifecycleOwner.lifecycle.addObserver(historyViewModel)

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.historyListView.adapter = adapter

        historyViewModel.urls.observe(viewLifecycleOwner) { urls ->
            val list = mutableListOf<Item<*>>()
            urls.mapTo(list) { url -> HistoryItem(url, historyViewModel) }
            adapter.update(list)
        }

        historyViewModel.navigateTo.receiveEvent(viewLifecycleOwner) { directions ->
            findNavController().navigate(directions)
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
abstract class HistoryModule {
    @HistoryPageScoped
    @ContributesAndroidInjector(modules = [HistoryFragmentModule::class])
    internal abstract fun contributeHistoryFragment(): HistoryFragment
}
