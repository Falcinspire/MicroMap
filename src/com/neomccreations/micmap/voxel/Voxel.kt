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
    