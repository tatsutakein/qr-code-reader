package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.model.CapturedCode
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import javax.inject.Inject

class HomeHistorySection @Inject constructor(
    private val eventListener: HomeEventListener
) : Section() {
    fun update(captures: List<CapturedCode>) {
        val list = mutableListOf<Item<*>>()
        captures.mapTo(list) { captured -> HomeHistoryItem(captured, eventListener) }
        update(list)
    }

    companion object {
        private const val DISPLAY_LIMIT_COUNT = 3
    }
}