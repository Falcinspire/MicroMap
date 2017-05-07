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

import com.neomccreations.micmap.mc.nbt.*
import com.neomccreations.micmap.schematic.schematicFromFile
import com.neomccreations.micmap.trans.VoxelSpaceToBlockModel
import com.neomccreations.micmap.voxel.voxelSpaceFromSchematic
import java.io.*
import javax.swing.JFileChooser

/**
 * @author Falcinspire
 * @version Apr/21/2017 (4:27 PM)
 */

fun main(args: Array<String>) {

    test_converter()

}

fun actually_run(args: Array<String>) {
    val schameticLocation = args[0]
    val outputLocation = args[1]
    val block = args[2]

    val schem = schematicFromFile(File(schameticLocation))
    val space = voxelSpaceFromSchematic(schem)
    space.cullOptimize()
    val file = File(outputLocation)
    if (!file.exists()) file.createNewFile()
    VoxelSpaceToBlockModel(space).write(file, block)
}

fun test_converter() {

    val chooser = JFileChooser()
    chooser.showDialog(null, "Pick ur poison")

    val schem = schematicFromFile(chooser.selectedFile)

    val space = voxelSpaceFromSchematic(schem)

    space.cullOptimize()

    val file = File("C:/Users/Arceege/Test.txt")
    if (!file.exists()) file.createNewFile()

    VoxelSpaceToBlockModel(space).write(file, "blocks")
}

fun test_optimizer() {

    val chooser = JFileChooser()
    chooser.showDialog(null, "Pick ur poison")

    val schem = schematicFromFile(chooser.selectedFile)

    val space = voxelSpaceFromSchematic(schem)

    for (z in 0 until space.length) {
        for (x in 0 until space.width) {
            val rawIndex = space.xyzToIndex(x, 10, z)

            print(if (space.space[rawIndex].opaque) "■" else "□")

        }
        println()
    }

    println()

    space.cullOptimize()

    for (z in 0 until space.length) {
        for (x in 0 until space.width) {
            val rawIndex = space.xyzToIndex(x, 10, z)

            print(if (space.space[rawIndex].opaque) "■" else "□")

        }
        println()
    }
}

fun test_schematic() {
    val chooser = JFileChooser()
    chooser.showDialog(null, "Pick ur poison")

    for (entity in schematicFromFile(chooser.selectedFile).entities[0].values) {
        println(entity.name)
    }
}

fun test_pretty_printer() {

    val tag = NBTag("Level",
            NBTag("Name", "Freddy"),
            NBTag("Age", 1),
            NBTag("Married", false),
            NBTag("PaidHistory", intArrayOf(20, 3, 10, 20, 100, 40)),
            NBTag("Misc",
                    NBTag("Name", "Freddy"),
                    NBTag("Age", 1)),
            NBTag("Friends", IntData(4), FloatData(3.0f))
    )
    if (tag.dat is CompoundData) {
        tag.dat.string("Names", "Jennifer", replace = false)
    }

    val printer = PrettyPrinter()
    printer.visit(tag)

}

fun test_reader_and_writer() {

    val tag = NBTag("Level",
            NBTag("Name", "Freddy"),
            NBTag("Age", 1),
            NBTag("Married", false),
            NBTag("PaidHistory", intArrayOf(20, 3, 10, 20, 100, 40)),
            NBTag("Misc",
                    NBTag("Name", "Freddy"),
                    NBTag("Age", 1)),
            NBTag("Friends", IntData(4), FloatData(3.0f))
    )
    if (tag.dat is CompoundData) {
        tag.dat.string("Name", "Jennifer")
    }

    PrettyPrinter().visit(tag)

    val file = File("C:/Users/Arceege/Desktop/testnbt.nbt")
    if (!file.exists()) file.createNewFile()
    BinaryWriter(DataOutputStream(FileOutputStream(file))).write(tag)
    val tagread = BinaryReader(DataInputStream(FileInputStream(file))).read()

    PrettyPrinter().visit(tagread)
}

    