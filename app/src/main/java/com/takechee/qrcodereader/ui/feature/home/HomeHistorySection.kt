package com.takechee.qrcodereader.ui.feature.home

import com.xwray.groupie.Item
import com.xwray.groupie.Section
import javax.inject.Inject

class HomeHistorySection @Inject constructor(
    private val eventListener: HomeEventListener
) : Section() {
    fun update(urls: List<String>) {
        val list = mutableListOf<Item<*>>()
        urls.mapTo(list) { url -> HomeHistoryItem(url, eventListener) }
        update(list)
    }

    companion object {
        private const val DISPLAY_LIMIT_COUNT = 3
    }
}