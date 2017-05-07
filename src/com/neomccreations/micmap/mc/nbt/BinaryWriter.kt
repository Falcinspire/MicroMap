package com.neomccreations.micmap.mc.nbt

import java.io.DataOutputStream

/**
 * @author Falcinspire
 * @version Apr/22/2017 (7:33 PM)
 */
/* This was originally going to be part of the individual data classes
 * themselves, but I thought this was prettier
 */
class BinaryWriter(val out : DataOutputStream) : DataVisitor {

    override fun visit(dat: IntData) {
        out.writeInt(dat.value)
    }

    override fun visit(dat: ShortData) {
        out.writeShort(dat.value.toInt())
    }

    override fun visit(dat: ByteData) {
        out.writeByte(dat.value.toInt())
    }

    override fun visit(dat: LongData) {
        out.writeLong(dat.value)
    }

    override fun visit(dat: FloatData) {
        out.writeFloat(dat.value)
    }

    override fun visit(dat: DoubleData) {
        out.writeDouble(dat.value)
    }

    override fun visit(dat: StringData) {
        out.writeUTF(dat.value)
    }

    override fun visit(dat: ByteArrayData) {
        out.writeInt(dat.value.size)
        out.write(dat.value)
    }

    override fun visit(dat: CompoundData) {
        for (tag in dat.values) {
            out.writeByte(tag.dat.type.id.toInt())
            out.writeUTF(tag.name)
            tag.dat.visit(this)
        }
        out.writeByte(NBTypes.END.id.toInt()) //signifies end of compound
    }

    override fun visit(listDat: ListData<out Data>) {
        var id : Byte = 0
        if (listDat.values.size > 0) {
            id = listDat.values[0].type.id
        }

        out.writeByte(id.toInt())
        out.writeInt(listDat.values.size)

        for (dat in listDat.values) { dat.visit(this) }
    }

    override fun visit(dat: IntArrayData) {
        out.writeInt(dat.values.size)
        for (integer in dat.values) out.writeInt(integer)
    }

    fun write(tag: NBTag) {
        //minecraft does not actually wrap it in a compound tag; last END byte is left out
        if (tag.dat is CompoundData) {
            //header for what should be an unnamed compound tag
            out.writeByte(tag.dat.type.id.toInt());
            out.writeUTF("")

            visit(tag.dat)

        }
        else {
            throw IllegalArgumentException(tag.dat.javaClass.simpleName + " must be CompoundData")
        }
        out.close()
    }

}
    