package com.tal.analyze.bugle.custom.intercept.listening

import android.app.Activity
import android.app.Service
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.tal.analyze.bugle.custom.intercept.listening.lifecycle.IListener
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept
import com.tal.analyze.bugle.custom.utils.unpack

/**
 * 对于执行的监听者进行生命周期包装
 */
class ListeningLifecycleIntercept<T>(
    private val fragment: Fragment?,
    private val context: Context?,
    private val lifecycle: Lifecycle?
) : IListeningIntercept<T> {

    override fun intercept(chain: IListeningIntercept.IListeningChain<T?>) {
        val content = chain.getListenContent()
        var injectLifecycle : Lifecycle? = null
        var lifecycleType = IListener.LifecycleType.APPLICATION
        if(lifecycle != null){
            lifecycleType = IListener.LifecycleType.CUSTOM
            injectLifecycle = lifecycle
        }else if(fragment != null){
            lifecycleType = IListener.LifecycleType.FRAGMENT
            injectLifecycle = fragment.lifecycle
        }else if(context != null){
            when (val safeContext = context.unpack()) {
                is LifecycleOwner -> {
                    // 能拿到lifecycle，优先判断
                    lifecycleType = IListener.LifecycleType.CUSTOM
                    injectLifecycle = safeContext.lifecycle
                }
                is Activity -> {
                    // 记录下来，在application的切换中，做移除监听
                    lifecycleType = IListener.LifecycleType.ACTIVITY
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
//        val proxy = ListenerLifeProxy(listener).apply {
//            injectLifecycle(lifecycleType,injectLifecycle)
//        }
        // 自动view注册反注册，最后还是要看它的context获取对应的生命周期
//        view?.let{
//            it.addOnAttachStateChangeListener(object: View.OnAttachStateChangeListener{
//                override fun onViewAttachedToWindow(v: View) {
//
//                }
//
//                override fun onViewDetachedFromWindow(v: View) {
//
//                }
//            })
//            context
//        }
        // 对消息进行包装，等待消息加入消息监听再启动生命周期
    }

}