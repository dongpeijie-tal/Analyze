package com.tal.analyze.bugle

import androidx.lifecycle.Lifecycle
import com.tal.analyze.bugle.custom.callback.PuffProgressCallback
import com.tal.analyze.bugle.custom.open.DispatchThread
import com.tal.analyze.bugle.custom.open.api.listening
import com.tal.analyze.bugle.custom.open.api.puff
import com.tal.analyze.bugle.custom.open.intercept.IPuffIntercept

fun runBugleTest(lifecycle: Lifecycle){
    // 发送消息
    puff("key","value"){
        intercepts = mutableListOf(JsonConvertIntercept())
        progressCallback = object : PuffProgressCallback(){
            override fun unHandler() {
                println("没有接收者")
            }

            override fun onFail(e: Exception) {
                super.onFail(e)
            }
        }
    }

    // 注册消息
    listening<String>("key", optionBuilder = {
        thread = DispatchThread.MAIN
        this.lifecycle = lifecycle
    }){
        println("接收到消息$it")
    }
}

/**
 * json转换拦截器
 */
class JsonConvertIntercept: IPuffIntercept {
    override fun intercept(chain: IPuffIntercept.IPuffChain) {
        val bean = chain.getPuffContent<String>()
        chain.process(bean)
    }

}