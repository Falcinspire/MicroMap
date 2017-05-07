package com.neomccreations.micmap

/**
 * @author Falcinspire
 * @version Apr/28/2017 (7:35 PM)
 */

class ShortNibbleArray(val rawArray: ShortArray) {

    constructor (size : Int) : this(ShortArray(size))

    operator fun get(index : Int) : Short {
        return rawArray[index]
    }

    operator fun set(index : Int, value : Short) {
        rawArray[index] = value
    }

    fun getLeading(index : Int) : Byte {
        return (rawArray[index].toInt() and 0xF00 shr 8).toByte()
    }

    fun getTrailing(index : Int) : Byte {
        return (rawArray[index].toInt() and 0x0FF).toByte()
    }

    fun setLeading(index : Int, value : Byte) {
        rawArray[index] = ((rawArray[index].toInt() and 0x0FF) or (value.toInt() and 0x0F shl 8)).toShort()
    }

    fun setTrailing(index : Int, value : Byte) {
        rawArray[index] = ((rawArray[index].toInt() and 0xF00) or (value.toInt())).toShort()
    }

    fun splitToLeadingArray() : NibbleArray {
        val leading = NibbleArray(rawArray.size)
        for (i in 0 until rawArray.size) {
            leading[i] = getLeading(i)
        }
        return leading
    }

    fun splitToLeadingArrayBytes() : ByteArray {
        val leading = ByteArray(rawArray.size)
        for (i in 0 until rawArray.size) {
            leading[i] = getLeading(i)
        }
        return leading
    }

    fun splitToTrailingArray() : ByteArray {
        val leading = ByteArray(rawArray.size)
        for (i in 0 until rawArray.size) {
            leading[i] = getTrailing(i)
        }
        return leading
    }

    fun empty() : Boolean {

        for (bt in rawArray) if (bt != 0.toShort()) return false
        return true

    }
}
    