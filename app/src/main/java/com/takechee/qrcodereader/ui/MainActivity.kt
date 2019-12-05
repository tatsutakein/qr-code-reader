package com.takechee.qrcodereader.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ActivityMainBinding
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import com.takechee.qrcodereader.ui.feature.home.HomeFragmentDirections
import dagger.Module

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        
        binding.navigation.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_settings -> {
                    findNavController(R.id.nav_host_fragment).navigate(HomeFragmentDirections.toSettings())
                }
                R.id.navigation_oss_licenses -> {
                    findNavController(R.id.nav_host_fragment).navigate(HomeFragmentDirections.toOssLicenses())
                }
                else -> return@setNavigationItemSelectedListener false
            }
            return@setNavigationItemSelectedListener true
        }
    }

}

@Module
@Suppress("UNUSED")
abstract class MainActivityModule {

}
