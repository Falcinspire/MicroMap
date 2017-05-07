/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.jsonutil

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