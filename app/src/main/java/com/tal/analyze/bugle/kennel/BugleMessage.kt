package com.tal.analyze.bugle.kennel

import com.tal.analyze.bugle.custom.callback.PuffProgressCallback

internal data class BugleMessage(
    val key: String,
    val message: Any?,
    val progressCallback: PuffProgressCallback?
)
