package com.takechee.qrcodereader.util.extension

import android.widget.SearchView

fun SearchView.setOnQueryTextListener(
    onQueryTextSubmit: (query: String) -> Boolean = { false },
    onQueryTextChange: (newText: String) -> Boolean = { false }
): SearchView.OnQueryTextListener {
    val wrappedOnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean = onQueryTextSubmit.invoke(query)
        override fun onQueryTextChange(newText: String): Boolean = onQueryTextChange.invoke(newText)
    }
    setOnQueryTextListener(wrappedOnQueryTextListener)
    return wrappedOnQueryTextListener
}