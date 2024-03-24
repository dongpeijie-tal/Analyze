package com.tal.analyze.bugle.custom.intercept.listening.base

import com.tal.analyze.bugle.custom.open.DispatchThread
import com.tal.analyze.bugle.kennel.BugleMessage

interface IContentListener<T>{
    val key: String
    val onThread : DispatchThread
    val listen : (T?)->Unit
    val autoCancel : ((T?)->Boolean)?
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

    /**
     * 准备监听
     */
    fun prepareListening()

    /**
     * 监听到的内容
     * 这里用挂起的原因是为了条件反注册
     */
    suspend fun listening(bugleMessage: BugleMessage)

    enum class LifecycleType{
        CUSTOM,
        VIEW,
        FRAGMENT,
        ACTIVITY,
        APPLICATION,
    }
}
