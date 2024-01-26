package com.tal.analyze

import android.app.Application
import com.didi.drouter.api.DRouter

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        DRouter.init(this)
    }
}