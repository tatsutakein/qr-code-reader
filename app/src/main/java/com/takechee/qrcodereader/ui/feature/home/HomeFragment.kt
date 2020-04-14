package com.takechee.qrcodereader.ui.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentHomeBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class HomeFragment : MainNavigationFragment(R.layout.fragment_home) {

    @Inject
    lateinit var intentIntegrator: IntentIntegrator

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

        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        setupNavigation(viewModel)

        val historySection = HomeHistorySection(viewModel)

        val adapter = GroupAdapter<GroupieViewHolder>()
        binding.contentsView.adapter = adapter
        val list = mutableListOf<Item<*>>()
        list.add(HomeHistoryContainerItem(historySection, viewModel))
        adapter.update(list)
        viewModel.urls.observe(viewLifecycleOwner) { historySection.update(it) }

        viewModel.openReader.receiveEvent(viewLifecycleOwner) {
            intentIntegrator.initiateScan()
        }

        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is HomeEvent.SwitchingHistory -> {
                    navigationHost?.switchingBottomNavigationMenu(event.value)
                }
                is HomeEvent.OpenDetail -> {
                    startActivity(event.intent)
                }
            }
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
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun contributeHomeFragment(): HomeFragment
}
