package com.tal.analyze.bugle.custom.intercept.listening.base

import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept

/**
 * 监听拦截器启动者
 */
class ListeningChain<T>(
    private val contentListener: IContentListener<T?>,
    private val index: Int,
    private val intercepts: List<IListeningIntercept<T?>>
) : IListeningIntercept.IListeningChain<T?>{

    private fun createChain(index: Int,contentListener: IContentListener<T?>) =
        ListeningChain(contentListener,index, intercepts)

    fun call() {
        process(contentListener)
    }

    override fun process(listener: IContentListener<T?>) {
        if(index >= intercepts.size){
            throw IllegalArgumentException("listening消息拦截器数目错误，请注意检查拦截器数量")
        }
        intercepts[index].intercept(createChain(index + 1,contentListener))
    }

    override fun getListenListener(): IContentListener<T?> {
        return contentListener
    }

}
