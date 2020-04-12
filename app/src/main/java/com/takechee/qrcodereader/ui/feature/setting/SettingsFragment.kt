package com.takechee.qrcodereader.ui.feature.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentSettingsBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.takechee.qrcodereader.util.extension.setUpGroupAdapter
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class SettingsFragment : MainNavigationFragment(R.layout.fragment_settings) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SettingsViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSettingsBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        val groupAdapter = binding.settingListView.setUpGroupAdapter()
        viewModel.settingBinders.observe(viewLifecycleOwner) { settingBinders ->
            val list = mutableListOf<Item<*>>()
            settingBinders.mapTo(list) { binder ->
                when (binder) {
                    is SettingBooleanBinder -> SettingsBooleanItem(binder, viewModel)
                }
            }
            groupAdapter.update(list)
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
abstract class SettingsModule {
    @SettingsPageScoped
    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    internal abstract fun contributeSettingsFragment(): SettingsFragment
}
