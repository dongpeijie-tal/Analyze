package com.tal.analyze.bugle.custom.intercept.listening.invoke

import com.tal.analyze.bugle.custom.factory.listener.ListenerTaskFactory
import com.tal.analyze.bugle.custom.intercept.listening.base.Listener
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept

/**
 * 执行监听拦截
 */
class InvokeListeningIntercept<T>: IListeningIntercept<T> {

    override fun intercept(chain: IListeningIntercept.IListeningChain<T?>) {
        // 先检查聆听者任务是否正常运转
        ListenerTaskFactory.checkListenerTask()
        // 把聆听者添加到队列里
        val listener = chain.getListenListener() as Listener<T>
        // 准备监听了。
        listener.prepareListening()
    }
}