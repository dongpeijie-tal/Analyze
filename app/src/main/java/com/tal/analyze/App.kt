package com.tal.analyze

import android.app.Application
import com.didi.drouter.api.DRouter
import com.tal.analyze.bugle.custom.utils.GlobalAppLifecycle

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        DRouter.init(this)
        GlobalAppLifecycle.init(this)
    }
}