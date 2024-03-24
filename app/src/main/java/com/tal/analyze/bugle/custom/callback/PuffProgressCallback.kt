package com.tal.analyze.bugle.custom.callback


/**
 * 发送事件进度回调
 */
abstract class PuffProgressCallback{

    /**
     * 发送失败，在发送途中遇到问题
     */
    open fun onFail(e: Exception){}

    /**
     * 没有接收者
     */
    open fun unHandler(){}
}