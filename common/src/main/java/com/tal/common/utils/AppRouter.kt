package com.tal.common.utils

import com.didi.drouter.api.DRouter

/**
 * 页面跳转
 */
object AppRouter {

    fun go(path: String){
        DRouter.build(path).start()
    }

}