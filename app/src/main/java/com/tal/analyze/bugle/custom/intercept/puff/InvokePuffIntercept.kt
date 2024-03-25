package com.tal.analyze.bugle.custom.intercept.puff

import com.tal.analyze.bugle.custom.factory.listener.ListenerTaskFactory
import com.tal.analyze.bugle.custom.factory.trumpeter.TrumpeterFactory
import com.tal.analyze.bugle.custom.open.intercept.IPuffIntercept
import com.tal.analyze.bugle.custom.stick.Gramophone

/**
 * 真正执行bugle的地方
 */
internal class InvokePuffIntercept : IPuffIntercept {

    override fun intercept(chain: IPuffIntercept.IPuffChain) {
        /**
         * 检查是否需要更新粘性消息
         */
        val message = chain.getPuffContent<Any?>()
        // 粘性消息记录，不论是否有接收者
        if(message.isStick){
            Gramophone.record(message.key,message.msg)
        }
        /**
         * 检查聆听者工作台是否启动
         */
        ListenerTaskFactory.checkListenerTask(message.key)
        /**
         * 号手工厂创建号手,执行吹奏任务
         */
        TrumpeterFactory.create().puff(chain.getPuffContent<Any?>())
    }

}
