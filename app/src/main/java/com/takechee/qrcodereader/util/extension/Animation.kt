package com.takechee.qrcodereader.util.extension

import android.view.animation.Animation

inline fun Animation.setAnimationListener(
    crossinline onAnimationRepeat: (anim: Animation?) -> Unit = {},
    crossinline onAnimationStart: (anim: Animation?) -> Unit = {},
    crossinline onAnimationEnd: (anim: Animation?) -> Unit = {}
): Animation.AnimationListener {
    val wrappedListener = object : Animation.AnimationListener {
        override fun onAnimationRepeat(anim: Animation?) = onAnimationRepeat.invoke(anim)
        override fun onAnimationStart(anim: Animation?) = onAnimationStart.invoke(anim)
        override fun onAnimationEnd(anim: Animation?) = onAnimationEnd.invoke(anim)
    }
    setAnimationListener(wrappedListener)
    return wrappedListener
}