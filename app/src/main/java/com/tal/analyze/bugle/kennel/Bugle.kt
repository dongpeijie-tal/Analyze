package com.tal.analyze.bugle.kennel

import kotlinx.coroutines.channels.Channel

internal class Bugle <T> {
    // 通信关键
    private val channel by lazy{
        Channel<BugleMessage<T>>()
    }

    suspend fun send(msg: BugleMessage<T>){
        channel.send(msg)
    }

    suspend fun receive() = channel.receive()

}