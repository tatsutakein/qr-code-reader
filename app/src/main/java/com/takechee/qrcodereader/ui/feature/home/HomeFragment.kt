package com.takechee.qrcodereader.ui.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
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

        setupNavigation(viewModel)

        val historyContainer = HomeHistoryContainerItem(viewModel)

        binding.contentsView.simpleItemAnimatorEnabled(false)
        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.contentsView.adapter = adapter
        val list = mutableListOf<Item<*>>()
        list += historyContainer
        list += HomeShortcutItem(viewModel)
        adapter.update(list)
        viewModel.contents.observe(viewLifecycleOwner) {
            historyContainer.update(it)
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
