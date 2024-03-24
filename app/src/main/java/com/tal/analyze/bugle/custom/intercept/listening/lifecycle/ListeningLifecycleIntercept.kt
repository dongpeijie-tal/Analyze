package com.tal.analyze.bugle.custom.intercept.listening.lifecycle

import android.app.Activity
import android.app.Service
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.tal.analyze.bugle.custom.intercept.listening.base.IListener
import com.tal.analyze.bugle.custom.intercept.listening.base.Listener
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept
import com.tal.analyze.bugle.custom.utils.unpack
import java.lang.ref.WeakReference

/**
 * 对于执行的监听者进行生命周期包装
 * support fragment 决定不支持，太早了，支持意义不大
 */
class ListeningLifecycleIntercept<T>(
    private val view: View?,
    private val fragment: Fragment?,
    private val context: Context?,
    private val lifecycle: Lifecycle?
) : IListeningIntercept<T> {
    // 注入的生命周期
    private var injectLifecycle : Lifecycle? = null
    // 针对没有lifecycle的activity
    private var activityWeakReference : WeakReference<Activity>? = null
    private var viewWeakReference : WeakReference<View>? = null
    // 什么都没有传，是全局
    private var lifecycleType = IListener.LifecycleType.APPLICATION
    override fun intercept(chain: IListeningIntercept.IListeningChain<T?>) {
        if(lifecycle != null){
            lifecycleType = IListener.LifecycleType.CUSTOM
            injectLifecycle = lifecycle
        }else if(fragment != null){
            lifecycleType = IListener.LifecycleType.FRAGMENT
            injectLifecycle = fragment.lifecycle
        }else if(context != null){
            unpackContext(context)
        }else if(view != null){
            lifecycleType = IListener.LifecycleType.VIEW
            viewWeakReference = WeakReference(view)
        }
        val content = chain.getListenListener()
        // 对消息进行包装，等待消息加入消息监听再启动生命周期
        val proxy = ListenerLifeProxy(content as Listener<T?>).apply {
            injectLifecycle(lifecycleType,injectLifecycle,activityWeakReference,viewWeakReference)
        }
        chain.process(proxy)
    }

    /**
     * 解包context并进行参数注入
     */
    private fun unpackContext(context: Context){
        when (val safeContext = context.unpack()) {
            // 这里处理了lifeService，以及非activity等情况
            is LifecycleOwner -> {
                // 能拿到lifecycle，优先判断
                lifecycleType = IListener.LifecycleType.CUSTOM
                injectLifecycle = safeContext.lifecycle
            }
            is Activity -> {
                // 记录下来，在application的切换中，做移除监听
                lifecycleType = IListener.LifecycleType.ACTIVITY
                activityWeakReference = WeakReference(safeContext)
            }
            is Service -> {
                // 服务目前没有办法支持
                throw RuntimeException("service 目前不支持自动释放，请手动释放")
            }
            else -> {
                throw RuntimeException("抱歉，无法识别的Context。")
            }
        }
    }

}