package com.takechee.qrcodereader.corecomponent.ui.common.base

import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.takechee.qrcodereader.corecomponent.ui.common.dagger.DaggerDialogFragment

abstract class BaseDialogFragment : DaggerDialogFragment {

    // =============================================================================================
    //
    // Constructor
    //
    // =============================================================================================
    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

}