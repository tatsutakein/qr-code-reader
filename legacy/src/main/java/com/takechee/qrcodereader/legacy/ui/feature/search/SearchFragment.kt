package com.takechee.qrcodereader.legacy.ui.feature.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.legacy.databinding.FragmentSearchBinding
import com.takechee.qrcodereader.legacy.ui.MainNavigationFragment
import com.takechee.qrcodereader.legacy.util.extension.setOnQueryTextListener
import com.takechee.qrcodereader.legacy.util.extension.simpleItemAnimatorEnabled
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class SearchFragment : MainNavigationFragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private var searchView: SearchView? = null


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        setupNavigation(viewModel)

        searchView = binding.searchView.apply {
            setOnQueryTextListener(
                onQueryTextSubmit = {
                    dismissKeyboard(this@apply)
                    true
                },
                onQueryTextChange = { newText ->
                    viewModel.onSearchQueryChanged(newText)
                    true
                }
            )

            // Set focus on the SearchView and open the keyboard
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    showKeyboard(view.findFocus())
                }
            }
            requestFocus()
        }

        val section = SearchSection(viewModel)
        binding.searchResultView.apply {
            adapter = GroupAdapter<GroupieViewHolder>().apply { add(section) }
            simpleItemAnimatorEnabled(false)
        }

        viewModel.contents.observe(viewLifecycleOwner) { contents -> section.update(contents) }
    }

    override fun onPause() {
        searchView?.let { view -> dismissKeyboard(view) }
        super.onPause()
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    private fun dismissKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


// =================================================================================================
//
// Module
//
// =================================================================================================
@Module
@Suppress("UNUSED")
abstract class SearchModule {
    @SearchPageScoped
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    internal abstract fun contributeSearchFragment(): SearchFragment
}
