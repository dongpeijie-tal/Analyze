package com.tal.analyze.bugle.custom.factory.listener

import com.tal.analyze.bugle.custom.debug.lod
import com.tal.analyze.bugle.custom.factory.trumpeter.impl.OrderlyTrumpeterImpl
import com.tal.analyze.bugle.custom.manager.BugleSchedule
import com.tal.analyze.bugle.custom.manager.ListenerManager
import com.tal.analyze.bugle.kennel.BugleManager

/**
 * 负责聆听者的任务正常轮转
 */
object ListenerTaskFactory {

    /**
     * 启动一个监听任务
     */
    fun checkListenerTask(key: String, isOrderly: Boolean) {
        lod("key为:$key 的bugle准备启动listener协程")
        val job = BugleSchedule.stage.launch(OrderlyTrumpeterImpl.singleThreadContext) {
            lod("key为:$key 的bugle启动listener协程")
            val bugleMessage = BugleManager.findBugle(key).receive()
            lod("key为:$key 的bugle收到消息==>${bugleMessage.key}")
            val listeners = ListenerManager.findListeners(bugleMessage.key)
            if (listeners.isNullOrEmpty()) {
                bugleMessage.progressCallback?.unHandler()
            } else {
                listeners.forEach {
                    it.listening(bugleMessage)
                }
            }
        }
        job.invokeOnCompletion {
            lod("key为:$key 的bugle的listener协程 执行完毕，关闭,包含错误信息==>${it?.message}")
        }
    }

}