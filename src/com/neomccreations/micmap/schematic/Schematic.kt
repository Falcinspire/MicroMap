package com.neomccreations.micmap.schematic

import com.neomccreations.micmap.NibbleArray
import com.neomccreations.micmap.ShortNibbleArray
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
        return blocks[xyzToIndex(x, y, z)]
    }

    fun block(x : Int, y : Int, z : Int, id : Short, data : Byte = 0) {
        val index = xyzToIndex(x, y, z)
        blocks[index] = id
        blockData[index] = data
    }

    fun forAllBlocks(operation : (x: Int, y: Int, z: Int, index: Int, id: Short, data: Byte) -> Unit) {
        for (y in 0 until height) {
            for (z in 0 until length) {
                for (x in 0 until width) {
                    val rawIndex = xyzToIndex(x, y, z)
                    operation(x, y, z, rawIndex, blocks[rawIndex], blockData[rawIndex])
                }
            }
        }
    }

    //TODO repeated in VoxelSpace
    inline fun xyzToIndex(x : Int, y : Int, z : Int) : Int {
        return y * width * length + z * width + x
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

