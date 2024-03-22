package com.tal.analyze.bugle.custom.intercept.listening.lifecycle

internal interface IListener {
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
