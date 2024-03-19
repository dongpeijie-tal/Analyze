package com.tal.analyze.bugle.kennel

import com.tal.analyze.bugle.custom.utils.lifeSafe

/**
 * 号角管理员
 */
internal object BugleManager {
    // 单一号角，重复使用
    private val singleBugle by lifeSafe {
        Bugle<Any?>()
    }

    /**
     * 单个号角
     */
    fun getSingleBugle() = singleBugle
}