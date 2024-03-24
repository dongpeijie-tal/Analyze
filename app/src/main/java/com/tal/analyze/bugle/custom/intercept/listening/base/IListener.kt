package com.tal.analyze.bugle.custom.intercept.listening.base

import com.tal.analyze.bugle.custom.open.DispatchThread

interface IContentListener<T>{
    val key: String
    val onThread : DispatchThread
    val listen : (T?)->Unit
}

internal interface IListener<T> : IContentListener<T> {
    /**
     * 注册
     */
    fun register()
    /**
     * 反注册
     */
    fun unregister()

    fun listening()

    enum class LifecycleType{
        CUSTOM,
        VIEW,
        FRAGMENT,
        ACTIVITY,
        APPLICATION,
    }
}
