package com.tal.analyze.bugle.custom.open

import com.tal.analyze.bugle.custom.listener.PuffProgressCallback

data class ChainBean<T>(val key: String, val msg: T?,val progressCallback: PuffProgressCallback?)