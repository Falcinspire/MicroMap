/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
    