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

import java.io.DataInputStream
import java.io.IOException

/**
 * @author Falcinspire
 * @version Apr/22/2017 (7:33 PM)
 */

class BinaryReader(val dis : DataInputStream) {

    fun read() : NBTag {

        val compoundId = dis.readByte()
        if (compoundId != NBTypes.COMPOUND.id) throw IOException("Malformed NBT; root tag must be a compound")

        dis.readUTF()

        val read = readCompound(dis)
        return NBTag("", read) //unbox the first value
    }

   companion object {

       /** Maps the ids of all of the data types to their read functions */
       val map = hashMapOf(
               NBTypes.BYTE.id to fun(dis : DataInputStream) : Data {return ByteData(dis.readByte())},
               NBTypes.SHORT.id to fun(dis : DataInputStream) : Data {return ShortData(dis.readShort())},
               NBTypes.INT.id to fun(dis : DataInputStream) : Data {return IntData(dis.readInt())},
               NBTypes.LONG.id to fun(dis : DataInputStream) : Data {return LongData(dis.readLong())},
               NBTypes.FLOAT.id to fun(dis : DataInputStream) : Data {return FloatData(dis.readFloat())},
               NBTypes.DOUBLE.id to fun(dis : DataInputStream) : Data {return DoubleData(dis.readDouble())},
               NBTypes.BYTE_ARRAY.id to Companion::readByteArray,
               NBTypes.STRING.id to fun(dis : DataInputStream) : Data {return StringData(dis.readUTF())},
               NBTypes.LIST.id to Companion::readList,
               NBTypes.COMPOUND.id to Companion::readCompound,
               NBTypes.INT_ARRAY.id to Companion::readIntArray
               )

       fun readByteArray(dis : DataInputStream) : Data {
           val size = dis.readInt()
           var array = ByteArray(size)
           dis.readFully(array)

           return ByteArrayData(array)
       }

       fun readList(dis : DataInputStream) : Data {

           val list = mutableListOf<Data>();

           val id = dis.readByte()
           val size = dis.readInt()

           val readFunc = map[id]!!

           repeat (size) {
               list.add(readFunc(dis))
           }

           return ListData(list)
       }

       fun readCompound(dis : DataInputStream) : CompoundData {

           val list = mutableListOf<NBTag>();

           var nextId = dis.readByte()

           while (nextId != NBTypes.END.id) {

               val name = dis.readUTF()

               val dat = map[nextId]!!.invoke(dis)

               list.add(NBTag(name, dat))

               try {
                   nextId = dis.readByte()
               } catch (e : IOException) {
                   println("Failed on ${name}");
               }
           }

           return CompoundData(list)
       }

       fun readIntArray(dis : DataInputStream) : Data {
           val size = dis.readInt()
           var array = IntArray(size)

           for (i in 0 until size) {
                array[i] = dis.readInt()
           }

           return IntArrayData(array)
       }

    }

}
    