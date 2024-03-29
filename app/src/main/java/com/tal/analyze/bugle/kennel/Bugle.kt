package com.tal.analyze.bugle.kennel

import com.tal.analyze.bugle.custom.utils.Available
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedSendChannelException
import java.io.Closeable

/**
 * 号角（channel）
 */
internal class Bugle : Closeable,Available{
    // 通信关键
    val channel by lazy{
        Channel<BugleMessage>(capacity = 100)
    }

    suspend fun send(msg: BugleMessage,failCall: suspend ()->Unit){
        try {
            channel.send(msg)
        }catch (e: ClosedSendChannelException){
            failCall()
        }
    }

    suspend fun receive()=
        channel.receive()

    override fun close() {
        channel.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun isEmpty() = channel.isEmpty

    @OptIn(DelicateCoroutinesApi::class)
    override fun isActive(): Boolean {
        return !channel.isClosedForSend && !channel.isClosedForReceive
    }

}