# Cristina Sandoval Laborde - 2ºDAM

import os
from PySide6.QtGui import QAction, QIcon, QKeySequence
from PySide6.QtWidgets import (
    QApplication, QMainWindow, QToolBar,
    QLabel, QDockWidget, QTextEdit
)
from PySide6.QtCore import Qt

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio - Componentes flotantes")

        # --- COMPONENTE CENTRAL ---
        self.setCentralWidget(QLabel("Área principal de la aplicación"))

        # --- CREACIÓN DE DOCKS ---
        # Panel 1 (izquierda) - Fijo
        dock1 = QDockWidget("Panel 1", self)
        dock1.setWidget(QTextEdit("Panel de notas"))
        dock1.setFeatures(QDockWidget.NoDockWidgetFeatures)
        self.addDockWidget(Qt.LeftDockWidgetArea, dock1)

        # Panel 2 (derecha) - Flotante
        dock2 = QDockWidget("Panel 2", self)
        dock2.setWidget(QLabel("Panel de estado"))
        dock2.setFeatures(QDockWidget.DockWidgetFloatable | QDockWidget.DockWidgetMovable)
        self.addDockWidget(Qt.RightDockWidgetArea, dock2)

        # Panel 3 (abajo) - Flotante y cerrable
        dock3 = QDockWidget("Panel 3", self)
        dock3.setWidget(QLabel("Panel de ayuda"))
        dock3.setFeatures(QDockWidget.DockWidgetFloatable |
                          QDockWidget.DockWidgetClosable |
                          QDockWidget.DockWidgetMovable)
        self.addDockWidget(Qt.BottomDockWidgetArea, dock3)

        # --- BARRA DE ESTADO ---
        barra_estado = self.statusBar()
        barra_estado.showMessage("Listo. Paneles creados correctamente.", 3000)

        # --- BARRA DE HERRAMIENTAS ---
        barra_herramientas = QToolBar("Barra principal")
        self.addToolBar(barra_herramientas)

        # Ruta del icono
        ruta_icono = os.path.join(os.path.dirname(__file__), "xrp.png")

        # Acción imprimir por consola
        accion_imprimir = QAction(QIcon(ruta_icono), "Imprimir por consola", self)
        accion_imprimir.setShortcut(QKeySequence("Ctrl+P"))
        accion_imprimir.triggered.connect(self.imprimir_por_consola)

        barra_herramientas.addAction(accion_imprimir)

    def imprimir_por_consola(self):
        print("Acción lanzada desde la barra de herramientas.")


app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()
