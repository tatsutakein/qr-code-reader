package com.takechee.qrcodereader.ui.feature.detail

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.SimpleItemAnimator
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentDetailBinding
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class DetailFragment : MainNavigationFragment(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    internal val args: DetailFragmentArgs by navArgs()


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        val customTabsIntent = CustomTabsIntent.Builder().build()
        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is DetailEvent.OpenIntent -> event.action(this)
                is DetailEvent.OpenUrl -> event.action(requireContext(), customTabsIntent)
            }
        }

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        binding.contentsView.apply {
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            adapter = groupAdapter
        }

        viewModel.uiModel.observe(viewLifecycleOwner) { uiModel ->
            val list = mutableListOf<Item<*>>()
            uiModel.hasQRImage { bitmap -> list += DetailViewContent.QRImage(bitmap) }
            uiModel.hasTitle { title -> list += DetailViewContent.Title(title) }
            uiModel.hasText { text -> list += DetailViewContent.Text(text) }
            uiModel.whenText(
                textAction = { list += DetailViewContent.ActionArea.TextAction(viewModel) },
                urlAction = { list += DetailViewContent.ActionArea.UrlAction(viewModel) },
                specifiedAction = { list += DetailViewContent.ActionArea.SpecifiedAction(viewModel) }
            )
            groupAdapter.update(list)
        }
    }
}


@Module
@Suppress("UNUSED")
abstract class DetailModule {
    @DetailFragmentScoped
    @ContributesAndroidInjector(modules = [DetailFragmentModule::class])
    internal abstract fun contributeDetailFragment(): DetailFragment
}
