package com.tal.analyze.bugle.custom.stick

/**
 * 留声机(粘性事件)
 */
object Gramophone{

    private val stickMap = mutableMapOf<String,StickMessage<*>>()

    fun record(key: String,msg: Any?,lifeScope: StickLifeScope = StickLifeScope.ACTIVITY,){
        stickMap[key] = StickMessage(key,lifeScope,msg)
    }

    fun clear(){
        stickMap.clear()
    }

    fun expired(key: String){

    }

    fun remove(key: String){

    }

}
