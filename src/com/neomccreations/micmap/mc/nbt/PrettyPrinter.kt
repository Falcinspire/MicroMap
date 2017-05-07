package com.neomccreations.micmap.mc.nbt

import java.io.PrintStream

/**
 * @author Falcinspire
 * @version Apr/22/2017 (1:20 PM)
 */

class PrettyPrinter(val writer : PrintStream = System.out) : DataVisitor {

    /** Tracks the number of indents in the nested structure*/
    private var indent = 0

    /** Not actually part of the visitor pattern, but its needed and it fits, so awesome*/
    fun visit(tag : NBTag) {
        fancyPrint(tag.name + " : ", tabbed = true, newlined = false)
        tag.dat.visit(this)
    }

    override fun visit(dat: IntData) {
        fancyPrint("${dat.value}", tabbed = false, newlined = true)
    }

    override fun visit(dat: ShortData) {
        fancyPrint("${dat.value}s", tabbed = false, newlined = true)
    }

    override fun visit(dat: ByteData) {
        fancyPrint("${dat.value}b", tabbed = false, newlined = true)
    }

    override fun visit(dat: LongData) {
        fancyPrint("${dat.value}l", tabbed = false, newlined = true)
    }

    override fun visit(dat: FloatData) {
        fancyPrint("${dat.value}", tabbed = false, newlined = true)
    }

    override fun visit(dat: DoubleData) {
        fancyPrint("${dat.value}d", tabbed = false, newlined = true)
    }

    override fun visit(dat: StringData) {
        fancyPrint("\"${dat.value}\"", tabbed = false, newlined = true)
    }

    override fun visit(dat: ByteArrayData) {
        val size = dat.value.size

        fancyPrint("byte[" + size + "]", tabbed = false, newlined = true)

    }

    override fun visit(dat: CompoundData) {

        fancyPrint("{ ", tabbed = false, newlined = true);

        indent++
        for (tag in dat.values) { visit(tag); }
        indent--

        fancyPrint("}", tabbed = true, newlined = true);
    }

    override fun visit(dat: ListData<out Data>) {
        fancyPrint("[ ", tabbed = false, newlined = true);

        indent++
        for (otherDat in dat.values)
        {
            //no metadata, but still need to format its absence
            fancyPrint("", tabbed = true, newlined = false)
            otherDat.visit(this)
        }
        indent--

        fancyPrint("]", tabbed = true, newlined = true);
    }

    override fun visit(dat: IntArrayData) {
        val size = dat.values.size

        fancyPrint("int[" + size + "]", tabbed = false, newlined = true)
    }

    /** Prints the message or empty string provided with a saved tabbing and/or a newline */
    private fun fancyPrint(message: String, tabbed: Boolean, newlined: Boolean) {

        if (tabbed) repeat(indent) {writer.print("\t")}

        writer.print(message)

        if (newlined) writer.println();

    }

}
    