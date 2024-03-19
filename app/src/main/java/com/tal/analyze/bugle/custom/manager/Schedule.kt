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


    override fun close() {
        job.cancel()
        scope.cancel()
    }

    override fun isActive(): Boolean {
        return job.isActive
    }
}