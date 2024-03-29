package com.tal.analyze.bugle.custom.open.intercept

import com.tal.analyze.bugle.custom.intercept.listening.base.IContentListener

/**
 * 聆听拦截
 */
interface IListeningIntercept<T>{
    // 需要实现此方法实现消息拦截处理
    // 考虑场景 过滤空消息等
    fun intercept(chain: IListeningChain<T?>)

    interface IListeningChain<C> {

        fun process(listener: IContentListener<C>)

        fun getListenListener(): IContentListener<C>

    }
}