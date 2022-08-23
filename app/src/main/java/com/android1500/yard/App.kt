package com.android1500.yard

import android.app.Application
import com.google.android.material.color.DynamicColors

class App : Application() {

    init {
        // Load native library
        System.loadLibrary("app")
    }

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

}