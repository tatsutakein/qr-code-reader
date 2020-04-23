package com.takechee.qrcodereader.ui.feature.misc

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentMiscListBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
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

        val customTabsIntent = CustomTabsIntent.Builder().build()
        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is MiscListEvent.OpenUrl -> event.action(requireContext(), customTabsIntent)
                is MiscListEvent.OpenLicenses -> event.action(fragment = this)
            }
        }
    }
}