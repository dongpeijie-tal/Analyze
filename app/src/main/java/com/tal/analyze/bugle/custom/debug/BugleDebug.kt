package com.tal.analyze.bugle.custom.debug

import android.util.Log

internal object BugleDebug{
    private var isDebug = true

    private const val TAG = "bugle"
    fun logD(msg:String){
        if(isDebug){
            Log.d(TAG,msg)
        }
    }

}


fun lod(msg:String) = BugleDebug.logD(msg)
