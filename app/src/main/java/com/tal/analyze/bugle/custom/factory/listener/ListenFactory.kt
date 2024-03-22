package com.tal.analyze.bugle.custom.factory.listener

import com.tal.analyze.bugle.custom.factory.listener.impl.LifecycleListenImpl

/**
 * 号角工厂，决定创建什么样的号角
 */
object ListenFactory {

    fun createBugle(): IListenFactory {
        return LifecycleListenImpl()
    }

}