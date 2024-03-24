package com.tal.analyze.bugle.custom.manager

import com.tal.analyze.bugle.custom.utils.lifeSafe


/**
 * bugle排期（bugle的生命周期）
 * 用于管理scope
 */
internal object BugleSchedule {

    // 舞台，为号手提供吹号地址
    val stage by lifeSafe {
        Schedule()
    }

    /**
     * 危险方法
     * 在你不了解框架设计的前提下，请不要随意调用此方法
     * 作用：用于休息协程
     * 基于activity为起点，让scope有机会休息，因为在一些页面并不需要使用此scope
     */
    fun stageTryRest(){
        stage.tryRest()
    }

}

