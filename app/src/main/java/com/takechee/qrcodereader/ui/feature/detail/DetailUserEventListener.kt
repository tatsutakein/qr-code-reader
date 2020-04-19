package com.takechee.qrcodereader.ui.feature.detail

import androidx.lifecycle.LiveData
import com.takechee.qrcodereader.result.Event

interface DetailUserEventListener : DetailViewContentEventListener {
    fun onEditNicknamePositiveClick(nickname: String)
    fun onGetTitleByUrlClick(): LiveData<Event<String>>
}