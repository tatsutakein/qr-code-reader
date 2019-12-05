package com.takechee.qrcodereader.ui.feature.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.databinding.FragmentSettingsBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.takechee.qrcodereader.util.extension.setUpGroupAdapter
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val settingsViewModel: SettingsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false).apply {
            viewModel = settingsViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewLifecycleOwner.lifecycle.addObserver(settingsViewModel)

        val groupAdapter = binding.settingListView.setUpGroupAdapter()
        settingsViewModel.settingBinders.observe(viewLifecycleOwner) { settingBinders ->
            val list = mutableListOf<Item<*>>()
            settingBinders.mapTo(list) { binder ->
                when (binder) {
                    is SettingBooleanBinder -> SettingsBooleanItem(binder, settingsViewModel)
                }
            }
            groupAdapter.update(list)
        }

        settingsViewModel.navigateTo.receiveEvent(viewLifecycleOwner) { directions ->
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
abstract class SettingsModule {
    @SettingsPageScoped
    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    internal abstract fun contributeSettingsFragment(): SettingsFragment
}
