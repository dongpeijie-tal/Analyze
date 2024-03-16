package com.tal.analyze.bugle.custom


import com.tal.analyze.bugle.custom.utils.Available
import com.tal.analyze.bugle.kennel.Bugle
import com.tal.analyze.bugle.kennel.BugleMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Closeable
import java.util.concurrent.ConcurrentHashMap

/**
 * 1、拿到全局ApplicationContext，注册activity监听，在activity销毁的时候
 * 根据注册消息的context判断当前activity是否销毁了，如果销毁了，就把对应的监听移除，并且移除跟此activity有关的粘性消息记录
 * 2、context是compentActivity，可以获取它的lifecycle，不过拿到application，也不需要了
 * 3、考虑，需不需要提供view的注册方式，view的注册方式需要考虑从页面移除与添加的状态恢复与销毁
 * 4、job的管理根据当前监听者数量，如果在activity销毁后没有了监听者，意味着job可以结束了，job做懒加载机制。
 * 5、消息分发的时候添加拦截器，让用户可以判断什么消息不需要分发。什么消息需要二次处理
 * 6、做接口分离
 * 7、监听注册可以指定线程
 * 8、粘性消息处理
 * 9、消息顺序问题
 * 10、channel的容量，考虑要不要最大容量，不然就得让协程不断挂起
 * 11、这仅仅是支持发送json消息，还要支持发送javaBean
 */
internal class SingleBugle : Closeable, Available {
    // 总任务，子任务独立管理
    private val job = SupervisorJob()
    // 运行的协程
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + job)
    // 粘性事件
    private val stickMap = ConcurrentHashMap<String,BugleMessage<Any?>>()
    // 监听者
    private val listener = ConcurrentHashMap<String,MutableList<suspend (Any?)->Unit>>()

    private val bugle = Bugle<Any?>()

    fun puff(msg: BugleMessage<Any?>){
        scope.launch {
            bugle.send(msg)
        }
    }

    private fun loop(){
        scope.launch {
            val bean = bugle.receive()
            // 分发的时候，获取一下lifecycle，如果有对应的scope，使用对应的scope回调,是不是会影响事件顺序？
            dispatchMessage(bean)
        }
    }

    /**
     * 分发消息
     */
    private suspend fun dispatchMessage(bean: BugleMessage<Any?>) {
        updateStickMessage(bean)
        listener[bean.key]?.forEach {
            withContext(Dispatchers.Default){
                it.invoke(bean.message)
            }
        }
    }

    /**
     * 更新粘性消息
     */
    private fun updateStickMessage(bean: BugleMessage<Any?>) {
        stickMap[bean.key] = bean
    }

    fun register(key: String,run : suspend (Any?)->Unit){
        listener.getOrDefault(key, mutableListOf()).add(run)
    }

    fun unregister(key: String){
        // 移除指定见监听
        listener.remove(key)
    }

    override fun close() {

    }

    override fun isActive(): Boolean {
        return scope.isActive
    }


}

