package com.tal.analyze.bugle.custom.open.intercept

import com.tal.analyze.bugle.custom.open.DispatchThread

data class ListenContent<T>(val key: String, val onThread: DispatchThread = DispatchThread.DEFAULT, val listening: (T?)->Unit)