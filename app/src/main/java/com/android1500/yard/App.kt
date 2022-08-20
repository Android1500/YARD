package com.android1500.yard

import android.app.Application

class App : Application() {
    init {
        System.loadLibrary("app")
    }


}