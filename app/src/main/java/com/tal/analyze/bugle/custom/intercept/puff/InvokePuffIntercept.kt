package com.tal.analyze.bugle.custom.intercept.puff

import com.tal.analyze.bugle.custom.factory.BugleFactory
import com.tal.analyze.bugle.custom.open.IPuffIntercept

/**
 * 真正执行bugle的地方
 */
internal class InvokePuffIntercept<T> : IPuffIntercept<T> {

    override fun intercept(chain: IPuffIntercept.IPuffChain<T>) {
        /**
         * TODO 检查是否有接收者，如果没有，直接更新粘性消息，并直接执行无接收回调
         */

        /**
         * TODO 检查协程（号手）是否可用，不可用创建新的
         */

        /**
         * channel（号角）工厂执行channel（号角）检查
         */
        BugleFactory.createBugle().puff(chain.getChainBean().msg)
    }

}
