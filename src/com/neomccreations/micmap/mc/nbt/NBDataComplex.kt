package com.neomccreations.micmap.mc.nbt

/**
 * @author Falcinspire
 * @version Apr/24/2017 (8:37 PM)
 */

class ListData<T : Data>(val values: MutableList<T>) : Data {

    override val type = NBTypes.LIST

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

}

class CompoundData(val values: MutableList<NBTag>) : Data {

    override val type = NBTypes.COMPOUND

    override fun visit(vis: DataVisitor) {
        vis.visit(this)
    }

    fun tag(name: String) : NBTag? {
        for (value in values) if (value.name == name) return value
        return null
    }

    /* Utility class to map a tag to it's index*/
    private inner class TagIndex(val tag : NBTag, val index : Int)

    private fun tagIndex(name : String) : TagIndex? {
        for (index in values.indices) {
            val tag = values[index]
            if (tag.name == name) {
                return TagIndex(tag, index)
            };
        }
        return null
    }

    fun byte(name : String) : Byte {
        val tag = tag(name)!!
        if (tag.dat is ByteData) return tag.dat.value
        else throw  ClassCastException("Expected ByteData for '${name}' but was type " + tag.dat.type.name)
    }

    fun byte(name : String, value : Byte, replace : Boolean = true) {

        val newTag = NBTag(name, ByteData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is ByteData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not ByteData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }

    fun short(name : String) : Short {
        val tag = tag(name)!!
        if (tag.dat is ShortData) return tag.dat.value
        else throw  ClassCastException("Expected ShortData for '${name}' but was type " + tag.dat.type.name)
    }

    fun short(name : String, value : Short, replace : Boolean = true) {

        val newTag = NBTag(name, ShortData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is ShortData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not ShortData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }


    fun int(name : String) : Int {
        val tag = tag(name)!!
        if (tag.dat is IntData) return tag.dat.value
        else throw  ClassCastException("Expected IntData for '${name}' but was type " + tag.dat.type.name)
    }

    fun int(name : String, value : Int, replace : Boolean = true) {

        val newTag = NBTag(name, IntData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is IntData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not IntData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }


    fun long(name : String) : Long {
        val tag = tag(name)!!
        if (tag.dat is LongData) return tag.dat.value
        else throw  ClassCastException("Expected LongData for '${name}' but was type " + tag.dat.type.name)
    }

    fun long(name : String, value : Long, replace : Boolean = true) {

        val newTag = NBTag(name, LongData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is LongData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not LongData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }


    fun float(name : String) : Float {
        val tag = tag(name)!!
        if (tag.dat is FloatData) return tag.dat.value
        else throw  ClassCastException("Expected FloatData for '${name}' but was type " + tag.dat.type.name)
    }

    fun float(name : String, value : Float, replace : Boolean = true) {

        val newTag = NBTag(name, FloatData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is FloatData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not FloatData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }

    fun double(name : String) : Double {
        val tag = tag(name)!!
        if (tag.dat is DoubleData) return tag.dat.value
        else throw  ClassCastException("Expected DoubleData for '${name}' but was type " + tag.dat.type.name)
    }

    fun double(name : String, value : Double, replace : Boolean = true) {

        val newTag = NBTag(name, DoubleData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is DoubleData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not DoubleData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }

    fun string(name : String) : String {
        val tag = tag(name)!!
        if (tag.dat is StringData) return tag.dat.value
        else throw  ClassCastException("Expected StringData for '${name}' but was type " + tag.dat.type.name)
    }

    fun string(name : String, value : String, replace : Boolean = true) {

        val newTag = NBTag(name, StringData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is StringData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not StringData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }


    fun byteArray(name : String) : ByteArray {
        val tag = tag(name)!!
        if (tag.dat is ByteArrayData) return tag.dat.value
        else throw  ClassCastException("Expected ByteArrayData for '${name}' but was type " + tag.dat.type.name)
    }

    fun byteArray(name : String, value : ByteArray, replace : Boolean = true) {

        val newTag = NBTag(name, ByteArrayData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is ByteArrayData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not ByteArrayData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Data> list(name : String) : MutableList<T> {
        val tag = tag(name)!!
        if (tag.dat is ListData<*>) return tag.dat.values as MutableList<T>
        else throw  ClassCastException("Expected List for '${name}' but was type " + tag.dat.type.name)
    }

    fun list(name : String, value : MutableList<Data>, replace : Boolean = true) {

        val newTag = NBTag(name, ListData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is ListData<*>) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not ListData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }

    fun compound(name : String) : CompoundData {
        val tag = tag(name)!!
        if (tag.dat is CompoundData) return tag.dat
        else throw  ClassCastException("Expected CompoundData for '${name}' but was type " + tag.dat.type.name)
    }

    fun compound(name : String, value : MutableList<NBTag>, replace : Boolean = true) {

        val newTag = NBTag(name, CompoundData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is CompoundData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not CompoundData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }


    fun intArray(name : String) : IntArray {
        val tag = tag(name)!!
        if (tag.dat is IntArrayData) return tag.dat.values
        else throw  ClassCastException("Expected IntArrayData for '${name}' but was type " + tag.dat.type.name)
    }

    fun intArray(name : String, value : IntArray, replace : Boolean = true) {

        val newTag = NBTag(name, IntArrayData(value))

        val tagPair = tagIndex(name)

        if (replace) {
            if (tagPair == null) throw IllegalStateException("The tag '$name' does not exist; it cannot be replaced")
            if (tagPair.tag.dat !is IntArrayData) throw ClassCastException("The tag '$name' is of type ${tagPair.tag.dat.type.name}, not IntArrayData")

            values[tagPair.index] = newTag
        }
        else {
            if (tagPair == null)
                values.add(newTag)
            else
                values[tagPair.index] = newTag
        }
    }

    fun byteArrayIfExists(name : String) : ByteArray? {
        val tag = tag(name)
        if (tag != null) {
            if (tag.dat is ByteArrayData) return tag.dat.value
            else throw  ClassCastException("Expected ByteArrayData for '${name}' but was type " + tag.dat.type.name)
        }
        else {
            return null
        }
    }
}