package com.tal.analyze.bugle

import com.tal.analyze.bugle.custom.listener.PuffProgressCallback
import com.tal.analyze.bugle.custom.puff
import com.tal.analyze.bugle.custom.register

fun runBugleTest(){
    puff("",""){
        intercepts = mutableListOf()
        progressCallback = object : PuffProgressCallback(){
            override fun unHandler() {

            }
        }
    }
    register<TestBean>(""){

    }
}

data class TestBean(val name: String,val age: Int,val favor: List<String>)