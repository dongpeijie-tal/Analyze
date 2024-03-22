package com.tal.analyze.bugle.custom

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.tal.analyze.bugle.custom.callback.PuffProgressCallback
import com.tal.analyze.bugle.custom.intercept.listening.InvokeListeningIntercept
import com.tal.analyze.bugle.custom.intercept.listening.ListenChainBean
import com.tal.analyze.bugle.custom.intercept.listening.ListeningChain
import com.tal.analyze.bugle.custom.intercept.listening.ListeningLifecycleIntercept
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
    var intercepts : MutableList<IListeningIntercept<T?>>? = null

    fun create(key: String, listener: (T?) -> Unit) {
        if(intercepts == null){
            intercepts = mutableListOf()
        }
        intercepts?.add(0,InvokeListeningIntercept())
        intercepts?.add(0,ListeningLifecycleIntercept(fragment,context,lifecycle))
        ListeningChain(ListenChainBean(key = key,listener = listener),0,intercepts!!).call()
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