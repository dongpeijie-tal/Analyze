package com.tal.analyze.bugle.custom.stick

/**
 * 粘性消息格式
 */
data class StickMessage<T>(val key: String,val lifeScope: StickLifeScope,val msg:T?)
