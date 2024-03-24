package com.tal.analyze.bugle.custom.intercept.listening.base

import com.tal.analyze.bugle.custom.manager.ListenerManager
import com.tal.analyze.bugle.custom.open.DispatchThread
import com.tal.analyze.bugle.kennel.BugleMessage

/**
 * 聆听者聆听实现
 */
internal class Listener<T>(
    override val key: String,
    override val onThread: DispatchThread,
    override val listen: (T?) -> Unit,
    override val autoCancel : ((T?)->Boolean)?,
): IListener<T> {
    override fun register() {
        ListenerManager.addListener(this)
    }

    override fun unregister() {
        ListenerManager.removeListener(this)
    }

    /**
     * 没有什么需要准备的操作
     */
    override fun prepareListening() {

    }

    override suspend fun listening(bugleMessage: BugleMessage) {
        try{
            val message = bugleMessage.message as T?
            autoCancel?.let {
                // 条件反注册
                if(it.invoke(message)){
                    unregister()
                }
            }
            // 回调 todo 可以拿到拦截器继续做拦截，目前没有需求，暂时不做
            listen.invoke(message)
        }catch (e: ClassCastException){
            bugleMessage.progressCallback?.onFail(e)
        }
    }

}