package com.tal.analyze.bugle.custom.open

import com.tal.analyze.bugle.custom.callback.PuffProgressCallback

data class PuffContent<T>(val key: String, val msg: T?, val isStick: Boolean, val progressCallback: PuffProgressCallback?)