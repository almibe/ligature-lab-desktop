/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.lab.desktop

import java.awt.Dimension

import javax.swing.{JFrame, UIManager, WindowConstants}

object LigatureLabDesktop {
  def main(args: Array[String]): Unit = {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName);

    val mainWindow = new MainWindow()
    mainWindow.startUp()
  }

  class MainWindow {
    private val frame = new JFrame("Library Weasel")

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.setSize(new Dimension(1200,800))

    def startUp() {
      frame.show()
    }
  }
}
