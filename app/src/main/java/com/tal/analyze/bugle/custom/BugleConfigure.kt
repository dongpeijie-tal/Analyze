package com.tal.analyze.bugle.custom

import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import com.tal.analyze.bugle.custom.callback.PuffProgressCallback
import com.tal.analyze.bugle.custom.intercept.puff.ChainBean
import com.tal.analyze.bugle.custom.intercept.puff.InvokePuffIntercept
import com.tal.analyze.bugle.custom.intercept.puff.PuffChain
import com.tal.analyze.bugle.custom.open.DispatchThread
import com.tal.analyze.bugle.custom.open.IPuffIntercept


class OptionBuilder<T>{
    // 接收消息线程
    var thread : DispatchThread = DispatchThread.DEFAULT
    // 自动取消监听
    var autoCancel : ((T)->Boolean)? = null
    var lifecycle: Lifecycle? = null
    var context: Context? = null
    var view: View? = null

    fun <T> create(key: String, run: (T) -> Unit) {

    }
}

class PuffConfigure internal constructor() {
    // 进度回调
    var progressCallback : PuffProgressCallback? = null
    var intercepts : MutableList<IPuffIntercept>? = null

    fun create(key: String,msg: Any?) {
        if(intercepts == null){
            intercepts = mutableListOf()
        }
        intercepts?.add(InvokePuffIntercept())
        PuffChain(ChainBean(key,msg,progressCallback = progressCallback),0,intercepts!!).call()
    }
}