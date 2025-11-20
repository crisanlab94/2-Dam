#Cristina Sandoval Laborde 2ºDam

import os
import platform
import getpass
from PySide6.QtGui import QAction, QIcon, QKeySequence
from PySide6.QtWidgets import QApplication, QMainWindow, QToolBar,QLabel
from PySide6.QtCore import QTimer

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio 12")

        # --- MENÚ ---
        barra_menus = self.menuBar()
        menu = barra_menus.addMenu("&Archivo")
        #Ruta del icono
        ruta_icono = os.path.join(os.path.dirname(__file__), "xrp.png")


        #Acciones
        #Mensaje temporal
        self.accion = QAction(QIcon(ruta_icono), "Mostrar mensaje temporal", self)
        self.accion.setShortcut(QKeySequence("Ctrl+T"))
        self.accion.triggered.connect(self.mostrar_mensaje_temporal)
       
        #Limpiar mensaje
        self.accion_limpiar = QAction(QIcon(ruta_icono), "Limpiar mensaje", self)
        self.accion_limpiar.setShortcut(QKeySequence("Ctrl+L"))
        self.accion_limpiar.triggered.connect(self.limpiar_mensaje)

        #Mostrar info sistema 
        self.accion_sistema = QAction(QIcon(ruta_icono), "Mostrar información del sistema", self)
        self.accion_sistema.setShortcut(QKeySequence("Ctrl+I"))
        self.accion_sistema.triggered.connect(self.mostrar_sistema)

        # Añadir al menú
        menu.addAction(self.accion)
        menu.addAction(self.accion_limpiar)
        menu.addAction(self.accion_sistema)

        # Barra de heramientas
        barra_herramientas = QToolBar("Barra principal")
        barra_herramientas.addAction(self.accion)
        barra_herramientas.addAction(self.accion_limpiar)
        barra_herramientas.addAction(self.accion_sistema)
        self.addToolBar(barra_herramientas)

        # Barra de estado
        self.barra_estado=self.statusBar()
        #Mensaje inicial
        self.barra_estado.showMessage("Aplicacion iniciada correctamente...", 3000)
        
        # Añadir etiqueta permanente (nombre de usuario)
        nombre_usuario = getpass.getuser()
        etiqueta_usuario = QLabel("Usuario: " + nombre_usuario)

        self.barra_estado.addPermanentWidget(etiqueta_usuario)
        
        self.barra_estado.clearMessage()

        self.barra_estado.addWidget(QLabel("Sistema: " + platform.system()))


        # Timer para alternar mensajes
        self.estado_toggle = True
        self.timer = QTimer()
        self.timer.timeout.connect(self.mensaje_ciclico)
        self.timer.start(3000) 

        

#Metodos 
    def mostrar_mensaje_temporal(self):
        self.barra_estado.showMessage("Mensaje temporal: desaparece en 3 segundos", 3000)

    def limpiar_mensaje(self):
        self.barra_estado.clearMessage()

    def mostrar_sistema(self):
        sistema = platform.system()
        self.barra_estado.addWidget(QLabel(f"Sistema: {sistema}"))

    # Mensaje alternado por QTimer
    def mensaje_ciclico(self):
        if self.estado_toggle:
            self.barra_estado.showMessage("Esperando acción...")
        else:
            self.barra_estado.showMessage("Listo para trabajar")
        self.estado_toggle = not self.estado_toggle

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()