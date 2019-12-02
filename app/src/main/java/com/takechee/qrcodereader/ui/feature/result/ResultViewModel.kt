package com.takechee.qrcodereader.ui.feature.result

import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    @ResultFragmentScoped private val args: ResultFragmentArguments
) : BaseViewModel() {

    val url: LiveData<String> = MutableLiveData(args.url)

    private val _showIntent = MutableLiveData<Event<Intent>>()
    val showIntent: LiveData<Event<Intent>> = _showIntent.distinctUntilChanged()

    fun onOpenUrlClick() {
        _showIntent.fireEvent { Intent(Intent.ACTION_VIEW, args.url.toUri()) }
    }
}