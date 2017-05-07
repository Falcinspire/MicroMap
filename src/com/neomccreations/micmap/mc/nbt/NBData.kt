package com.neomccreations.micmap.mc.nbt

/**
 * @author Falcinspire
 * @version Apr/22/2017 (1:34 PM)
 */
interface Data {

    fun visit(vis : DataVisitor)
    val type : NBTypes
}


    