package com.neomccreations.micmap.util

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

/**
 * @author Falcinspire
 * @version May/05/2017 (10:24 PM)
 */

fun JsonWriter.objectAround(wrap : (writer : JsonWriter) -> Unit) {
    this.beginObject()
    wrap(this)
    this.endObject()
}

fun JsonWriter.arrayAround(wrap : (writer : JsonWriter) -> Unit) {
    this.beginArray()
    wrap(this)
    this.endArray()
}

fun JsonReader.parseArray(wrap : () -> Unit) {
    this.beginArray()
    while (this.hasNext())
        wrap()
    this.endArray()
}

fun JsonReader.forAllObjectMembers(wrap : (String) -> Unit) {
    this.beginObject()
    while (this.hasNext())
        wrap(this.nextName())
    this.endObject()
}

fun JsonReader.forAll(wrap : () -> Unit) {
    while (this.hasNext())
        wrap()
}