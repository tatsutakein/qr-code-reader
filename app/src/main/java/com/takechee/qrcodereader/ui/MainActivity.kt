package com.takechee.qrcodereader.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ActivityMainBinding
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import com.takechee.qrcodereader.ui.feature.home.HomeFragmentDirections
import dagger.Module

class MainActivity : BaseActivity(), NavigationHost {

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_home
        )
    }

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController ?: findNavController(R.id.nav_host_fragment).also {
            _navController = it
        }

    private var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        drawer = binding.drawer

        binding.navigation.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_settings -> {
                    navController.navigate(HomeFragmentDirections.toSettings())
                }
                R.id.navigation_oss_licenses -> {
                    navController.navigate(HomeFragmentDirections.toOssLicenses())
                }
                else -> return@setNavigationItemSelectedListener false
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS, drawer)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}

@Module
@Suppress("UNUSED")
abstract class MainActivityModule {

}
