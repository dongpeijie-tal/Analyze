package com.tal.analyze.bugle.kennel

/**
 * 号角管理员
 */
internal object BugleManager {
    private var _bugle : Bugle? = null
    // 单一号角，重复使用
    private val singleBugle: Bugle
        get(){
            if(_bugle == null || !_bugle!!.isActive()){
                _bugle = Bugle()
            }
            return _bugle!!
        }

    /**
     * 单个号角
     */
    fun singleBugle() = singleBugle

    fun tryRest(){
        if(_bugle?.isEmpty() == true){
            _bugle?.close()
        }
    }
}