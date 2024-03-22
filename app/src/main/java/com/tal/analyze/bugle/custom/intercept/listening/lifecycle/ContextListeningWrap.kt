package com.tal.analyze.bugle.custom.intercept.listening.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.tal.analyze.bugle.custom.open.DispatchThread

internal class ListenerLifeProxy(private val listener: IListener): DefaultLifecycleObserver,IListener{
    private var lifecycle : Lifecycle? = null
    override fun onCreate(owner: LifecycleOwner) {
        register()
        super.onCreate(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        unregister()
        super.onDestroy(owner)
    }

    override fun register() {
        listener.register()
    }

    override fun unregister() {
        listener.register()
    }

    override fun listening() {
        lifecycle?.addObserver(this)
        listener.listening()
    }

    fun injectLifecycle(lifecycleType: IListener.LifecycleType, lifecycle: Lifecycle?){
        this.lifecycle = lifecycle
    }

}

internal class Listener(private val key: String,private val onThread : DispatchThread,private val listen : (Any?)->Unit): IListener{
    override fun register() {

    }


    override fun unregister() {

    }

    /**
     * 执行注入的聆听方法
     */
    override fun listening() {

    }


}