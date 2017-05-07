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