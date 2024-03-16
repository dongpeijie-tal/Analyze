package com.tal.analyze.bugle.custom.utils

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class LifeSafe<T: Available>(private val initializer: () -> T) : ReadOnlyProperty<Any?, T> {
    private var value: T? = null
    private var initialized = false

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!initialized) {
            value = initializer()
            initialized = true
        }
        if(value == null){
            throw RuntimeException("使用lifeSafe委托创建失败")
        }else{
            // 如果之前创建的已经失活了，创建新的
            if(!value!!.isActive()){
                value = initializer()
            }
        }
        return value ?: throw RuntimeException("使用lifeSafe委托创建失败")
    }
}

internal fun <T : Available> lifeSafe(initializer: () -> T) = LifeSafe(initializer)