from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout, QHBoxLayout, QFormLayout,
    QGridLayout, QGroupBox, QLineEdit, QComboBox, QRadioButton, QDateEdit,
    QTimeEdit, QDateTimeEdit, QSpinBox, QDoubleSpinBox, QTextEdit, QPlainTextEdit,
    QListWidget, QListWidgetItem, QTreeWidget, QTreeWidgetItem, QTableWidget, QTableWidgetItem,
    QPushButton, QToolButton, QMenuBar, QMenu, QStatusBar, QMessageBox
)
from PySide6.QtCore import Qt, QDate, QTime, QDateTime
from PySide6.QtGui import QKeySequence, QAction
import sys

class GestorTareas(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Gestor de Tareas Completas")
        self.resize(900, 600)

        # --- Widgets de formulario ---
        self.titulo = QLineEdit()
        self.titulo.setPlaceholderText("Título de la tarea")
        self.titulo.setMaxLength(50)

        self.categoria = QComboBox()
        self.categoria.addItems(["Trabajo", "Personal", "Otros"])

        self.prioridad_normal = QRadioButton("Normal")
        self.prioridad_alta = QRadioButton("Alta")
        self.prioridad_normal.setChecked(True)

        self.fecha = QDateEdit(QDate.currentDate())
        self.hora = QTimeEdit(QTime.currentTime())
        self.fecha_hora = QDateTimeEdit(QDateTime.currentDateTime())

        self.subtareas = QSpinBox()
        self.subtareas.setRange(0, 20)

        self.duracion = QDoubleSpinBox()
        self.duracion.setRange(0.0, 24.0)
        self.duracion.setSingleStep(0.5)

        self.descripcion = QTextEdit()
        self.descripcion.setPlaceholderText("Descripción de la tarea")

        self.notas = QPlainTextEdit()
        self.notas.setPlaceholderText("Notas rápidas")

        # --- Visualización de tareas ---
        self.lista = QListWidget()
        self.arbol = QTreeWidget()
        self.arbol.setHeaderLabels(["Categoría", "Título", "Prioridad"])
        self.tabla = QTableWidget()
        self.tabla.setColumnCount(5)
        self.tabla.setHorizontalHeaderLabels(["Título", "Categoría", "Prioridad", "Fecha", "Duración"])

        # --- Botones ---
        self.boton_agregar = QPushButton("Agregar Tarea")
        self.boton_eliminar = QPushButton("Eliminar Seleccionada")
        self.boton_imprimir = QToolButton()
        self.boton_imprimir.setText("Imprimir Tarea")

        # --- Layouts ---
        form_layout = QFormLayout()
        form_layout.addRow("Título:", self.titulo)
        form_layout.addRow("Categoría:", self.categoria)

        prioridad_box = QGroupBox("Prioridad")
        prio_layout = QHBoxLayout()
        prio_layout.addWidget(self.prioridad_normal)
        prio_layout.addWidget(self.prioridad_alta)
        prioridad_box.setLayout(prio_layout)
        form_layout.addRow(prioridad_box)

        form_layout.addRow("Fecha:", self.fecha)
        form_layout.addRow("Hora:", self.hora)
        form_layout.addRow("Fecha+Hora:", self.fecha_hora)
        form_layout.addRow("Subtareas:", self.subtareas)
        form_layout.addRow("Duración (hrs):", self.duracion)
        form_layout.addRow("Descripción:", self.descripcion)
        form_layout.addRow("Notas:", self.notas)

        botones_layout = QHBoxLayout()
        botones_layout.addWidget(self.boton_agregar)
        botones_layout.addWidget(self.boton_eliminar)
        botones_layout.addWidget(self.boton_imprimir)

        left_layout = QVBoxLayout()
        left_layout.addLayout(form_layout)
        left_layout.addLayout(botones_layout)

        right_layout = QVBoxLayout()
        right_layout.addWidget(self.lista)
        right_layout.addWidget(self.arbol)
        right_layout.addWidget(self.tabla)

        main_layout = QHBoxLayout()
        main_layout.addLayout(left_layout, 2)
        main_layout.addLayout(right_layout, 3)

        central_widget = QWidget()
        central_widget.setLayout(main_layout)
        self.setCentralWidget(central_widget)

        # --- Barra de estado ---
        self.status = QStatusBar()
        self.setStatusBar(self.status)
        self.status.showMessage("Gestor de tareas listo")

        # --- Menú ---
        menu_bar = QMenuBar()
        archivo_menu = QMenu("Archivo", self)
        ayuda_menu = QMenu("Ayuda", self)

        agregar_action = QAction("Agregar Tarea", self)
        eliminar_action = QAction("Eliminar Seleccionada", self)
        imprimir_action = QAction("Imprimir Tarea", self)
        salir_action = QAction("Salir", self)
        acerca_action = QAction("Acerca de...", self)

        archivo_menu.addAction(agregar_action)
        archivo_menu.addAction(eliminar_action)
        archivo_menu.addAction(imprimir_action)
        archivo_menu.addSeparator()
        archivo_menu.addAction(salir_action)
        ayuda_menu.addAction(acerca_action)

        menu_bar.addMenu(archivo_menu)
        menu_bar.addMenu(ayuda_menu)
        self.setMenuBar(menu_bar)

        # --- Conexiones ---
        self.boton_agregar.clicked.connect(self.agregar_tarea)
        self.boton_eliminar.clicked.connect(self.eliminar_tarea)
        self.boton_imprimir.clicked.connect(self.imprimir_tarea)
        agregar_action.triggered.connect(self.agregar_tarea)
        eliminar_action.triggered.connect(self.eliminar_tarea)
        imprimir_action.triggered.connect(self.imprimir_tarea)
        salir_action.triggered.connect(self.close)
        acerca_action.triggered.connect(self.acerca_de)

    # --- Funciones ---
    def agregar_tarea(self):
        titulo = self.titulo.text()
        categoria = self.categoria.currentText()
        prioridad = "Alta" if self.prioridad_alta.isChecked() else "Normal"
        fecha = self.fecha.date().toString("yyyy-MM-dd")
        hora = self.hora.time().toString("HH:mm")
        subtareas = self.subtareas.value()
        duracion = self.duracion.value()
        descripcion = self.descripcion.toPlainText()
        notas = self.notas.toPlainText()

        if not titulo:
            QMessageBox.warning(self, "Error", "El título es obligatorio")
            return

        # Agregar a QListWidget
        item_text = f"{titulo} ({categoria}, {prioridad})"
        self.lista.addItem(item_text)

        # Agregar a QTreeWidget por categoría
        raiz = None
        for i in range(self.arbol.topLevelItemCount()):
            if self.arbol.topLevelItem(i).text(0) == categoria:
                raiz = self.arbol.topLevelItem(i)
                break
        if not raiz:
            raiz = QTreeWidgetItem([categoria])
            self.arbol.addTopLevelItem(raiz)
        hijo = QTreeWidgetItem([categoria, titulo, prioridad])
        raiz.addChild(hijo)

        # Agregar a QTableWidget
        row = self.tabla.rowCount()
        self.tabla.insertRow(row)
        self.tabla.setItem(row, 0, QTableWidgetItem(titulo))
        self.tabla.setItem(row, 1, QTableWidgetItem(categoria))
        self.tabla.setItem(row, 2, QTableWidgetItem(prioridad))
        self.tabla.setItem(row, 3, QTableWidgetItem(f"{fecha} {hora}"))
        self.tabla.setItem(row, 4, QTableWidgetItem(str(duracion)))

        self.status.showMessage(f"Tarea agregada: {titulo}")

    def eliminar_tarea(self):
        item = self.lista.currentItem()
        if item:
            respuesta = QMessageBox.question(self, "Eliminar", f"¿Eliminar '{item.text()}'?")
            if respuesta == QMessageBox.Yes:
                row = self.lista.row(item)
                self.lista.takeItem(row)
                self.status.showMessage("Tarea eliminada")

    def imprimir_tarea(self):
        print("=== Lista de tareas ===")
        for i in range(self.tabla.rowCount()):
            fila = [self.tabla.item(i, j).text() for j in range(self.tabla.columnCount())]
            print(fila)
        self.status.showMessage("Tareas impresas en consola")

    def acerca_de(self):
        QMessageBox.information(self, "Acerca de", "Gestor de Tareas - Proyecto de repaso completo")

# --- Ejecutar aplicación ---
if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = GestorTareas()
    ventana.show()
    sys.exit(app.exec())
