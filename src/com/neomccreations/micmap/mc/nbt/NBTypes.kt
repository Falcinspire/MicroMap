package com.neomccreations.micmap.mc.nbt

/**
 * @author Falcinspire
 * @version Apr/22/2017 (7:46 PM)
 */

enum class NBTypes {
    END,
    BYTE,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BYTE_ARRAY,
    STRING,
    LIST,
    COMPOUND,
    INT_ARRAY;

    val id : Byte = this.ordinal.toByte()
}

fun lookupNBT(id : Byte) : NBTypes {
    return NBTypes.values()[id.toInt()]
}