package com.tal.analyze.bugle.custom.open.api

import com.tal.analyze.bugle.custom.ListenerBuilder
import com.tal.analyze.bugle.custom.PuffConfigure


fun <T> listening(
    key: String,
    optionBuilder: (ListenerBuilder<T>.() -> Unit)? = null,
    run: (T?) -> Unit
) {
    ListenerBuilder<T>().apply {
        optionBuilder?.invoke(this)
        create(key, run)
    }
}

fun puff(key: String, msg: Any?, configure: (PuffConfigure.() -> Unit)? = null) {
    PuffConfigure().apply {
        configure?.invoke(this)
        create(key, msg)
    }
}

