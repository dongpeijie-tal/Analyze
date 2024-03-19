package com.tal.analyze.bugle.custom.manager

import com.tal.analyze.bugle.custom.utils.lifeSafe


/**
 * bugle排期（生命周期）
 * 用于管理所有的bugle
 * 创建bugle条件：
 * 1、强行创建单独的bugle（一些任务及时性高，数据量大，需要单独的号手处理）
 * 2、全局bugle（用于当下什么都拿不到，但是还是要监听号角的情况）
 * 3、自动协调bugle（普通任务，只是为了方便通信）
 *
 */
internal object BugleSchedule {
    // 舞台，为号手提供吹号地址
    val stage by lifeSafe {
        Schedule()
    }

}

