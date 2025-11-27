import sys
from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QLineEdit, QComboBox,
    QRadioButton, QTextEdit, QLabel, QButtonGroup,
    QFormLayout, QVBoxLayout, QHBoxLayout,
     QToolBar, QStatusBar, QMessageBox
)
from PySide6.QtGui import QKeySequence, QAction
from PySide6.QtCore import QTimer


class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        # ----------------------------
        # Configuración de la ventana
        # ----------------------------
        self.setWindowTitle("Mini Bloc de Notas")
        self.setMinimumSize(500, 400)

        # ----------------------------
        # Crear zona central con widgets y layouts
        # ----------------------------
        self.crear_central()

        # ----------------------------
        # Crear acciones, menú, toolbar y statusbar
        # ----------------------------
        self.crear_acciones()
        self.crear_menus()
        self.crear_toolbar()
        self.crear_statusbar()

        # ----------------------------
        # Conectar señales de los widgets
        # ----------------------------
        self.conectar_senales()

    # ----------------------------
    # CREAR ZONA CENTRAL
    # ----------------------------
    def crear_central(self):
        # Widget central y layout principal vertical
        widget_central = QWidget()  
        layout_principal = QVBoxLayout()

        # Layout tipo formulario
        layout_form = QFormLayout()

        # --- TÍTULO ---
        self.titulo = QLineEdit()
        self.titulo.setPlaceholderText("Introduce el título")
        self.titulo.setMaxLength(30)
        layout_form.addRow("Título de la nota:", self.titulo)

        # --- CATEGORÍA ---
        self.combo = QComboBox()
        self.combo.addItems(["Trabajo", "Ideas", "Otros"])
        layout_form.addRow("Categoría:", self.combo)

        # --- PRIORIDAD ---
        self.radio_alta = QRadioButton("Alta")
        self.radio_normal = QRadioButton("Normal")
        self.radio_normal.setChecked(True)

        # Grupo de botones para que solo se pueda seleccionar uno
        self.grupo_prioridad = QButtonGroup()
        self.grupo_prioridad.addButton(self.radio_alta)
        self.grupo_prioridad.addButton(self.radio_normal)

        # Layout horizontal para radio buttons
        layout_prioridad = QHBoxLayout()
        layout_prioridad.addWidget(self.radio_alta)
        layout_prioridad.addWidget(self.radio_normal)
        layout_form.addRow("Prioridad:", layout_prioridad)

        # --- CONTENIDO DE LA NOTA ---
        self.texto_nota = QTextEdit()
        self.texto_nota.setPlaceholderText("Escribe aquí el contenido de la nota...")
        layout_form.addRow("Contenido:", self.texto_nota)

        # --- ETIQUETA DE INFORMACIÓN ---
        self.label_info = QLabel("")
        self.label_info.setStyleSheet("font-weight: bold; margin-top: 10px;")

        # Añadir el formulario y la etiqueta al layout principal
        layout_principal.addLayout(layout_form)
        layout_principal.addWidget(self.label_info)

        # Asignar layout al widget central
        widget_central.setLayout(layout_principal)
        self.setCentralWidget(widget_central)

    # ----------------------------
    # CREAR ACCIONES
    # ----------------------------
    def crear_acciones(self):
        # Acción imprimir nota
        self.accion_imprimir = QAction("Imprimir nota", self)
        self.accion_imprimir.setShortcut(QKeySequence("Ctrl+T"))
        self.accion_imprimir.triggered.connect(self.imprimir_nota)

        # Acción limpiar nota
        self.accion_limpiar = QAction("Limpiar nota", self)
        self.accion_limpiar.setShortcut(QKeySequence("Ctrl+L"))
        self.accion_limpiar.triggered.connect(self.limpiar_nota)

        # Acción salir
        self.accion_salir = QAction("Salir", self)
        self.accion_salir.setShortcut(QKeySequence("Ctrl+I"))
        self.accion_salir.triggered.connect(self.salir)

        # Acción acerca de
        self.accion_acerca = QAction("Acerca de...", self)
        self.accion_acerca.setShortcut(QKeySequence("Ctrl+P"))
        self.accion_acerca.triggered.connect(self.acerca_de)

    # ----------------------------
    # CREAR MENÚS
    # ----------------------------
    def crear_menus(self):
        # Menú Archivo
        menu_archivo = self.menuBar().addMenu("&Archivo")
        menu_archivo.addAction(self.accion_imprimir)
        menu_archivo.addAction(self.accion_limpiar)
        menu_archivo.addAction(self.accion_salir)

        # Menú Ayuda
        menu_ayuda = self.menuBar().addMenu("&Ayuda")
        menu_ayuda.addAction(self.accion_acerca)

    # ----------------------------
    # CREAR TOOLBAR
    # ----------------------------
    def crear_toolbar(self):
        toolbar = QToolBar("Barra de herramientas")
        self.addToolBar(toolbar)
        toolbar.addAction(self.accion_imprimir)
        toolbar.addAction(self.accion_limpiar)
        toolbar.addSeparator

    # ----------------------------
    # CREAR STATUSBAR
    # ----------------------------
    def crear_statusbar(self):
        self.statusbar = QStatusBar()
        self.setStatusBar(self.statusbar)
        self.statusbar.showMessage("Listo")

    # ----------------------------
    # CONEXIÓN DE SEÑALES
    # ----------------------------
    def conectar_senales(self):
        self.titulo.textChanged.connect(self.actualizar_titulo_ventana)
        self.texto_nota.textChanged.connect(self.actualizar_titulo_ventana)
        self.combo.currentTextChanged.connect(self.mostrar_categoria)
        self.grupo_prioridad.buttonToggled.connect(self.mostrar_prioridad)

    # ----------------------------
    # FUNCIONES DE UTILIDAD
    # ----------------------------
    def actualizar_titulo_ventana(self):
        """Actualizar título de ventana con el título de la nota."""
        titulo = self.titulo.text()
        if titulo:
            self.setWindowTitle(f"Mini Bloc de Notas - {titulo}")
        else:
            self.setWindowTitle("Mini Bloc de Notas")

    def mostrar_prioridad(self, button, checked):
        """Mostrar prioridad seleccionada por 10 segundos."""
        if checked:
            self.label_info.setText(f"Prioridad seleccionada: {button.text()}")
            QTimer.singleShot(10000, lambda: self.label_info.setText(""))

    def mostrar_categoria(self, texto):
        """Mostrar categoría seleccionada por 5 segundos."""
        self.label_info.setText(f"Categoría seleccionada: {texto}")
        QTimer.singleShot(5000, lambda: self.label_info.setText(""))

    # ----------------------------
    # ACCIONES
    # ----------------------------
    def limpiar_nota(self):
        """Limpiar todos los campos de la nota después de confirmación."""
        respuesta = QMessageBox.question(
            self, "Confirmar", "¿Deseas limpiar la nota?",
            QMessageBox.Yes | QMessageBox.No
        )
        if respuesta == QMessageBox.Yes:
            self.titulo.clear()
            self.combo.setCurrentIndex(0)
            self.radio_normal.setChecked(True)
            self.texto_nota.clear()
            self.statusbar.showMessage("Nota limpiada", 3000)

    def imprimir_nota(self):
        """Imprimir nota en consola."""
        print("----- Nota -----")
        print(f"Título: {self.titulo.text()}")
        print(f"Categoría: {self.combo.currentText()}")
        prioridad = "Alta" if self.radio_alta.isChecked() else "Normal"
        print(f"Prioridad: {prioridad}")
        print("Contenido:")
        print(self.texto_nota.toPlainText())
        print("----------------")
        self.statusbar.showMessage("Nota impresa en consola", 3000)

    def salir(self):
        """Salir de la aplicación con confirmación."""
        respuesta = QMessageBox.question(
            self, "Salir", "¿Deseas salir de la aplicación?",
            QMessageBox.Yes | QMessageBox.No
        )
        if respuesta == QMessageBox.Yes:
            self.close()

    def acerca_de(self):
        """Mostrar cuadro de diálogo 'Acerca de'."""
        QMessageBox.information(
            self, "Acerca de",
            "Mini Bloc de Notas\nDesarrollado con PySide6\nCristina Sandoval"
        )







app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()