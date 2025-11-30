from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout, QHBoxLayout, QFormLayout,
    QLineEdit, QPlainTextEdit, QComboBox, QRadioButton, QProgressBar,
    QLCDNumber, QToolButton, QGroupBox, QTabWidget, QMessageBox, QStatusBar,QTextEdit,QToolBar,QButtonGroup
)
from PySide6.QtGui import QKeySequence, QAction
from PySide6.QtCore import Qt
import sys

class PanelProyectos(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Encuesta de satisfación - Compañía de telefonía móvil")
        self.setMinimumSize(800, 600)

      

  # ----------------------------
     # Crear zona central con widgets y layouts
     # ----------------------------
        #self.crear_central()
        self.crear_acciones()
        self.crear_menus()
        self.crear_toolbar()
    



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
        self.accion_resumen.setShortcut(QKeySequence("Ctrl+R"))
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

        # Widgets generales
        self.nombre = QLineEdit()
        self.nombre.setPlaceholderText("Inicia sesión para rellenar el nombre")
        self.nombre.setMaxLength(30)
      

        self.telefono = QLineEdit()
        self.telefono.setPlaceholderText("Número de teléfono")
        self.telefono.setMaxLength(9)
       

        self.compañia = QComboBox()
        self.compañia.addItems(["Orange", "Movistar", "Yoigo", "Vodafone","MoviPlus"])
       
        self.satisfaccion = QComboBox()
        self.satisfaccion.addItems(["Muy buena", "Buena", "Media","Baja","Muy baja"])

        self.texto_nota = QTextEdit()
        self.texto_nota.setPlaceholderText("Notas internas sobre esta encuesta...")



        # --- Calidad de la cobertura ---
        self.calidad = QComboBox()
        self.calidad.addItems(["Buena", "Mala", "Regular"])
       
# --- velocidad de datos ---
        self.velocidad = QComboBox()
        self.velocidad.addItems(["Buena", "Mala", "Regular"])
     
        # --- atencion al cliente ---
        self.atencion = QComboBox()
        self.atencion.addItems(["Buena", "Mala", "Regular"])
       
# --- relacion calidad precio ---
        self.relacion = QComboBox()
        self.relacion.addItems(["Buena", "Mala", "Regular"])
   

    # --- Recomendacion ---
       
        self.radio_si = QRadioButton("Si")
        self.radio_no = QRadioButton("No")
        self.radio_si.setChecked(True)

        # Grupo de botones para que solo se pueda seleccionar uno
        self.grupo_recomendacion = QButtonGroup()
        self.grupo_recomendacion.addButton(self.radio_si)
        self.grupo_recomendacion.addButton(self.radio_no)

        

       
        # Formulario general
        form_layout = QFormLayout()
        form_layout.addRow("Nombre:", self.nombre)
        form_layout.addRow("Telefono:", self.telefono)
        form_layout.addRow("Compañia:", self.compañia)
        form_layout.addRow("Satisfacción", self.satisfaccion)

        form2_layout= QFormLayout()
        layout_prioridad = QHBoxLayout()
        form2_layout.addRow("Calidad de la cobertura:", self.calidad)
        form2_layout.addRow("Velocidad de datos:", self.velocidad)
        form2_layout.addRow("Atención al cliente:", self.atencion)
        form2_layout.addRow("Relación calidad-Precio", self.relacion)
        form2_layout.addRow("¿Recomendarias esta compañia?", layout_prioridad)

     

        # Pestañas
        tabs = QTabWidget()
        tab_general = QWidget()
        tab_detalles = QWidget()

        layout_tab_general = QVBoxLayout()
        layout_tab_general.addLayout(form_layout)

        layout_tab_detalles=QVBoxLayout()
        layout_tab_detalles.addLayout(form2_layout)

        tab_general.setLayout(layout_tab_general)
        tab_detalles.setLayout(layout_tab_detalles)

        tabs.addTab(tab_general, "Datos de la persona")
        tabs.addTab(tab_detalles, "Opinion sobre el servicio")

        self.setCentralWidget(tabs)

        # Barra de estado
        self.status = QStatusBar()
        self.setStatusBar(self.status)
        self.status.showMessage("Panel  listo")

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

# Ejecutar aplicación
if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = PanelProyectos()
    ventana.show()
    sys.exit(app.exec())
