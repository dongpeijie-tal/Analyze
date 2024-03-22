package com.tal.analyze.bugle.custom.factory.trumpeter

import com.tal.analyze.bugle.custom.open.intercept.PuffContent

interface ITrumpeterFactory {

    fun puff(content: PuffContent<Any?>)

}