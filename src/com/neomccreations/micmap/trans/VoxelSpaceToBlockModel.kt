/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.trans

import com.google.gson.stream.JsonWriter
import com.neomccreations.micmap.jsonutil.arrayAround
import com.neomccreations.micmap.jsonutil.objectAround
import com.neomccreations.micmap.voxel.VoxelSpace
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

/**
 * @author Falcinspire
 * @version Apr/30/2017 (10:18 AM)
 */

class VoxelSpaceToBlockModel(val space : VoxelSpace) {

    fun write(file : File, texture : String = "blocks/web") {

        val writer = JsonWriter(OutputStreamWriter(FileOutputStream(file)))

        val modelWidth = 48
        val max2D = if (space.width > space.height) space.width else space.height
        val maxLen = if (space.height > max2D) space.height else max2D
        val vSize : Float = modelWidth.toFloat() / maxLen.toFloat()

        writer.objectAround {
            writer.name("parent").value("block/cube_all")

            writer.name("textures").objectAround {
                writer.name("text").value(texture)
            }

            writer.name("elements").arrayAround {

                space.forAll({x, y, z, index, voxel ->
                    if (voxel.opaque){

                        val fromX = -16 + x * vSize;
                        val fromY = -16 + y * vSize;
                        val fromZ = -16 + z * vSize;

                        val toX = fromX + vSize;
                        val toY = fromY + vSize;
                        val toZ = fromZ + vSize;

                        writer.objectAround {
                            writer.name("from").valueArrayNumb(fromX, fromY, fromZ)
                            writer.name("to").valueArrayNumb(toX, toY, toZ)

                            writer.name("faces").objectAround {
                                if (voxel.up) writer.writeFace("up")
                                if (voxel.down) writer.writeFace("down")
                                if (voxel.north) writer.writeFace("north")
                                if (voxel.south) writer.writeFace("south")
                                if (voxel.east) writer.writeFace("east")
                                if (voxel.west) writer.writeFace("west")
                            }
                        }
                    }
                })
            }
        }

        writer.flush()
        writer.close()
    }
}

fun JsonWriter.writeFace(face : String) : JsonWriter {
    this.name(face).beginObject()

    this.name("texture").value("text")
    this.name("cullface").value(face) //TODO do I need a #?
    this.name("uv").valueArrayNumb(0, 0, 16, 16)

    this.endObject()

    return this
}

fun <T : Number> JsonWriter.valueArrayNumb(vararg args: T) : JsonWriter {
    this.beginArray()
    for (arg in args) this.value(arg)
    this.endArray()
    return this
}
