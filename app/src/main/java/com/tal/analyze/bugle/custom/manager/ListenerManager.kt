package com.tal.analyze.bugle.custom.manager

import com.tal.analyze.bugle.custom.intercept.listening.base.IListener
import com.tal.analyze.bugle.custom.intercept.listening.base.Listener

/**
 * 倾听者管理
 */
internal object ListenerManager {

    // 所有的倾听者
    private val listeners = hashMapOf<String, MutableList<IListener<*>>>()

    /**
     * 清空某个key的所有监听
     */
    fun removeAll(key: String) = listeners.remove(key)

    /**
     * 清空所有监听
     */
    fun clear() = listeners.clear()

    /**
     * 添加监听
     */
    fun <T> addListener(listener: Listener<T>) {
        val list = listeners.getOrDefault(listener.key, mutableListOf())
        if(!list.contains(listener)){
            list.add(listener)
        }
    }

    /**
     * 移除监听
     */
    fun <T> removeListener(listener: Listener<T>) {
        listeners[listener.key]?.remove(listener)
    }
}