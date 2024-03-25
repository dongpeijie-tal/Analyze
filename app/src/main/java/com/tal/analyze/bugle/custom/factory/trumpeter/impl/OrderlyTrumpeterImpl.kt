package com.tal.analyze.bugle.custom.factory.trumpeter.impl

import com.tal.analyze.bugle.custom.debug.lod
import com.tal.analyze.bugle.custom.factory.trumpeter.ITrumpeterFactory
import com.tal.analyze.bugle.custom.manager.BugleSchedule
import com.tal.analyze.bugle.custom.open.intercept.PuffContent
import com.tal.analyze.bugle.kennel.Bugle
import com.tal.analyze.bugle.kennel.BugleManager
import com.tal.analyze.bugle.kennel.BugleMessage
import kotlinx.coroutines.newSingleThreadContext

/**
 * 有序号手
 */
object OrderlyTrumpeterImpl : ITrumpeterFactory {

    val singleThreadContext = newSingleThreadContext("MySingleThread")
    override fun puff(content: PuffContent<Any?>) {
        // 准备号角
        val bugle = prepareBugle(content.key)
        val music = BugleMessage(content.key,content.msg,content.progressCallback)
        // 搭建舞台（scope）
        lod("key为:${content.key} ,value:${content.msg} 的bugle准备发送消息")
        BugleSchedule.stage.launch(singleThreadContext) {
            // 吹奏
            lod("key为:${content.key} ,value:${content.msg} 的bugle发送消息")
            bugle.send(music){
                // 吹的时候，发现号角坏了，去换一个新的继续
                prepareBugle(content.key).send(music){
                    // 新的不会是坏的，如果还不能用，框架有问题了
                    throw RuntimeException("请检查bugle创建，尝试创建新的bugle无法发送消息。")
                }
            }
            lod("key为:${content.key} ,value:${content.msg} 的bugle发送完毕")
        }
    }

    /**
     * 准备号角
     */
    private fun prepareBugle(key: String):Bugle{
        return BugleManager.findBugle(key)
    }

}