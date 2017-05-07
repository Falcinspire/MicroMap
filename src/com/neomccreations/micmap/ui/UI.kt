@file:JvmName("UI")

package com.neomccreations.micmap.ui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage


/**
 * @author Falcinspire
 * @version May/05/2017 (5:25 PM)
 */

class UI : Application() {

    //TODO License for ControlsFX
    override fun start(primaryStage: Stage?) {

        val stage = primaryStage!!

        stage.title = "MicroMap"
        stage.isResizable = false

        val root = Group()
        val pane = FXMLLoader.load<Pane>(this.javaClass.getResource("/ui.fxml"))

        stage.scene = Scene(pane)
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(UI::class.java)
        }
    }

}
    