package com.tal.analyze.bugle.custom.factory

/**
 * bugle工厂，创建对应的Bugle实例
 */
interface IBugleFactory {
    fun register()
    fun puff(msg: Any?)
}

internal class GlobalBugleFactory: IBugleFactory{

    override fun register() {

    }

    override fun puff(msg: Any?) {

    }

}

object BugleFactory {
    fun createBugle(): IBugleFactory{
        return GlobalBugleFactory()
    }
}