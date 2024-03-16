package com.tal.analyze.bugle.custom

import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import com.tal.analyze.bugle.custom.intercept.puff.InvokePuffIntercept
import com.tal.analyze.bugle.custom.intercept.puff.PuffChain
import com.tal.analyze.bugle.custom.listener.PuffProgressCallback
import com.tal.analyze.bugle.custom.open.ChainBean
import com.tal.analyze.bugle.custom.open.IPuffIntercept

class BugleConfigure {

}

class OptionBuilder<T>{
    // 接收消息线程
    var thread : Boolean = false
    // 自动取消监听
    var autoCancel : ((T)->Boolean)? = null
    var lifecycle: Lifecycle? = null
    var context: Context? = null
    var view: View? = null
}

class PuffConfigure<T> internal constructor() {
    // 进度回调
    var progressCallback : PuffProgressCallback? = null
    var intercepts : MutableList<IPuffIntercept<T>>? = null

    fun create(key: String,msg: T?) {
        if(intercepts == null){
            intercepts = mutableListOf()
        }
        intercepts?.add(InvokePuffIntercept())
        PuffChain(ChainBean(key,msg,progressCallback),0,intercepts!!).call()
    }
}