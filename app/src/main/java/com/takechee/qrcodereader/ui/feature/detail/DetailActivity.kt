package com.takechee.qrcodereader.ui.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ActivityMainBinding
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import com.takechee.qrcodereader.ui.feature.result.ResultFragmentArgs
import com.takechee.qrcodereader.util.extension.setupWithNavController
import dagger.Binds
import dagger.Module
import javax.inject.Inject

private const val INTENT_PARAM_URL = "URL"

class DetailActivity : BaseActivity() {

    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val navHostFragment = findNavHostFragment()

        if (savedInstanceState == null) {
            val url = intent.getStringExtra(INTENT_PARAM_URL) ?: return
            navHostFragment.navController.setGraph(
                R.navigation.nav_graph_detail,
                ResultFragmentArgs(url).toBundle()
            )
        }
    }

    private fun findNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}

@Module
@Suppress("UNUSED")
abstract class DetailActivityModule {

}

// =============================================================================================
//
// IntentFactory
//
// =============================================================================================
@Module
internal abstract class DetailActivityIntentFactoryModule {
    @Binds
    abstract fun bindFactory(impl: DetailActivityIntentFactoryImpl): DetailActivityIntentFactory
}

interface DetailActivityIntentFactory {
    fun create(
        url: String,
        action: Intent.() -> Unit = {}
    ): Intent
}

internal class DetailActivityIntentFactoryImpl @Inject constructor(
    private val context: Context
) : DetailActivityIntentFactory {
    override fun create(url: String, action: Intent.() -> Unit): Intent {
        return Intent(context, DetailActivity::class.java)
            .putExtra(INTENT_PARAM_URL, url)
            .apply(action)
    }
}