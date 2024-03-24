package com.tal.analyze.bugle.custom.manager

import com.tal.analyze.bugle.custom.utils.Available
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.Closeable

/**
 * 周期（协程）
 */
class Schedule : Closeable, Available {

    // 总任务，子任务独立管理
    private val job = SupervisorJob()

    // 运行的协程
    private val scope: CoroutineScope = CoroutineScope(job)

    fun launch(
        handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> },
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        run: suspend () -> Unit
    ) = scope.launch(handler + dispatcher) {
            run()
        }

    /**
     * 危险方法
     * 在你不了解框架设计的前提下，请不要随意改动此方法
     * 作用：用于休息协程
     * 基于activity为起点，让scope有机会休息，因为在一些页面并不需要使用此scope
     */
    fun tryRest(){
        if(job.children.none { it.isActive }) {
            close()
        }
    }

    override fun close() {
        job.cancel()
        scope.cancel()
    }

    override fun isActive(): Boolean {
        return job.isActive
    }
}