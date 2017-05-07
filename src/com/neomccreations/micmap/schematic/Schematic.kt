/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.schematic

import com.neomccreations.micmap.NibbleArray
import com.neomccreations.micmap.ShortNibbleArray
import com.neomccreations.micmap.coordutil.xyzToIndex
import com.neomccreations.micmap.mc.nbt.BinaryReader
import com.neomccreations.micmap.mc.nbt.CompoundData
import com.neomccreations.micmap.mc.nbt.ListData
import com.neomccreations.micmap.mc.nbt.NBTag
import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.util.zip.GZIPInputStream

/**
 * @author Falcinspire
 * @version Apr/27/2017 (5:36 PM)
 */

class Schematic(val width : Short,
                val length : Short,
                val height : Short,
                val blocks : ShortArray,
                val blockData : NibbleArray,
                val entities : MutableList<CompoundData>,
                val tileEntities : MutableList<CompoundData>) {

    fun block(x : Int, y : Int, z : Int) : Short {
        return blocks[xyzToIndex(x, y, z, width, length)]
    }

    fun block(x : Int, y : Int, z : Int, id : Short, data : Byte = 0) {
        val index = xyzToIndex(x, y, z, width, length)
        blocks[index] = id
        blockData[index] = data
    }

    fun forAllBlocks(operation : (x: Int, y: Int, z: Int, index: Int, id: Short, data: Byte) -> Unit) {
        for (y in 0 until height) {
            for (z in 0 until length) {
                for (x in 0 until width) {
                    val rawIndex = xyzToIndex(x, y, z, width, length)
                    operation(x, y, z, rawIndex, blocks[rawIndex], blockData[rawIndex])
                }
            }
        }
    }
}

fun schematicFromFile(file : File) : Schematic {
    return schematicFromNBT(
            BinaryReader(
                    DataInputStream(
                            BufferedInputStream(
                                    GZIPInputStream(
                                            FileInputStream(file)))))
                    .read().dat as CompoundData)

}

fun schematicFromNBT(data : CompoundData) : Schematic {

    val blocksTrailing = data.byteArray("Blocks")
    val blocksLeadingRaw = data.byteArrayIfExists("AddBlocks")

    val blocks = ShortArray(blocksTrailing.size)

    //add the extra leading block ids if they exist
    if (blocksLeadingRaw != null) {

        val blocksLeading = NibbleArray(blocksLeadingRaw)

        for (i in 0 until blocks.size) {
            blocks[i] = (blocksTrailing[i].toInt() and (blocksLeading[i].toInt() shl 4)).toShort()
        }
    }
    else {

        for (i in 0 until blocks.size) {
            blocks[i] = blocksTrailing[i].toShort()
        }
    }

    return Schematic(data.short("Width"),
            data.short("Length"),
            data.short("Height"),
            blocks,
            NibbleArray(data.byteArray("Data")),
            data.list("Entities"),
            data.list("TileEntities"))

}

fun schematicToNBT(dat : Schematic) : CompoundData {

    val nbt = CompoundData(mutableListOf(
            NBTag("Width", dat.width),
            NBTag("Length", dat.width),
            NBTag("Height", dat.width),
            NBTag("Entities", ListData(dat.entities)),
            NBTag("TileEntities", ListData(dat.tileEntities))
    ))

    val splitter = ShortNibbleArray(dat.blocks)

    val add : NibbleArray = splitter.splitToLeadingArray()
    val blocks = splitter.splitToTrailingArray()

    nbt.byteArray("Blocks", blocks)
    if (!add.empty()) nbt.byteArray("Add", add.rawArray)

    return nbt

}

