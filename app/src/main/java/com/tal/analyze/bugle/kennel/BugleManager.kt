package com.tal.analyze.bugle.kennel

import java.util.concurrent.ConcurrentHashMap

/**
 * 号角管理员
 */
internal object BugleManager {

    private val bugleMap = ConcurrentHashMap<String,Bugle>()

    fun tryRest(){

    }

    /**
     * 查找是否创建过bugle
     */
    fun findBugle(key: String): Bugle {
        return bugleMap.getOrPut(key) { Bugle() }.apply {
            if (!isActive()) {
                // 如果Bugle不是活跃的，创建一个新的Bugle实例替换旧的
                bugleMap[key] = Bugle()
            }
        }
    }
}