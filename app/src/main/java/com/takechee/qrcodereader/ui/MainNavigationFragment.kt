package com.takechee.qrcodereader.ui

import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.takechee.qrcodereader.result.receiveEvent
import com.takechee.qrcodereader.ui.common.base.BaseFragment
import com.takechee.qrcodereader.ui.common.navigation.Navigator

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