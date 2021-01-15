package com.takechee.qrcodereader.legacy.ui.feature.detail

import androidx.lifecycle.LiveData
import com.takechee.qrcodereader.corecomponent.result.Event

interface DetailUserEventListener : DetailViewContentEventListener {
    fun onEditNicknamePositiveClick(nickname: String)
    fun onGetTitleByUrlClick(): LiveData<Event<String>>
    fun onDeleteClick()
}