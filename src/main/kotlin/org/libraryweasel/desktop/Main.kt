/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.libraryweasel.desktop

import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.UIManager

fun main() {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    val mainWindow = MainWindow()
    mainWindow.startUp()
}

class MainWindow {
    private val frame = JFrame("Library Weasel")

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.size = Dimension(1200,800)
    }

    fun startUp() {
        frame.show()
    }
}
