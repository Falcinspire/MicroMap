/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.voxel

/**
 * @author Falcinspire
 * @version Apr/29/2017 (8:29 PM)
 */

data class Voxel(var opaque : Boolean,
                 var north : Boolean = true,
                 var south : Boolean = true,
                 var east : Boolean = true,
                 var west : Boolean = true,
                 var up : Boolean = true,
                 var down : Boolean = true) {

    fun empty() : Boolean {

        return (!(north || south || east || west || up || down))
    }

    fun setNotOpaqueIfEmpty() {

        if  (!(north || south || east || west || up || down)) {
            opaque = false
        }
    }

}
    