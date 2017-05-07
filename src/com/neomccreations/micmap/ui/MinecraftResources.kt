package com.neomccreations.micmap.ui

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author Falcinspire
 * @version May/05/2017 (10:20 PM)
 */

class MinecraftResources {

    inline fun parseModelsList() : Array<String> {
        return parseList(javaClass.getResourceAsStream("/blocks.txt"))
    }

    inline fun parseTexturesList() : Array<String> {
        return parseList(javaClass.getResourceAsStream("/textures.txt"))
    }

    fun parseList(ins : InputStream) : Array<String> {
        val reader = BufferedReader(InputStreamReader(ins))
        val count = Integer.parseInt(reader.readLine())
        val array = Array<String>(count) { index ->
            reader.readLine()
        }
        reader.close()
        return array
    }
}