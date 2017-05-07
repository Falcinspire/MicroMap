/*
 * Copyright 2017 Masteroreo77
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.neomccreations.micmap.ui

import com.neomccreations.micmap.schematic.schematicFromFile
import com.neomccreations.micmap.trans.VoxelSpaceToBlockModel
import com.neomccreations.micmap.voxel.optimize.CullOptimization
import com.neomccreations.micmap.voxel.voxelSpaceFromSchematic
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Control
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.FileChooser
import org.controlsfx.control.MaskerPane
import org.controlsfx.control.textfield.TextFields
import java.io.File
import java.net.URL
import java.util.*

/**
 * @author Falcinspire
 * @version May/05/2017 (6:17 PM)
 */

class Controller : Initializable {

    @FXML private lateinit var input: TextField
    @FXML private lateinit var texture: TextField
    @FXML private lateinit var block: TextField
    @FXML private lateinit var mask: MaskerPane
    @FXML private lateinit var log: Label

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        val res = MinecraftResources()
        TextFields.bindAutoCompletion(block, res.parseModelsList().asList())
        TextFields.bindAutoCompletion(texture, res.parseTexturesList().asList())
    }

    fun openExplorer(event : ActionEvent) {

        val explorer = FileChooser()

        var home = File(input.text)
        if (home.exists())
            explorer.initialDirectory = home.parentFile

        val file = explorer.showOpenDialog((event.source as Control).scene.window)
        if (file != null) input.text = file.absolutePath
    }

    fun convert(event : ActionEvent) {

        mask.isVisible = true

        Thread {

            val schematicFile = File(input.text)
            val schem = schematicFromFile(schematicFile)
            val space = voxelSpaceFromSchematic(schem)
            space.optimize(CullOptimization())

            val file = File(schematicFile.parentFile, block.text + ".json")
            if (!file.exists()) file.createNewFile()
            VoxelSpaceToBlockModel(space).write(file, "blocks/${texture.text}")

            Platform.runLater {

                log.text = "Saved to ${file.absolutePath}"
                mask.isVisible = false
            }

        }.start()

        log.text = "First"
    }

}
    