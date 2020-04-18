package com.takechee.qrcodereader.ui.common.base

import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.takechee.qrcodereader.ui.common.dagger.DaggerDialogFragment
import com.takechee.qrcodereader.ui.common.dagger.DaggerFragment

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