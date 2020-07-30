/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package dev.ligature.lab.desktop

import java.awt.{BorderLayout, Dimension}
import java.awt.event.ActionEvent

import dev.ligature.{Entity, Ligature, NamedEntity, PersistedStatement, Predicate, Statement, Object => LigObject}
import dev.ligature.store.inmemory.LigatureInMemory
import javax.swing.{BoxLayout, JButton, JFrame, JLabel, JPanel, JScrollPane, JSplitPane, JTable, JTextField, UIManager, WindowConstants}
import javax.swing.table.DefaultTableModel

object LigatureLabDesktop {
  def main(args: Array[String]): Unit = {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName);

    val mainWindow = new MainWindow()
    mainWindow.startUp()
  }
}

class MainWindow {
  private val store = new LigatureInMemory()

  private val frame = new JFrame("ligatureLab")

  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setSize(new Dimension(1200,800))

  def startUp() {
    val mp = new MainPane(store)
    frame.setContentPane(mp.p)
    frame.setVisible(true)
    mp.resize()
  }
}

class MainPane(private val store: Ligature) {
  val p = new JSplitPane()
  p.setOrientation(JSplitPane.VERTICAL_SPLIT)
  private val contentPane = new ContentPane(store)
  private val entryPane = new EntryPane(store, contentPane)
  p.setTopComponent(entryPane.p)
  p.setBottomComponent(contentPane.p)

  def resize(): Unit = {
    p.setDividerLocation(.2)
  }
}

class EntryPane(val store: Ligature, contentPane: ContentPane) {
  private val collectionLabel = new JLabel("Collection:")
  private val subjectLabel = new JLabel("Subject:")
  private val predicateLabel = new JLabel("Predicate:")
  private val objectLabel = new JLabel("Object:")

  private val collectionTextBox = new JTextField("test", 20)
  private val subjectTextBox = new JTextField(20)
  private val predicateTextBox = new JTextField(20)
  private val objectTextBox = new JTextField(20)

  private val buttonPanel = new JPanel()
  private val buttonLayout = new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS)

  private val addButton = new JButton("Add")
  private val searchButton = new JButton("Search")
  private val clearButton = new JButton("Clear")
//  private val dumpButton = new JButton("Dump")

  buttonPanel.add(addButton)
  buttonPanel.add(searchButton)
  buttonPanel.add(clearButton)

  val p = new JPanel()

//  p.add(collectionLabel)
//  p.add(collectionTextBox)
  p.add(subjectLabel)
  p.add(subjectTextBox)
  p.add(predicateLabel)
  p.add(predicateTextBox)
  p.add(objectLabel)
  p.add(objectTextBox)
  p.add(buttonPanel)

  addButton.addActionListener { _: ActionEvent =>
    val context = NamedEntity(collectionTextBox.getText())
    val subject = NamedEntity(subjectTextBox.getText())
    val predicate = Predicate(predicateTextBox.getText())
    val `object` = NamedEntity(objectTextBox.getText())

    store.write.use( tx => for {
      _ <- tx.addStatement(
        context, Statement(subject, predicate, `object`))
    } yield ()).unsafeRunSync()

    contentPane.update()
  }

  searchButton.addActionListener { _: ActionEvent =>
    val context: NamedEntity = NamedEntity(collectionTextBox.getText())
    val subject: Option[Entity] = if (subjectTextBox.getText().nonEmpty) Some(NamedEntity(subjectTextBox.getText())) else None
    val predicate: Option[Predicate] = if (predicateTextBox.getText().nonEmpty) Some(Predicate(predicateTextBox.getText())) else None
    val `object`: Option[LigObject] = if (objectTextBox.getText().nonEmpty) Some(NamedEntity(objectTextBox.getText())) else None

    val s = store.compute.use(tx => for {
      s <- tx.matchStatements(context, subject, predicate, `object`)
    } yield s).unsafeRunSync()

    contentPane.update(s)
  }

  clearButton.addActionListener { _: ActionEvent =>
    subjectTextBox.setText("")
    predicateTextBox.setText("")
    objectTextBox.setText("")
  }
}

class ContentPane(private val store: Ligature) {
  val data: Array[Array[AnyRef]] = Array()
  val columnNames: Array[AnyRef] = Array("Statement")

  val tm = new DefaultTableModel(data, columnNames)

  val p = new JPanel(new BorderLayout())
  private val t = new JTable(tm)
  t.setFillsViewportHeight(true)
  private val scrollPane = new JScrollPane()
  scrollPane.setViewportView(t)
  p.add(scrollPane)
  p.add(scrollPane)

  def update(): Unit = {
    while (tm.getRowCount > 0) {
      tm.removeRow(0)
    }

    val collection = NamedEntity("test") //TODO hard coded for now

    val s = store.compute.use( tx => for {
      s <- tx.allStatements(collection)
    } yield s).unsafeRunSync()

    s.foreach { v =>
      println(v.toString)
      val a: Array[Object] = Array(v.toString)
      tm.addRow(a)
    }
  }

  def update(s: Iterable[PersistedStatement]): Unit = {
    while (tm.getRowCount > 0) {
      tm.removeRow(0)
    }

    s.foreach { v =>
      println(v.toString)
      val a: Array[Object] = Array(v.toString)
      tm.addRow(a)
    }
  }
}
