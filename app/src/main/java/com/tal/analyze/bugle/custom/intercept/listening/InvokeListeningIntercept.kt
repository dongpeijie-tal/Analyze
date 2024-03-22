package com.tal.analyze.bugle.custom.intercept.listening

import com.tal.analyze.bugle.custom.factory.listener.ListenFactory
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept

/**
 * 执行监听拦截
 */
class InvokeListeningIntercept<T>: IListeningIntercept<T> {

    override fun intercept(chain: IListeningIntercept.IListeningChain<T?>) {
        ListenFactory.createBugle().listening()
        val content = chain.getListenContent()
        chain.process(content)
    }
}