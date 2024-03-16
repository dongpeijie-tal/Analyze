package com.tal.analyze.bugle.custom.open



/**
 * 准备吹号角拦截器
 * 消息发送前拦截
 */
interface IPuffIntercept<T> {
    /**
     * 需要实现的方法
     * 例如过滤空消息，一条拆分多条请在此处处理
     * 逻辑处理完毕一定要调用chain.process（）方法，启动下一个拦截器。
     */
    fun intercept(chain: IPuffChain<T>)
    interface IPuffChain<T> {
        fun process(bean: ChainBean<T>)
        fun getChainBean(): ChainBean<T>
    }
}