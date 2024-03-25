package com.tal.analyze.bugle.custom.utils

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.tal.analyze.bugle.custom.manager.BugleSchedule
import com.tal.analyze.bugle.kennel.BugleManager

internal object GlobalAppLifecycle{
    // 注入application
    private lateinit var app : Application

    fun init(app: Application){
        this.app = app
        app.registerActivityLifecycleCallbacks(object: ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                // 每当有activity销毁，尝试让stage\bugle休息
                BugleSchedule.stageTryRest()
                BugleManager.tryRest()
            }
        })
    }

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