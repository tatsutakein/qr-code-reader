package com.takechee.qrcodereader.ui.feature.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentHistoryBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HistoryViewModel by viewModels { viewModelFactory }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHistoryBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.historyListView.adapter = adapter

        viewModel.urls.observe(viewLifecycleOwner) { urls ->
            val list = mutableListOf<Item<*>>()
            urls.mapTo(list) { url -> HistoryItem(url, viewModel) }
            adapter.update(list)
        }

        viewModel.navigateTo.receiveEvent(viewLifecycleOwner) { directions ->
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
