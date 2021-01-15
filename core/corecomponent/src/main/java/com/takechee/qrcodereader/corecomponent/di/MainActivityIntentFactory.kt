package com.takechee.qrcodereader.corecomponent.di

import android.content.Intent

interface MainActivityIntentFactory {
    fun create(): Intent
}