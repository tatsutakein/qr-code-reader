package com.takechee.qrcodereader.ui.feature.history

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentHistoryBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.takechee.qrcodereader.util.extension.simpleItemAnimatorEnabled
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class HistoryFragment : MainNavigationFragment(R.layout.fragment_history) {

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

        setupNavigation(viewModel)

        binding.filterToggleFab.doOnLayout {
            binding.historyListView.updatePadding(
                bottom = binding.historyListView.paddingBottom
                        + it.marginTop + it.height + it.marginBottom
            )
        }

        val section = HistorySection(viewModel)
        binding.historyListView.apply {
            adapter = GroupAdapter<GroupieViewHolder>().apply { add(section) }
            simpleItemAnimatorEnabled(false)
        }

        viewModel.contents.observe(viewLifecycleOwner) { contents -> section.update(contents) }
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
