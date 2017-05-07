/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.mc.nbt

/** A simple nbt tag, but with utility functions
 * @author Falcinspire
 * @version Apr/22/2017 (1:35 PM)
 */
data class NBTag(val name : String, val dat : Data) {

    constructor(name: String, dat : Int) : this(name, IntData(dat))
    constructor(name: String, dat : Short) : this(name, ShortData(dat))
    constructor(name: String, dat : Byte) : this(name, ByteData(dat))
    constructor(name: String, dat : Boolean) : this(name, if (dat) ByteData(1) else ByteData(0))
    constructor(name: String, dat : Long) : this(name, LongData(dat))
    constructor(name: String, dat : Float) : this(name, FloatData(dat))
    constructor(name: String, dat : Double) : this(name, DoubleData(dat))
    constructor(name: String, dat : String) : this(name, StringData(dat))
    constructor(name: String, dat : ByteArray) : this(name, ByteArrayData(dat))
    constructor(name: String, dat : IntArray) : this(name, IntArrayData(dat))
    constructor(name: String,  vararg dats : Data) : this(name, ListData(dats.toMutableList()))
    constructor(name: String,  vararg dats : NBTag) : this(name, CompoundData(dats.toMutableList()))
}

    