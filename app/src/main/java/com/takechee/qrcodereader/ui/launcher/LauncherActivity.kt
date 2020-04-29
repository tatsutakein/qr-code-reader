package com.takechee.qrcodereader.ui.launcher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.takechee.qrcodereader.di.ViewModelKey
import com.takechee.qrcodereader.corecomponent.result.receiveEvent
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

class LauncherActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LaunchViewModel by viewModels { viewModelFactory }


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.event.receiveEvent(this) { event ->
            when (event) {
                is LauncherEvent.Destination -> {
                    startActivity(event.intent)
                    finish()
                }
            }
        }
    }
}


@Module
@Suppress("UNUSED")
abstract class LauncherActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    abstract fun bindLaunchViewModel(viewModel: LaunchViewModel): ViewModel
}
