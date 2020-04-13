package com.takechee.qrcodereader.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ActivityMainBinding
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import com.takechee.qrcodereader.util.extension.setupWithNavController
import dagger.Module

class MainActivity : BaseActivity(), NavigationHost {

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_home,
            R.id.navigation_history,
            R.id.navigation_settings
        )
    }

    private var _navHost: NavHostFragment? = null
    private val navHost: NavHostFragment
        get() = _navHost ?: findNavHostFragment().also {
            _navHost = it
        }

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController ?: navHost.navController.also {
            _navController = it
        }

    private var currentNavController: LiveData<NavController>? = null

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

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

    override fun switchingBottomNavigationMenu(bottomNavigationMenu: NavigationHost.BottomNavigationMenu) {
        binding.bottomNavigation.selectedItemId = bottomNavigationMenu.resId
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
//        val appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)
//        currentNavController?.value?.let { navController ->
//            toolbar.setupWithNavController(navController, appBarConfiguration)
//        }
    }

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

        currentNavController = controller
    }

    private fun findNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}

@Module
@Suppress("UNUSED")
abstract class MainActivityModule {

}
