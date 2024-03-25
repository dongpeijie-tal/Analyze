package com.tal.analyze.bugle.custom.intercept.listening.invoke

import com.tal.analyze.bugle.custom.intercept.listening.base.IListener
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept

/**
 * 执行监听拦截
 */
class InvokeListeningIntercept<T>: IListeningIntercept<T> {

    override fun intercept(chain: IListeningIntercept.IListeningChain<T?>) {
        // 准备监听了。
        val listener = chain.getListenListener() as IListener<T>
        listener.prepareListening()
    }
}