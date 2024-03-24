package com.tal.analyze.bugle.custom.intercept.listening.lifecycle

import android.app.Activity
import android.view.View
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
internal class ListenerLifeProxy<T>(
    private val listener: IListener<T>,
) : DefaultLifecycleObserver, IListener<T> by listener {

    private var lifecycle: Lifecycle? = null
    private var lifecycleType: IListener.LifecycleType? = null
    private var activityWeakReference: WeakReference<Activity>? = null
    private var viewWeakReference: WeakReference<View>? = null
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
        when (lifecycleType) {
            IListener.LifecycleType.CUSTOM, IListener.LifecycleType.FRAGMENT -> {
                lifecycle?.addObserver(this)
            }
            IListener.LifecycleType.ACTIVITY -> {
                // 记录activity WeakReference
                // 如果注册前就被回收了，就不注册了
                activityWeakReference!!.get()?.let {
                    GlobalAppLifecycle.registry(it).lifecycle.addObserver(this)
                }
            }
            IListener.LifecycleType.VIEW -> {
                viewWeakReference!!.get()?.let{
                    // 已经添加到window并不会有attached回调，手动执行一次
                    if(it.isAttachedToWindow){
                        register()
                    }
                    it.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                        override fun onViewAttachedToWindow(v: View) {
                            register()
                        }

                        override fun onViewDetachedFromWindow(v: View) {
                            unregister()
                        }
                    })
                }
            }
            else -> {
                // 全局不回收
            }
        }
        listener.listening()
    }

    /**
     * 生命周期注入
     */
    fun injectLifecycle(
        lifecycleType: IListener.LifecycleType,
        lifecycle: Lifecycle?,
        activity: WeakReference<Activity>?,
        viewWeakReference: WeakReference<View>?
    ) {
        this.lifecycleType = lifecycleType
        this.lifecycle = lifecycle
        this.activityWeakReference = activity
        this.viewWeakReference = viewWeakReference
    }

}