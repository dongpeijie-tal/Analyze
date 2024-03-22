package com.tal.analyze.bugle.custom.intercept.puff

import com.tal.analyze.bugle.custom.open.intercept.IPuffIntercept
import com.tal.analyze.bugle.custom.open.intercept.PuffContent


/**
 * 拦截器启动者
 */
internal class PuffChain(
    private val chainBean: ChainBean,
    private val index: Int,
    private val intercepts: List<IPuffIntercept>,
) : IPuffIntercept.IPuffChain {

    private fun createChain(index: Int,bean: ChainBean) =
        PuffChain(bean, index, intercepts)

    /**
     * 拦截器链执行的位置
     * 默认从第一个开始执行
     */
    fun call(){
        process(chainBean.convertPuffContent<Any?>())
    }

    override fun process(bean: PuffContent<*>) {
        if(index >= intercepts.size){
            throw IllegalArgumentException("puff消息拦截器数目错误，请注意检查拦截器数量")
        }
        intercepts[index].intercept(createChain(index + 1,bean.convertChainBean()))
    }

    override fun <R> getPuffContent(): PuffContent<R?> = chainBean.convertPuffContent()

    @Suppress("UNCHECKED_CAST")
    private fun <R> ChainBean.convertPuffContent(): PuffContent<R?> =
        PuffContent(key = key, msg = msg as R?, isStick = isStick, progressCallback = progressCallback)

    private fun PuffContent<*>.convertChainBean(): ChainBean =
        ChainBean(key = key, msg = msg, isStick = isStick, progressCallback = progressCallback)
}

