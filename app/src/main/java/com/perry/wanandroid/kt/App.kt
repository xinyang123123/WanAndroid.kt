package com.perry.wanandroid.kt

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import kotlin.properties.Delegates

class App : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        Utils.init(this)
    }

}