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
//        intercepts = mutableListOf(JsonConvertIntercept())
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
        println("接收到消息$it ${Thread.currentThread().name}")
    }
    puff("key","value2")
    puff("key","value3")
    puff("key","value4")
    puff("key","value5")
    puff("key","value6")
    puff("key","value7")
    puff("key","value8")
    puff("key","value9")
    puff("key","value10")
    puff("key","value11")
    puff("key","value12")
    puff("key","value13")
    puff("key","value14")
    puff("key","value15")
    puff("key","value16")
    puff("key","value17")
    puff("key","value18")
    puff("key","value19")
    puff("key","value20")
    puff("key","value21")
    puff("key","value22")
    puff("key","value23")
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