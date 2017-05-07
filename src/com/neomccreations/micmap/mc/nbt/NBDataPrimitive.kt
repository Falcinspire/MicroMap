package com.neomccreations.micmap.mc.nbt

/**
 * @author Falcinspire
 * @version Apr/24/2017 (8:36 PM)
 */

class ByteData(val value : Byte) : Data {

    override val type = NBTypes.BYTE

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class ShortData(val value : Short) : Data {

    override val type = NBTypes.SHORT

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class IntData(val value : Int) : Data {

    override val type = NBTypes.INT

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class LongData(val value : Long) : Data {

    override val type = NBTypes.LONG

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class FloatData(val value : Float) : Data {

    override val type = NBTypes.FLOAT

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class DoubleData(val value : Double) : Data {

    override val type = NBTypes.DOUBLE

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class ByteArrayData(val value : ByteArray) : Data {

    override val type = NBTypes.BYTE_ARRAY

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class StringData(val value : String) : Data {

    override val type = NBTypes.STRING

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}


class IntArrayData(val values : IntArray) : Data {

    override val type = NBTypes.INT_ARRAY

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}