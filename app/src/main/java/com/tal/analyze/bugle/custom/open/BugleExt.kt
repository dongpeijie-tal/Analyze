package com.tal.analyze.bugle.custom.open

import com.tal.analyze.bugle.custom.OptionBuilder
import com.tal.analyze.bugle.custom.PuffConfigure


inline fun <reified T> listening(
    key: String,
    noinline optionBuilder: (OptionBuilder<T>.() -> Unit)? = null,
    noinline run: (T) -> Unit
) {
    OptionBuilder<T>().apply {
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

