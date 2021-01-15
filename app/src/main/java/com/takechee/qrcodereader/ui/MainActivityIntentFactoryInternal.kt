package com.takechee.qrcodereader.ui

import android.content.Context
import android.content.Intent
import com.takechee.qrcodereader.corecomponent.di.MainActivityIntentFactory
import javax.inject.Inject

internal class MainActivityIntentFactoryInternal @Inject constructor(
    private val context: Context,
) : MainActivityIntentFactory {
    override fun create(): Intent = Intent(context, MainActivity::class.java)
}