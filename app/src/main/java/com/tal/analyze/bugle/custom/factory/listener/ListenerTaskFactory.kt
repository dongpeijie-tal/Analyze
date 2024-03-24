package com.tal.analyze.bugle.custom.factory.listener

import com.tal.analyze.bugle.custom.manager.BugleSchedule
import com.tal.analyze.bugle.custom.manager.ListenerManager
import com.tal.analyze.bugle.kennel.BugleManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 负责聆听者的任务正常轮转
 */
object ListenerTaskFactory {

    // 聆听任务
    private var listenJob: Job? = null

    /**
     * 检查监听任务是否正常
     */
    fun checkListenerTask() {
        // 没有工作，或者失业了，重新找一个
        if (listenJob == null || !listenJob!!.isActive) {
            listenJob = BugleSchedule.stage.launch {
                val bugleMessage = BugleManager.singleBugle().receive()
                val listeners = ListenerManager.findListeners(bugleMessage.key)
                if (listeners.isNullOrEmpty()) {
                    bugleMessage.progressCallback?.unHandler()
                } else {
                    listeners.forEach {
                        it.listening(bugleMessage)
                    }
                }
            }
        }
    }

}