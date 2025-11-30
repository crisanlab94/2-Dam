#Cristina Sandoval Laborde

import sys
from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QLineEdit, QComboBox,
    QRadioButton, QTextEdit, QLabel, QButtonGroup,
    QFormLayout, QVBoxLayout, QHBoxLayout,
     QToolBar, QStatusBar, QMessageBox,QStackedLayout,QPushButton
)
from PySide6.QtGui import QKeySequence, QAction
from PySide6.QtCore import QTimer


class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        # ----------------------------
        # Configuración de la ventana
        # ----------------------------
        self.setWindowTitle("Encuesta de satisfación - Compañía de telefonía móvil")
        self.setMinimumSize(800, 600)
        layout_principal = QHBoxLayout()
        componente_principal = QWidget()
        componente_principal.setLayout(layout_principal)
        self.setCentralWidget(componente_principal)


        #Layout derecho
        layout_derecho = QHBoxLayout()
        layout_derecho.addWidget(QPushButton("Datos de la persona"))
        layout_derecho.addWidget(QPushButton("Opiniones sobre el servicio"))
      
        layout_principal.addLayout(layout_derecho)
        # ----------------------------
        # Crear zona central con widgets y layouts
        # ----------------------------
        self.crear_central()
        self.crear_acciones()
        self.crear_menus()
        self.crear_toolbar()
        self.activar_datos()

    # ----------------------------
    # CREAR ZONA CENTRAL
    # ----------------------------
    def crear_central(self):
        # Layout tipo formulario
        Layout_botones = QHBoxLayout()
        boton1= QPushButton("Datos de la persona")
        boton1.clicked.connect(self.activar_datos)
        Layout_botones.addWidget(boton1)
        boton2= QPushButton("Opinion sobre el servicio")
#        boton2.clicked.connect(self.opinion)
        Layout_botones.addWidget(boton2)

   
      
        

     # ----------------------------
    # CREAR ACCIONES
    # ----------------------------
    def crear_acciones(self):
        # Acción iniciar sesion
        self.iniciar_sesion = QAction("Iniciar sesión", self)
        self.iniciar_sesion.triggered.connect(self.sesion)

        # Acción nueva encuesta
        self.accion_limpiar = QAction("Nueva encuesta", self)
        self.accion_limpiar.setShortcut(QKeySequence("Ctrl+L"))
        self.accion_limpiar.triggered.connect(self.nueva_encuesta)

        # Acción salir
        self.accion_salir = QAction("Salir", self)
        self.accion_salir.triggered.connect(self.salir)

        # Acción resumen
        self.accion_resumen = QAction("Ver resumen", self)
        self.accion_resumen.setShortcut(QKeySequence("Ctrl+P"))
        self.accion_resumen.triggered.connect(self.resumen)

        # Acción acerca de 
        self.accion_acerca = QAction("Acerca de...", self)
        self.accion_acerca.setShortcut(QKeySequence("Ctrl+P"))
        self.accion_acerca.triggered.connect(self.acerca_de)

    def activar_datos(self):
        layout_form = QFormLayout()

        # --- NOMBRE ---
        self.nombre = QLineEdit()
        self.nombre.setPlaceholderText("Inicia sesión para rellenar el nombre")
        self.nombre.setMaxLength(30)
        layout_form.addRow("Nombre:", self.nombre)

        # --- TELÉFONO ---
        self.telefono = QLineEdit()
        self.telefono.setPlaceholderText("Número de teléfono")
        self.telefono.setMaxLength(9)
        layout_form.addRow("Teléfono:", self.telefono)
      

        # --- COMPAÑIA ---
        self.compañia = QComboBox()
        self.compañia.addItems(["Orange", "Movistar", "Yoigo", "Vodafone","MoviPlus"])
        layout_form.addRow("Compañía:", self.compañia)

        # --- SATISFACCION ---
        self.satisfaccion = QComboBox()
        self.satisfaccion.addItems(["Muy buena", "Buena", "Media","Baja","Muy baja"])
        layout_form.addRow("Satisfacción global:", self.satisfaccion)

     
        # --- CONTENIDO DE LA NOTA INTERNA---
        self.texto_nota = QTextEdit()
        self.texto_nota.setPlaceholderText("Notas internas sobre esta encuesta...")
        layout_form.addRow("Contenido:", self.texto_nota)

        # --- ETIQUETA DE INFORMACIÓN ---
        self.label_info = QLabel("")
        self.label_info.setStyleSheet("font-weight: bold; margin-top: 10px;")

        # Widget central y layout principal vertical
        widget_central = QWidget()  
        layout_principal = QVBoxLayout()
        layout1= QHBoxLayout()

        # Añadir el formulario y la etiqueta al layout principal
        layout_principal.addLayout(layout_form)
        layout_principal.addWidget(self.label_info)
   

        # Asignar layout al widget central
        widget_central.setLayout(layout_principal)
        self.setCentralWidget(widget_central)

    # ----------------------------
    # CREAR MENÚS
    # ----------------------------
    def crear_menus(self):
        # Menú Archivo
        menu_encuesta = self.menuBar().addMenu("&Encuesta")
        menu_encuesta.addAction(self.iniciar_sesion)
        menu_encuesta.addAction(self.accion_limpiar)
        menu_encuesta.addAction(self.accion_resumen)
        menu_encuesta.addAction(self.accion_salir)


        # Menú Ayuda
        menu_ayuda = self.menuBar().addMenu("&Ayuda")
        menu_ayuda.addAction(self.accion_acerca)


    # ----------------------------
    # CREAR TOOLBAR
    # ----------------------------
    def crear_toolbar(self):
        toolbar = QToolBar("Barra de herramientas")
        self.addToolBar(toolbar)
        toolbar.addAction(self.accion_limpiar)
        toolbar.addAction(self.accion_resumen)
        toolbar.addSeparator
        toolbar.addAction(self.accion_limpiar)
        toolbar.addAction(self.accion_resumen)
    # ----------------------------
    # ACCIONES
    # ----------------------------

    def sesion(self):
        if  self.password.text() == "interfaces":
            self.accept() 
        else:
            QMessageBox.warning(
        self,
        "Error",
        "El usuario o la contraseña son incorrectos"
    )
    def nueva_encuesta(self):
        """Limpiar todos los campos de la nota después de confirmación."""
        respuesta = QMessageBox.question(
            self, "Confirmar", "¿Deseas limpiar la encuesta?",
            QMessageBox.Yes | QMessageBox.No
        )
        if respuesta == QMessageBox.Yes:
            self.nombre.clear()
            self.telefono.clear()
            self.compañia.clear()
            self.satisfaccion.clear()

    def resumen(self):
        """Mostrar cuadro de diálogo 'ver resumen'."""
        QMessageBox.information(
            self, "ver resumen",
            "Nombre: Cristina Sandoval \nCompañia: Móvistar \nSatisfacción: media\nLa persona recomienda la compañia"
        )
           
    def salir(self):
        """Salir de la encuesta con confirmación."""
        respuesta = QMessageBox.question(
            self, "Salir", "¿Deseas salir de la encuesta?",
            QMessageBox.Yes | QMessageBox.No
        )
        if respuesta == QMessageBox.Yes:
            self.close()

    def acerca_de(self):
        """Mostrar cuadro de diálogo 'Acerca de'."""
        QMessageBox.information(
            self, "Acerca de",
            "Encuesta de satisfacción compañia telefonía móvil\nDesarrollo de interfaces\n2º DAM"
        )

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()