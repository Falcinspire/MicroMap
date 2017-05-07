/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.voxel.optimize

import com.neomccreations.micmap.coordutil.xyzToIndex
import com.neomccreations.micmap.voxel.VoxelSpace

/**
 * @author Falcinspire
 * @version May/07/2017 (4:59 PM)
 */

class CullOptimization : Optimization {

    override fun optimize(space: VoxelSpace) {

        val width = space.width
        val length = space.length
        val height = space.height
        val array = space.space

        for (y in 0 until height) {
            for (z in 0 until length) {
                for (x in 0 until width) {
                    val rawIndex = xyzToIndex(x, y, z, width, length)

                    val voxel = array[rawIndex]
                    if (voxel.opaque) {

                        voxel.south = !((z + 1 < length) && array[xyzToIndex(x, y, z + 1, width, length)].opaque)
                        voxel.north = !((z - 1 >= 0) && array[xyzToIndex(x, y, z - 1, width, length)].opaque)
                        voxel.east = !((x + 1 < width) && array[xyzToIndex(x + 1, y, z, width, length)].opaque)
                        voxel.west = !((x - 1 >= 0) && array[xyzToIndex(x - 1, y, z, width, length)].opaque)
                        voxel.up = !((y + 1 < height) && array[xyzToIndex(x, y + 1, z, width, length)].opaque)
                        voxel.down = !((y - 1 >= 0) && array[xyzToIndex(x, y - 1, z, width, length)].opaque)

                    }
                }
            }
        }
    }


}