package com.tal.analyze.bugle.custom.intercept.listening.invoke

import com.tal.analyze.bugle.custom.intercept.listening.base.Listener
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept

/**
 * 执行监听拦截
 */
class InvokeListeningIntercept<T>: IListeningIntercept<T> {

    override fun intercept(chain: IListeningIntercept.IListeningChain<T?>) {
        val content = chain.getListenListener()
        (content as Listener<T>).listening()
        chain.process(content)
    }
}