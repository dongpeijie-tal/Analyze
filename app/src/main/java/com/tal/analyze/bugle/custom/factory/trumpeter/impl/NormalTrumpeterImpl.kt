package com.tal.analyze.bugle.custom.factory.trumpeter.impl

import com.tal.analyze.bugle.custom.factory.trumpeter.ITrumpeterFactory
import com.tal.analyze.bugle.custom.manager.BugleSchedule
import com.tal.analyze.bugle.custom.open.PuffContent
import com.tal.analyze.bugle.kennel.Bugle
import com.tal.analyze.bugle.kennel.BugleManager
import com.tal.analyze.bugle.kennel.BugleMessage

/**
 * 通用号手
 */
class NormalTrumpeterImpl: ITrumpeterFactory {

    override fun puff(content: PuffContent<Any?>) {
        // 准备号角
        val bugle = prepareBugle()
        val music = BugleMessage(content.key,content.msg,content.progressCallback)
        // 搭建舞台（scope）
        BugleSchedule.stage.launch {
            // 吹奏
            bugle.send(music){
                // 吹的时候，发现号角坏了，去换一个新的继续
                prepareBugle().send(music){
                    // 新的不会是坏的，如果还不能用，框架有问题了
                    throw RuntimeException("请检查bugle创建，尝试创建新的bugle无法发送消息。")
                }
            }
        }
    }

    /**
     * 准备号角
     */
    private fun prepareBugle():Bugle{
        return BugleManager.getSingleBugle()
    }

}