package com.takechee.qrcodereader.util.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <T> MediatorLiveData<T>.addSources(
    vararg sources: LiveData<*>,
    crossinline onChanged: () -> Unit
) {
    sources.forEach { source ->
        addSource(source) { onChanged.invoke() }
    }
}