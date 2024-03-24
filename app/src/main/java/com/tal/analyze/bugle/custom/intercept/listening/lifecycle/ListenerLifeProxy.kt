package com.tal.analyze.bugle.custom.intercept.listening.lifecycle

import android.app.Activity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.tal.analyze.bugle.custom.intercept.listening.base.IListener
import com.tal.analyze.bugle.custom.utils.GlobalAppLifecycle
import java.lang.ref.WeakReference

/**
 * 生命周期包裹的聆听者代理
 * 负责处理事件的回收操作
 */
internal class ListenerLifeProxy(private val listener: IListener): DefaultLifecycleObserver,
    IListener {
    private var lifecycle : Lifecycle? = null
    private var lifecycleType : IListener.LifecycleType? = null
    private var activity : WeakReference<Activity>? = null
    override fun onCreate(owner: LifecycleOwner) {
        register()
        super.onCreate(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        unregister()
        lifecycle?.removeObserver(this)
        super.onDestroy(owner)
    }

    override fun register() {
        listener.register()
    }

    override fun unregister() {
        listener.unregister()
    }

    override fun listening() {
        when(lifecycleType){
            IListener.LifecycleType.CUSTOM, IListener.LifecycleType.FRAGMENT->{
                lifecycle?.addObserver(this)
            }
            IListener.LifecycleType.ACTIVITY->{
                // 记录activity WeakReference
                // 如果注册前就被回收了，就不注册了
                activity!!.get()?.let {
                    GlobalAppLifecycle.registry(it).lifecycle.addObserver(this)
                }
            }
            else->{
                // 全局不回收
            }
        }
        listener.listening()

    }

    fun injectLifecycle(lifecycleType: IListener.LifecycleType, lifecycle: Lifecycle?,activity: WeakReference<Activity>?){
        this.lifecycleType = lifecycleType
        this.lifecycle = lifecycle
        this.activity = activity
    }

}