package com.tal.analyze.bugle.custom.intercept.puff

import com.tal.analyze.bugle.custom.open.ChainBean
import com.tal.analyze.bugle.custom.open.IPuffIntercept


/**
 * 拦截器启动者
 */
internal class PuffChain<T>(
    private val chainBean: ChainBean<T>,
    private val index: Int,
    private val intercepts: List<IPuffIntercept<T>>,
) : IPuffIntercept.IPuffChain<T> {

    private fun createChain(index: Int,bean: ChainBean<T>) =
        PuffChain(bean, index, intercepts)

    override fun process(bean: ChainBean<T>) {
        if(index >= intercepts.size){
            throw RuntimeException("puff消息拦截器数目错误，请注意检查拦截器数量")
        }
        intercepts[index].intercept(createChain(index + 1,bean))
    }

    /**
     * 拦截器链执行的位置
     * 默认从第一个开始执行
     */
    fun call(){
        process(chainBean)
    }

    override fun getChainBean() = chainBean

}

