package com.tal.analyze.bugle.custom.utils

import android.content.Context
import android.content.ContextWrapper

/**
 * 拆包ContextWrapper
 */
fun Context.unpack(): Context{
    var target = this
    while (target is ContextWrapper) {
        target = target.baseContext
    }
    return target
}
