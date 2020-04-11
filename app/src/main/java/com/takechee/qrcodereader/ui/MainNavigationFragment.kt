package com.takechee.qrcodereader.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*

abstract class MainNavigationFragment : BaseFragment {

    // =============================================================================================
    //
    // Constructor
    //
    // =============================================================================================
    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)


    // =============================================================================================
    //
    // NavHost
    //
    // =============================================================================================
    protected var navigationHost: NavigationHost? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationHost = context as? NavigationHost
    }

    override fun onDetach() {
        navigationHost = null
        super.onDetach()
    }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = navigationHost ?: return
        view.findViewById<Toolbar>(R.id.toolbar)?.also { toolbar ->
            navHost.registerToolbarWithNavigation(toolbar)
        }
    }


    // =============================================================================================
    //
    // Setup
    //
    // =============================================================================================
    protected fun setupNavigation(navigator: Navigator) {
        navigator.navDirections.receiveEvent(viewLifecycleOwner) { navDirections ->
            try {
                findNavController().navigate(navDirections)
            } catch (ignore: IllegalArgumentException) {
                // unknown to this NavController
            }
        }
    }
}


// =============================================================================================
//
// NavHost
//
// =============================================================================================
interface NavigationHost {
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}


// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface Navigator {
    val navDirections: LiveData<Event<NavDirections>>
}

interface NavigateHelper : Navigator {
    fun navigateTo(factory: () -> NavDirections)
}

class DefaultNavigateHelper : NavigateHelper {
    private val _navDirections = MutableLiveData<Event<NavDirections>>()
    override val navDirections: LiveData<Event<NavDirections>>
        get() = _navDirections

    override fun navigateTo(factory: () -> NavDirections) {
        _navDirections.fireEvent(factory)
    }
}