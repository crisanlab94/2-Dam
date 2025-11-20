# Cristina Sandoval Laborde 

import os
from PySide6.QtCore import Qt
from PySide6.QtGui import QAction, QIcon, QKeySequence
from PySide6.QtWidgets import QApplication, QMainWindow, QToolBar

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio 11 T02")

        # Menú
        barra_menus = self.menuBar()
        menu = barra_menus.addMenu("&Archivo")

        # Ruta de los iconos (puedes usar cualquier PNG en la misma carpeta)
        ruta_icono_mensaje = os.path.join(os.path.dirname(__file__), "mensaje.png")
        ruta_icono_titulo = os.path.join(os.path.dirname(__file__), "titulo.png")
        ruta_icono_desactivar = os.path.join(os.path.dirname(__file__), "desactivar.png")
        

        # Acción 1: Mostrar mensaje
        self.accion_mensaje = QAction(QIcon(ruta_icono_mensaje), "Mostrar mensaje", self)
        self.accion_mensaje.setWhatsThis("Muestra 'Hola' en la consola")
        self.accion_mensaje.triggered.connect(self.mostrar_mensaje)

        # Acción 2: Cambiar título
        self.accion_cambiar_titulo = QAction(QIcon(ruta_icono_titulo), "Cambiar título", self)
        self.accion_cambiar_titulo.setWhatsThis("Cambia el título de la ventana")
        self.accion_cambiar_titulo.triggered.connect(self.cambiar_titulo)

        # Acción 3: Desactivar acciones
        self.accion_desactivar = QAction(QIcon(ruta_icono_desactivar), "Desactivar acciones", self)
        self.accion_desactivar.setWhatsThis("Desactiva las acciones anteriores")
        self.accion_desactivar.triggered.connect(self.desactivar_acciones)

        # Añadir acciones al menú
        menu.addAction(self.accion_mensaje)
        menu.addAction(self.accion_cambiar_titulo)
        menu.addAction(self.accion_desactivar)

        # Barra de herramientas principal
        self.barra_principal = QToolBar("Barra principal")
        self.barra_principal.setToolButtonStyle(Qt.ToolButtonTextUnderIcon) 
        self.barra_principal.addAction(self.accion_mensaje)
        self.barra_principal.addAction(self.accion_cambiar_titulo)
        self.barra_principal.addAction(self.accion_desactivar)
        self.addToolBar(self.barra_principal)

        # Barra de herramientas secundaria
        self.barra_secundaria = QToolBar("Barra secundaria")
        self.accion_activar = QAction("Activar acciones", self)
        self.accion_activar.setWhatsThis("Vuelve a activar las acciones desactivadas")
        self.accion_activar.triggered.connect(self.activar_acciones)
        self.barra_secundaria.addAction(self.accion_activar)
        self.addToolBar(self.barra_secundaria)

  
    def mostrar_mensaje(self):
        print("Hola")

    def cambiar_titulo(self):
        self.setWindowTitle("Título cambiado")

    def desactivar_acciones(self):
        self.accion_mensaje.setEnabled(False)
        self.accion_cambiar_titulo.setEnabled(False)

    def activar_acciones(self):
        self.accion_mensaje.setEnabled(True)
        self.accion_cambiar_titulo.setEnabled(True)

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()