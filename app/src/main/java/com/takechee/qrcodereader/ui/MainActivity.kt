package com.takechee.qrcodereader.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseActivity
import com.takechee.qrcodereader.util.HeightTopWindowInsetsListener
import com.takechee.qrcodereader.util.NoopWindowInsetsListener
import com.takechee.qrcodereader.util.extension.setupWithNavController
import dagger.Module
import dev.chrisbanes.insetter.doOnApplyWindowInsets

class MainActivity : BaseActivity(R.layout.activity_main), NavigationHost {

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_home,
            R.id.navigation_history,
            R.id.navigation_misc,
            R.id.navigation_settings
        )
    }

    private var currentNavController: LiveData<NavController>? = null

    private lateinit var contentContainer: ViewGroup
    private lateinit var bottomNavigationView: BottomNavigationView


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentContainer = findViewById<ViewGroup>(R.id.container).also { container ->
            container.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    // ** レイアウトの領域をStatusBarとNavigationBarの領域も含むようにする **
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

            // Make the content ViewGroup ignore insets so that it does not use the default padding
            container.setOnApplyWindowInsetsListener(NoopWindowInsetsListener)
        }
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation).also {
            it.doOnApplyWindowInsets { view, insets, initialState ->
                view.updatePadding(
                    left = initialState.paddings.left + insets.systemWindowInsetLeft,
                    right = initialState.paddings.right + insets.systemWindowInsetRight,
                    bottom = initialState.paddings.bottom + insets.systemWindowInsetBottom
                )
            }
        }

        findViewById<View>(R.id.status_bar_scrim)
            .setOnApplyWindowInsetsListener(HeightTopWindowInsetsListener)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }


    // =============================================================================================
    //
    // NavigationHost
    //
    // =============================================================================================
    override fun switchingBottomNavigationMenu(menu: NavigationHost.BottomNavigationMenu) {
        bottomNavigationView.selectedItemId = menu.resId
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)
        currentNavController?.value?.let { navController ->
            toolbar.setupWithNavController(navController, appBarConfiguration)
        }
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val navGraphIds = listOf(
            R.navigation.nav_graph_home,
            R.navigation.nav_graph_history,
            R.navigation.nav_graph_misc
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the destination changed listener.
        controller.observe(this) { navController ->
            navController.addOnDestinationChangedListener { _, destination, _ ->
                TransitionManager.beginDelayedTransition(
                    contentContainer,
                    BottomNavigationViewTransition
                )
                val isTopLevelDestination = TOP_LEVEL_DESTINATIONS.contains(destination.id)
                bottomNavigationView.isVisible = isTopLevelDestination
            }
        }
        currentNavController = controller
    }


    // =============================================================================================
    //
    // Class
    //
    // =============================================================================================
    private object BottomNavigationViewTransition : Slide(Gravity.BOTTOM) {
        init {
            excludeTarget(R.id.nav_host_fragment, true)
            interpolator = AccelerateDecelerateInterpolator()
        }
    }
}

@Module
@Suppress("UNUSED")
abstract class MainActivityModule {

}
