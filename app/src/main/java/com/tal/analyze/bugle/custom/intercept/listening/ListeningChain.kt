package com.tal.analyze.bugle.custom.intercept.listening

import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept
import com.tal.analyze.bugle.custom.open.intercept.ListenContent

/**
 * 监听拦截器启动者
 */
class ListeningChain<T>(
    private val listenChainBean: ListenChainBean<T>,
    private val index: Int,
    private val intercepts: List<IListeningIntercept<T?>>
) : IListeningIntercept.IListeningChain<T?>{

    private fun createChain(index: Int,bean: ListenChainBean<T>) =
        ListeningChain(bean, index, intercepts)

    fun call() {
//        val listener = Listener(content.key,content.onThread, content.listening as (Any?) -> Unit)
        process(listenChainBean.convertListenContent())
    }

    override fun process(bean: ListenContent<*>) {
        if(index >= intercepts.size){
            throw IllegalArgumentException("listening消息拦截器数目错误，请注意检查拦截器数量")
        }
        intercepts[index].intercept(createChain(index + 1,bean.convertListenChain()))
    }

    override fun getListenContent(): ListenContent<T?> {
        return listenChainBean.convertListenContent()
    }

    @Suppress("UNCHECKED_CAST")
    private fun ListenContent<*>.convertListenChain(): ListenChainBean<T> =
        ListenChainBean(key = key, listener = listening as (T?)->Unit)

    @Suppress("UNCHECKED_CAST")
    private fun ListenChainBean<T>.convertListenContent(): ListenContent<T?> =
        ListenContent(key = key, listening = listener)
}
