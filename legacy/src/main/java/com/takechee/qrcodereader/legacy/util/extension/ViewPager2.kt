package com.takechee.qrcodereader.legacy.util.extension

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.registerOnPageChangeCallback(
    onPageScrolled: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit = { _, _, _ -> },
    onPageScrollStateChanged: (state: Int) -> Unit = {},
    onPageSelected: (position: Int) -> Unit = {}
): ViewPager2.OnPageChangeCallback {
    val wrappedOnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            onPageScrolled.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged.invoke(state)
        }

        override fun onPageSelected(position: Int) {
            onPageSelected.invoke(position)
        }
    }
    registerOnPageChangeCallback(wrappedOnPageChangeCallback)
    return wrappedOnPageChangeCallback
}