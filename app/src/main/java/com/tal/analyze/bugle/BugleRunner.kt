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
        }
    }

    // 注册消息
    listening<String>("key", optionBuilder = {
        thread = DispatchThread.MAIN
        this.lifecycle = lifecycle
    }){
        println("接收到消息$it ${Thread.currentThread().name}")
    }
    puff("key","2")
    puff("key","3")
    puff("key","4")
    puff("key","5")
    puff("key","6")
    puff("key","7")
    puff("key","8")
    puff("key","9")
    puff("key","10")
    puff("key","11")
    puff("key","12")
    puff("key","13")
    puff("key","14")
    puff("key","15")
    puff("key","16")
    puff("key","17")
    puff("key","18")
    puff("key","19")
    puff("key","20")
    puff("key","21")
    puff("key","22")
    puff("key","23")
    puff("key","24")
    puff("key","25")
    puff("key","26")
    puff("key","27")
    puff("key","28")
    puff("key","29")
    puff("key","30")
    puff("key","31")
    puff("key","32")
    puff("key","33")
    puff("key","34")
    puff("key","35")
    puff("key","36")
    puff("key","37")
    puff("key","38")
    puff("key","39")
    puff("key","40")
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