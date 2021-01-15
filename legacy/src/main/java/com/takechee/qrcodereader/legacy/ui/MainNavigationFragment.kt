package com.takechee.qrcodereader.legacy.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.ContentView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.corecomponent.result.Event
import com.takechee.qrcodereader.corecomponent.result.fireEvent
import com.takechee.qrcodereader.corecomponent.result.receiveEvent
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseFragment
import javax.inject.Inject

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
        navigator.navPopBack.receiveEvent(viewLifecycleOwner) {
            try {
                findNavController().popBackStack()
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

    enum class BottomNavigationMenu(@IdRes val resId: Int) {
        HOME(R.id.nav_graph_home),
        HISTORY(R.id.nav_graph_history),
        MISC(R.id.nav_graph_misc)
    }

    fun switchingBottomNavigationMenu(menu: BottomNavigationMenu)

    fun registerToolbarWithNavigation(toolbar: Toolbar)
}


// =============================================================================================
//
// Navigator
//
// =============================================================================================
interface Navigator {
    val navDirections: LiveData<Event<NavDirections>>
    val navPopBack: LiveData<Event<Unit>>
}

interface NavigateHelper : Navigator {
    fun navigateTo(factory: () -> NavDirections)
    fun navigatePopBack()
}

class DefaultNavigateHelper @Inject constructor() : NavigateHelper {
    private val _navDirections = MutableLiveData<Event<NavDirections>>()
    override val navDirections: LiveData<Event<NavDirections>>
        get() = _navDirections.distinctUntilChanged()

    private val _navPopBack = MutableLiveData<Event<Unit>>()
    override val navPopBack: LiveData<Event<Unit>>
        get() = _navPopBack.distinctUntilChanged()

    override fun navigateTo(factory: () -> NavDirections) {
        _navDirections.fireEvent(factory)
    }

    override fun navigatePopBack() {
        _navPopBack.fireEvent()
    }
}