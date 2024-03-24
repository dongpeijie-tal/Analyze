package com.tal.analyze.bugle.custom.intercept.listening.base

interface IContentListener{

}

internal interface IListener : IContentListener {
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
