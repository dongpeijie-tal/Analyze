package com.tal.analyze.bugle.custom.factory.trumpeter

import com.tal.analyze.bugle.custom.factory.trumpeter.impl.NormalTrumpeterImpl

/**
 * 号手工厂
 */
object TrumpeterFactory {

    /**
     * 目前没有额外的需求
     * 譬如：单例化号手，任务队列化号手，排序化号手，条件化号手
     * 普通号手，每个号手仅执行自己被告知的任务。
     */
    fun create(type: TrumpeterType): ITrumpeterFactory{
        return when(type){
            TrumpeterType.NORMAL->NormalTrumpeterImpl()
            TrumpeterType.ORDERLY-> NormalTrumpeterImpl()
        }
    }

    enum class TrumpeterType{
        NORMAL,// 普通
        ORDERLY,// 有序
    }
}