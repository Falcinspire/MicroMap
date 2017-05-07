package com.neomccreations.micmap.mc.nbt

/**
 * @author Falcinspire
 * @version Apr/21/2017 (4:36 PM)
 */
interface DataVisitor {

    fun visit(dat : IntData)
    fun visit(dat : ShortData)
    fun visit(dat : ByteData)
    fun visit(dat : LongData)
    fun visit(dat : FloatData)
    fun visit(dat : DoubleData)
    fun visit(dat : StringData)
    fun visit(dat : ByteArrayData)
    fun visit(dat : CompoundData)
    fun visit(dat : ListData<out Data>)
    fun visit(dat : IntArrayData)

}



    