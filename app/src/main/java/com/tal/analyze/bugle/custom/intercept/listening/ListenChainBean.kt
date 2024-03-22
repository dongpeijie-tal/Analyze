package com.tal.analyze.bugle.custom.intercept.listening

data class ListenChainBean<T>(val key : String,val listener : (T?)->Unit)
