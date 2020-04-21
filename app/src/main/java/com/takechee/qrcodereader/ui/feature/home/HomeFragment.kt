package com.takechee.qrcodereader.ui.feature.home

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentHomeBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.takechee.qrcodereader.util.extension.simpleItemAnimatorEnabled
import com.takechee.qrcodereader.util.shortcut.ShortcutController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class HomeFragment : MainNavigationFragment(R.layout.fragment_home) {

    @Inject
    lateinit var shortcutController: ShortcutController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.openReaderButton.doOnLayout {
            binding.contentsView.updatePadding(
                bottom = binding.contentsView.paddingBottom
                        + it.marginTop + it.height + it.marginBottom
            )
        }

        setupNavigation(viewModel)

        val historyContainer = HomeHistoryContainerItem(viewModel)

        binding.contentsView.simpleItemAnimatorEnabled(false)
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.contentsView.adapter = adapter
        val initList = mutableListOf<Item<*>>()
        initList += historyContainer
        initList += HomeShortcutItem(viewModel)
        adapter.update(initList)

        viewModel.uiModel.observe(viewLifecycleOwner) { uiModel ->
            val list = mutableListOf<Item<*>>()
            list += historyContainer
            if (uiModel.shortcutGuideVisible) list += HomeShortcutItem(viewModel)
            adapter.update(list)
            historyContainer.update(uiModel.contents)
        }

        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is HomeEvent.SwitchingHistory -> {
                    navigationHost?.switchingBottomNavigationMenu(event.value)
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
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun contributeHomeFragment(): HomeFragment
}
