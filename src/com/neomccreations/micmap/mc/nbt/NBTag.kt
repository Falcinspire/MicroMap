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

    