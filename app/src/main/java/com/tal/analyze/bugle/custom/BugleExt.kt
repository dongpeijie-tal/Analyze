package com.tal.analyze.bugle.custom

import android.app.Activity
import android.app.Service
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.tal.analyze.bugle.custom.factory.BugleFactory
import com.tal.analyze.bugle.custom.listener.PuffProgressCallback

inline fun <reified T> Context.registerJson(key:String, optionBuilder : OptionBuilder<T>.()->Unit, crossinline run : (T)->Unit){
    val builder = OptionBuilder<T>().apply {
        optionBuilder()
    }
    BugleFactory.createBugle().register()
//    SingleBugle().register(key){
//        if(it is String?){
//            val bean = Gson().fromJson(it,T::class.java)
//            if(builder.autoCancel?.invoke(bean) == true){
//                // TODO 生成eventId，用来取消监听
//                SingleBugle().unregister(key)
//            }
//            run(bean)
//        }
//    }
}

inline fun <reified T> View.registerJson(key:String, optionBuilder : OptionBuilder<T>.()->Unit, crossinline run : (T)->Unit){
    addOnAttachStateChangeListener(object: View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {

        }

        override fun onViewDetachedFromWindow(v: View) {

        }
    })
    post {

    }
}

inline fun <reified T> Fragment.registerJson(key:String, optionBuilder : OptionBuilder<T>.()->Unit, crossinline run : (T)->Unit){

}

inline fun <reified T> Activity.registerJson(key:String, optionBuilder : OptionBuilder<T>.()->Unit, crossinline run : (T)->Unit){

}

inline fun <reified T> Service.registerJson(key:String, optionBuilder : OptionBuilder<T>.()->Unit, crossinline run : (T)->Unit){

}

inline fun <reified T> register(key:String,crossinline run:(T)->Unit){

//    SingleBugle().register(key){
//        if(it !is T){
//            throw NullPointerException()
//        }else{
//            run(it as T)
//        }
//    }
}

fun <T> puff(key: String,msg: T?,configure: (PuffConfigure<T>.() -> Unit)? = null){
    PuffConfigure<T>().apply {
        configure?.invoke(this)
        create(key,msg)
    }
}

