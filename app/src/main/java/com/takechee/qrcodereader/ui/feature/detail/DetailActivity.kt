package com.takechee.qrcodereader.ui.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.ui.common.base.BaseActivity
import dagger.Binds
import dagger.Module
import javax.inject.Inject

private const val INTENT_PARAM_URL = "URL"

class DetailActivity : BaseActivity(R.layout.activity_detail) {

    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val url = intent.getStringExtra(INTENT_PARAM_URL) ?: return
            findNavHostFragment().navController.setGraph(
                R.navigation.nav_graph_detail,
                DetailFragmentArgs(url).toBundle()
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