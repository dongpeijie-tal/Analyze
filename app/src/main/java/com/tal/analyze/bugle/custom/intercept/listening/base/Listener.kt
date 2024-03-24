package com.tal.analyze.bugle.custom.intercept.listening.base

import com.tal.analyze.bugle.custom.manager.ListenerManager
import com.tal.analyze.bugle.custom.open.DispatchThread

/**
 * 聆听者聆听实现
 */
internal class Listener<T>(
    override val key: String,
    override val onThread: DispatchThread,
    override val listen: (T?) -> Unit
): IListener<T> {
    override fun register() {
        ListenerManager.addListener(this)
    }

    override fun unregister() {
        ListenerManager.removeListener(this)
    }

    /**
     * 检测channel的receive是否启动
     */
    override fun listening() {

    }

}