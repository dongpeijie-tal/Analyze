package com.tal.analyze.bugle.custom.stick

/**
 * 粘性消息生命周期
 */
enum class StickLifeScope{
    VIEW,  // 跟随view生命周期
    ACTIVITY, // 跟随activity生命周期
    GLOBAL, // 全局周期
}