package com.takechee.qrcodereader.ui.feature.detail

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.FragmentDetailBinding
import com.takechee.qrcodereader.corecomponent.result.receiveEvent
import com.takechee.qrcodereader.ui.MainNavigationFragment
import com.takechee.qrcodereader.util.extension.simpleItemAnimatorEnabled
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

        setupNavigation(viewModel)

        val binding = FragmentDetailBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        val customTabsIntent = CustomTabsIntent.Builder().build()
        viewModel.event.receiveEvent(viewLifecycleOwner) { event ->
            when (event) {
                is DetailEvent.OpenIntent -> event.action(this)
                is DetailEvent.OpenUrl.CustomTabs -> event.action(
                    requireContext(),
                    customTabsIntent
                )
                is DetailEvent.OpenUrl.BrowserApp -> event.action(this)
                is DetailEvent.ShowEditNicknameDialog -> event.action(this)
            }
        }

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        binding.contentsView.apply {
            simpleItemAnimatorEnabled(false)
            adapter = groupAdapter
        }

        viewModel.uiModel.observe(viewLifecycleOwner) { uiModel ->
            val list = mutableListOf<Item<*>>()
            uiModel.hasQRImage { bitmap -> list += DetailViewContentQRImage(bitmap) }
            uiModel.hasContent { content, text ->
                list += DetailViewContentText(text.value)
                list += when (text) {
                    is DetailUiModel.ContentText.Url ->
                        DetailViewContentActionArea.UrlAction(viewModel)
                    is DetailUiModel.ContentText.Text ->
                        DetailViewContentActionArea.TextAction(viewModel)
                    is DetailUiModel.ContentText.Specified ->
                        DetailViewContentActionArea.SpecifiedAction(viewModel)
                }
                list += DetailViewContentEditNickname(content.nickname.value, viewModel)
                list += DetailViewFavorite(uiModel.content.isFavorite, viewModel)
            }
            groupAdapter.update(list)
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_delete -> viewModel.onDeleteClick()
                else -> return@setOnMenuItemClickListener false
            }
            return@setOnMenuItemClickListener true
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
