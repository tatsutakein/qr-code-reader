package com.takechee.qrcodereader.misc.ui

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseFragment
import com.takechee.qrcodereader.misc.R
import com.takechee.qrcodereader.corecomponent.result.receiveEvent
import com.takechee.qrcodereader.misc.databinding.FragmentMiscBinding
import javax.inject.Inject

class MiscFragment : BaseFragment(R.layout.fragment_misc) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MiscViewModel by viewModels { viewModelFactory }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMiscBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        val customTabsIntent = CustomTabsIntent.Builder().build()
        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is MiscEvent.OpenUrl -> event.action(requireContext(), customTabsIntent)
                is MiscEvent.OpenLicenses -> event.action(fragment = this)
            }
        }
    }
}