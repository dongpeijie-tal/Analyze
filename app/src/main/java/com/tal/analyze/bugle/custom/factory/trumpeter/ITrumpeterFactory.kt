package com.tal.analyze.bugle.custom.factory.trumpeter

import com.tal.analyze.bugle.custom.open.PuffContent

interface ITrumpeterFactory {

    fun puff(content: PuffContent<Any?>)

}