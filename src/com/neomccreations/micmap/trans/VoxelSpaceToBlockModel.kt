package com.neomccreations.micmap.trans

import com.google.gson.stream.JsonWriter
import com.neomccreations.micmap.util.*
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
