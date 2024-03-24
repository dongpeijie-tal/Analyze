package com.tal.analyze.bugle.custom.intercept.listening.base

import com.tal.analyze.bugle.custom.manager.ListenerManager
import com.tal.analyze.bugle.custom.open.DispatchThread

/**
 * 聆听者聆听实现
 */
internal class Listener<T>(private val key: String, private val onThread : DispatchThread, private val listen : (T?)->Unit):
    IListener {
    override fun register() {
//        ListenerManager.addListener(this)
    }

    override fun unregister() {
//        ListenerManager.removeListener(this)
    }

    /**
     * 检测channel的receive是否启动
     */
    override fun listening() {
//        BugleSchedule.stage.launch {
//            val receive = BugleManager.singleBugle().receive()
//            try {
//                listen(receive.message as T?)
//            }catch (e: ClassCastException){
//                // TODO 打log
//            }
//        }
    }

}