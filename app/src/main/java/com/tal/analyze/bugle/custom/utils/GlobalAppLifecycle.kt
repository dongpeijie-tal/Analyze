package com.tal.analyze.bugle.custom.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

internal object GlobalAppLifecycle{
    // 注入application
    lateinit var app : Application

    fun registry(observerActivity: Activity): CustomActivityLifecycleOwner{
        val owner = CustomActivityLifecycleOwner()
        val lifecycleRegistry = owner.lifecycleRegistry
        app.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if(observerActivity == activity){
                    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
                }
            }

            override fun onActivityStarted(activity: Activity) {
                if(observerActivity == activity) {
                    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
                }
            }

            override fun onActivityResumed(activity: Activity) {
                if(observerActivity == activity) {
                    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
                }
            }

            override fun onActivityPaused(activity: Activity) {
                if(observerActivity == activity) {
                    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
                }
            }

            override fun onActivityStopped(activity: Activity) {
                if(observerActivity == activity) {
                    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                if(observerActivity == activity) {
                    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                }
            }
        })
        return owner
    }
}

/**
 * 自定义activity生命周期
 */
internal class CustomActivityLifecycleOwner : LifecycleOwner{

    val lifecycleRegistry = LifecycleRegistry(this)

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

}