package com.neomccreations.micmap

/**
 * @author Falcinspire
 * @version Apr/28/2017 (7:35 PM)
 */

class NibbleArray(val rawArray: ByteArray) : Iterator<Byte> {

    constructor (size : Int) : this(ByteArray(size))

    var cursor = 0
    val size = rawArray.size * 2

    operator fun get(index : Int) : Byte {
        if (index % 2 == 0)
            return nibbleLeft(rawArray[index / 2])
        else
            return nibbleRight(rawArray[index / 2])
    }

    operator fun set(index : Int, value : Byte) {

        val adjIndex = index / 2
        val origVal = rawArray[adjIndex]

        if (index % 2 == 0) {
            rawArray[adjIndex] = ((origVal.toInt() and 0x0F) or (value.toInt() and 0x0F shl 4)).toByte()
        }
        else {
            rawArray[adjIndex] = ((origVal.toInt() and 0xF0) or (value.toInt() and 0x0F)).toByte()
        }
    }

    inline fun nibbleLeft(value : Byte) : Byte {
        return (value.toInt() and 0xF0 shr 4).toByte()
    }

    inline fun nibbleRight(value: Byte) : Byte {
        return (value.toInt() and 0x0F).toByte()
    }

    fun empty() : Boolean {

        for (bt in rawArray) if (bt != 0.toByte()) return false
        return true

    }

    override fun hasNext(): Boolean {
        return cursor < size
    }

    override fun next(): Byte {
        return this[cursor++]
    }
}
    