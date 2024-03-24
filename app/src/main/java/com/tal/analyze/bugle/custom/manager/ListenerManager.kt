package com.tal.analyze.bugle.custom.manager

import com.tal.analyze.bugle.custom.intercept.listening.base.IListener

/**
 * 倾听者管理
 */
internal object ListenerManager {

    // 所有的倾听者
    private val listeners = hashMapOf<String, MutableList<IListener>>()

    fun removeAll(key: String) = listeners.remove(key)

    fun findListener(){

    }
}