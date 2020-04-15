package com.takechee.qrcodereader.ui.feature.misc

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentHistoryBinding
import com.takechee.qrcodereader.databinding.FragmentMiscListBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class MiscListFragment : BaseFragment(R.layout.fragment_misc_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MiscListViewModel by viewModels { viewModelFactory }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMiscListBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        viewModel.navigateTo.receiveEvent(viewLifecycleOwner) { directions ->
            findNavController().navigate(directions)
        }

        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is MiscListEvent.OpenLicenses -> event.action(fragment = this)
            }
        }
    }
}