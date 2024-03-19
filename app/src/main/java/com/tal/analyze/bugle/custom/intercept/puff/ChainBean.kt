package com.tal.analyze.bugle.custom.intercept.puff

import com.tal.analyze.bugle.custom.callback.PuffProgressCallback

internal data class ChainBean(
    val key: String,
    val msg: Any?,
    val isStick: Boolean = false,
    val progressCallback: PuffProgressCallback? = null
)