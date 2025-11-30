from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout, QHBoxLayout, QFormLayout,
    QPushButton, QLineEdit, QPlainTextEdit, QComboBox, QRadioButton,
    QCheckBox, QSlider, QSpinBox, QDoubleSpinBox, QLabel, QProgressBar,
    QLCDNumber, QDateEdit, QTimeEdit, QDateTimeEdit, QListWidget,
    QTreeWidget, QTreeWidgetItem, QTabWidget, QMessageBox, QToolBar, QStatusBar
)
from PySide6.QtCore import Qt
from PySide6.QtGui import QFont, QAction
import sys


class DemoWidgetsPySide6(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Demo completa PySide6")
        self.resize(900, 600)

        # --- Widgets principales ---
        self.entrada = QLineEdit()
        self.entrada.setPlaceholderText("Escribe tu nombre")

        self.comentarios = QPlainTextEdit()
        self.comentarios.setPlaceholderText("Comentarios sin formato")

        self.combo = QComboBox()
        self.combo.addItems(["Opción 1", "Opción 2", "Opción 3"])

        self.radio_a = QRadioButton("Prioridad Normal")
        self.radio_b = QRadioButton("Prioridad Alta")

        self.check = QCheckBox("Aceptar condiciones")

        self.slider = QSlider(Qt.Orientation.Horizontal)
        self.slider.setRange(0, 100)

        self.spin = QSpinBox()
        self.spin.setRange(0, 10)

        self.dspin = QDoubleSpinBox()
        self.dspin.setRange(0.0, 10.0)
        self.dspin.setSingleStep(0.5)

        self.label = QLabel("Etiqueta inicial")
        self.label.setAlignment(Qt.AlignmentFlag.AlignCenter)
        self.label.setStyleSheet("color: blue; background-color: lightyellow;")
        self.label.setFont(QFont("Arial", 14, QFont.Weight.Bold))

        self.progress = QProgressBar()
        self.progress.setRange(0, 100)
        self.lcd = QLCDNumber()

        self.fecha = QDateEdit()
        self.hora = QTimeEdit()
        self.fecha_hora = QDateTimeEdit()

        self.lista = QListWidget()
        self.lista.addItems(["Manzana", "Plátano", "Naranja"])

        self.arbol = QTreeWidget()
        self.arbol.setHeaderLabels(["Categoría"])
        animales = QTreeWidgetItem(["Animales"])
        animales.addChild(QTreeWidgetItem(["Perro"]))
        animales.addChild(QTreeWidgetItem(["Gato"]))
        self.arbol.addTopLevelItem(animales)

        # --- Pestañas con contenido ---
        self.tabs = QTabWidget()

        # Tab General
        tab_general = QWidget()
        layout_general = QVBoxLayout()
        layout_general.addWidget(QLabel("Opciones generales"))
        entrada_general = QLineEdit()
        entrada_general.setPlaceholderText("Escribe algo en General")
        boton_general = QPushButton("Acción General")
        layout_general.addWidget(entrada_general)
        layout_general.addWidget(boton_general)
        tab_general.setLayout(layout_general)

        # Tab Configuración
        tab_config = QWidget()
        layout_config = QFormLayout()
        check_config1 = QCheckBox("Activar modo oscuro")
        check_config2 = QCheckBox("Habilitar notificaciones")
        combo_config = QComboBox()
        combo_config.addItems(["Español", "Inglés", "Francés"])
        layout_config.addRow("Idioma:", combo_config)
        layout_config.addRow(check_config1)
        layout_config.addRow(check_config2)
        tab_config.setLayout(layout_config)

        self.tabs.addTab(tab_general, "General")
        self.tabs.addTab(tab_config, "Configuración")

        # --- Botones ---
        self.boton_guardar = QPushButton("Guardar")
        self.boton_salir = QPushButton("Salir")

        # --- Layout principal ---
        form_layout = QFormLayout()
        form_layout.addRow("Nombre:", self.entrada)
        form_layout.addRow("Comentarios:", self.comentarios)
        form_layout.addRow("Opciones:", self.combo)
        form_layout.addRow("Prioridad:", self.radio_a)
        form_layout.addRow("", self.radio_b)
        form_layout.addRow("Condiciones:", self.check)
        form_layout.addRow("Slider:", self.slider)
        form_layout.addRow("Spin:", self.spin)
        form_layout.addRow("DoubleSpin:", self.dspin)
        form_layout.addRow("Fecha:", self.fecha)
        form_layout.addRow("Hora:", self.hora)
        form_layout.addRow("Fecha+Hora:", self.fecha_hora)

        left_layout = QVBoxLayout()
        left_layout.addLayout(form_layout)
        left_layout.addWidget(self.label)
        left_layout.addWidget(self.progress)
        left_layout.addWidget(self.lcd)
        left_layout.addWidget(self.boton_guardar)
        left_layout.addWidget(self.boton_salir)

        right_layout = QVBoxLayout()
        right_layout.addWidget(self.lista)
        right_layout.addWidget(self.arbol)
        right_layout.addWidget(self.tabs)

        main_layout = QHBoxLayout()
        main_layout.addLayout(left_layout, 2)
        main_layout.addLayout(right_layout, 1)

        central = QWidget()
        central.setLayout(main_layout)
        self.setCentralWidget(central)

        # --- StatusBar ---
        self.status = QStatusBar()
        self.setStatusBar(self.status)
        self.status.showMessage("Aplicación lista")

        # --- Toolbar ---
        toolbar = QToolBar("Barra de herramientas")
        self.addToolBar(toolbar)

        self.accion_rojo = QAction("Rojo", self)
        self.accion_verde = QAction("Verde", self)
        self.accion_fuente1 = QAction("Arial", self)
        self.accion_fuente2 = QAction("Courier", self)

        toolbar.addAction(self.accion_rojo)
        toolbar.addAction(self.accion_verde)
        toolbar.addAction(self.accion_fuente1)
        toolbar.addAction(self.accion_fuente2)

        self.accion_rojo.triggered.connect(self.color_rojo)
        self.accion_verde.triggered.connect(self.color_verde)
        self.accion_fuente1.triggered.connect(self.fuente_arial)
        self.accion_fuente2.triggered.connect(self.fuente_courier)

        # --- MenuBar ---
        menu_bar = self.menuBar()
        menu_estilo = menu_bar.addMenu("Estilo")
        menu_fuente = menu_bar.addMenu("Fuente")

        self.accion_azul = QAction("Azul", self)
        self.accion_negrita = QAction("Negrita", self)
        self.accion_cursiva = QAction("Cursiva", self)

        menu_estilo.addAction(self.accion_azul)
        menu_fuente.addAction(self.accion_negrita)
        menu_fuente.addAction(self.accion_cursiva)

        self.accion_azul.triggered.connect(self.color_azul)
        self.accion_negrita.triggered.connect(self.fuente_negrita)
        self.accion_cursiva.triggered.connect(self.fuente_cursiva)

        # --- Conexiones básicas ---
        self.entrada.textChanged.connect(self.actualizar_label)
        self.comentarios.textChanged.connect(self.mostrar_comentarios)
        self.combo.currentTextChanged.connect(self.cambio_combo)
        self.radio_a.toggled.connect(self.cambio_prioridad)
        self.radio_b.toggled.connect(self.cambio_prioridad)
        self.check.stateChanged.connect(self.cambio_check)
        self.slider.valueChanged.connect(self.actualizar_slider)
        self.spin.valueChanged.connect(self.mostrar_spin)
        self.dspin.valueChanged.connect(self.mostrar_dspin)
        self.lista.currentItemChanged.connect(self.mostrar_lista)
        self.arbol.itemClicked.connect(self.mostrar_arbol)
        self.fecha.dateChanged.connect(self.mostrar_fecha)
        self.hora.timeChanged.connect(self.mostrar_hora)
        self.fecha_hora.dateTimeChanged.connect(self.mostrar_fecha_hora)
        self.boton_guardar.clicked.connect(self.guardar_datos)
        self.boton_salir.clicked.connect(self.confirmar_salida)

   

    # --- Slots de lógica ---
    def actualizar_label(self, texto):
        self.label.setText(f"Texto: {texto}")

    def mostrar_comentarios(self):
        print("Comentarios:", self.comentarios.toPlainText())

    def cambio_combo(self, texto):
        self.status.showMessage(f"Combo seleccionado: {texto}")

    def cambio_prioridad(self):
        if self.radio_a.isChecked():
            self.status.showMessage("Prioridad Normal")
        elif self.radio_b.isChecked():
            self.status.showMessage("Prioridad Alta")

    def cambio_check(self, estado):
        self.status.showMessage(f"CheckBox estado: {estado}")

    def actualizar_slider(self, valor):
        self.progress.setValue(valor)
        self.lcd.display(valor)

    def mostrar_spin(self, valor):
        print("SpinBox valor:", valor)

    def mostrar_dspin(self, valor):
        print("DoubleSpinBox valor:", valor)

    def mostrar_lista(self, actual, anterior):
        # currentItemChanged envía (current, previous)
        if actual and anterior:
            self.status.showMessage(
                f"Cambio de fruta: antes '{anterior.text()}', ahora '{actual.text()}'"
            )
        elif actual:
            self.status.showMessage(f"Fruta seleccionada: {actual.text()}")
        elif anterior:
            self.status.showMessage(f"Se deseleccionó: {anterior.text()}")

    def mostrar_arbol(self, item, column):
        self.status.showMessage(f"Elemento árbol: {item.text(column)}")

    def mostrar_fecha(self, fecha):
        self.status.showMessage(f"Fecha: {fecha.toString()}")

    def mostrar_hora(self, hora):
        self.status.showMessage(f"Hora: {hora.toString()}")

    def mostrar_fecha_hora(self, dt):
        self.status.showMessage(f"Fecha y hora: {dt.toString()}")

    def guardar_datos(self):
        QMessageBox.information(self, "Guardar", "Datos guardados correctamente")

    def confirmar_salida(self):
        respuesta = QMessageBox.question(
            self,
            "Salir",
            "¿Deseas cerrar la aplicación?",
            QMessageBox.StandardButton.Yes | QMessageBox.StandardButton.No
        )
        if respuesta == QMessageBox.StandardButton.Yes:
            self.close()

    # --- Slots de estilo ---
    def color_rojo(self):
        self.label.setStyleSheet("color: red; background-color: lightyellow;")
        self.status.showMessage("Color cambiado a rojo")

    def color_verde(self):
        self.label.setStyleSheet("color: green; background-color: lightyellow;")
        self.status.showMessage("Color cambiado a verde")

    def color_azul(self):
        self.label.setStyleSheet("color: blue; background-color: lightyellow;")
        self.status.showMessage("Color cambiado a azul")

    def fuente_arial(self):
        self.label.setFont(QFont("Arial", 14, QFont.Weight.Normal))
        self.status.showMessage("Fuente cambiada a Arial")

    def fuente_courier(self):
        self.label.setFont(QFont("Courier New", 12, QFont.Weight.Normal))
        self.status.showMessage("Fuente cambiada a Courier")

    def fuente_negrita(self):
        self.label.setFont(QFont("Arial", 14, QFont.Weight.Bold))
        self.status.showMessage("Fuente cambiada a Arial Negrita")

    def fuente_cursiva(self):
        font = QFont("Arial", 14)
        font.setItalic(True)
        self.label.setFont(font)
        self.status.showMessage("Fuente cambiada a Arial Cursiva")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = DemoWidgetsPySide6()
    ventana.show()
    sys.exit(app.exec())
