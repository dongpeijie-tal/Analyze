package com.tal.analyze.bugle.custom

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.tal.analyze.bugle.custom.callback.PuffProgressCallback
import com.tal.analyze.bugle.custom.intercept.listening.invoke.InvokeListeningIntercept
import com.tal.analyze.bugle.custom.intercept.listening.base.ListenChainBean
import com.tal.analyze.bugle.custom.intercept.listening.base.ListeningChain
import com.tal.analyze.bugle.custom.intercept.listening.lifecycle.ListeningLifecycleIntercept
import com.tal.analyze.bugle.custom.intercept.listening.base.Listener
import com.tal.analyze.bugle.custom.intercept.puff.ChainBean
import com.tal.analyze.bugle.custom.intercept.puff.InvokePuffIntercept
import com.tal.analyze.bugle.custom.intercept.puff.PuffChain
import com.tal.analyze.bugle.custom.open.DispatchThread
import com.tal.analyze.bugle.custom.open.intercept.IListeningIntercept
import com.tal.analyze.bugle.custom.open.intercept.IPuffIntercept


class ListenerBuilder<T>{
    // 接收消息线程
    var thread : DispatchThread = DispatchThread.DEFAULT
    var lifecycle: Lifecycle? = null
    var context: Context? = null
    var fragment: Fragment? = null
    var view: View? = null

    fun create(key: String, listening: (T?) -> Unit) {
        val intercepts = mutableListOf<IListeningIntercept<T?>>()
        // 生命周期处理
        intercepts.add(ListeningLifecycleIntercept(fragment,context,lifecycle))
        // 粘性消息处理
//        intercepts.add(StickListeningIntercept(key,listening))
        // 执行处理器
        intercepts.add(InvokeListeningIntercept())
        val content = Listener(key,thread, listening)
        ListeningChain(content, ListenChainBean(key = key,listener = listening),0,intercepts).call(content)
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