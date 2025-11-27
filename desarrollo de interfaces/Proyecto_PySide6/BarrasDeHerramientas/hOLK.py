import sys
from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QLineEdit, QComboBox,
    QRadioButton, QTextEdit, QLabel, QButtonGroup,
    QFormLayout, QVBoxLayout, QHBoxLayout,
     QToolBar, QStatusBar, QMessageBox,QStackedLayout,QPushButton,QDialog
)
from PySide6.QtGui import QKeySequence, QAction
from PySide6.QtCore import QTimer

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Encuesta de satisfación - Compañía de telefonía móvil")
        self.setMinimumSize(800, 600)

        #Layout horizontal
        layout_principal = QHBoxLayout()
        componente_principal = QWidget()
        componente_principal.setLayout(layout_principal)
        self.setCentralWidget(componente_principal)
      

        #Layout derecho
        layout_derecho = QHBoxLayout()
        boton1=QPushButton("Datos de la persona")
        boton1.clicked.connect(self.activar_capa1)
        layout_derecho.addWidget(QPushButton("Datos de la persona"))
        layout_derecho.addWidget(QPushButton("Opinion sobre el servicio"))
        
        


        # Añadimos los layouts secundarios al layout principal
        layout_principal.addLayout(layout_derecho)

     # ----------------------------
     # Crear zona central con widgets y layouts
     # ----------------------------
        #self.crear_central()
        self.crear_acciones()
        self.crear_menus()
        self.crear_toolbar()
        self.activar_datos()

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
    def activar_capa1(self):
        self

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
        layout_form.addRow("Notas internas:", self.texto_nota)

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
            "Encuesta de satisfacción compañia telefonía móvil\n Desarrollo de interfaces\n 2º DAM"
        )


'''
#Pestaña 2- Opinion
#No se como se ponen 2 pestañas lo he ntenado y nada
    def pestaña_2(self):
            layout_form = QFormLayout()

    # --- Calidad de la cobertura ---
            self.calidad = QComboBox()
            self.calidad.addItems(["Buena", "Mala", "Regular"])
            layout_form.addRow("Calidad:", self.compañia)
    # --- velocidad de datos ---
            self.velocidad = QComboBox()
            self.velocidad.addItems(["Buena", "Mala", "Regular"])
            layout_form.addRow("Velocidad:", self.compañia)
            # --- atencion al cliente ---
            self.atencion = QComboBox()
            self.atencion.addItems(["Buena", "Mala", "Regular"])
            layout_form.addRow("Atención:", self.compañia)
    # --- relacion calidad precio ---
            self.relacion = QComboBox()
            self.relacion.addItems(["Buena", "Mala", "Regular"])
            layout_form.addRow("relacion calidad-precio:", self.compañia)

       # --- Recomendacion ---
            self.radio_si = QRadioButton("Si")
            self.radio_no = QRadioButton("No")
            self.radio_si.setChecked(True)

            '''


class DialogoLogin(QDialog):
    def __init__(self, parent=None):
        super().__init__(parent)

        self.setWindowTitle("Iniciar sesión")


        
app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()