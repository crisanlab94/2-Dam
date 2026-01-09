import sys
import os
from PySide6.QtWidgets import (QApplication, QMainWindow, QWidget, QVBoxLayout, 
                             QHBoxLayout, QFormLayout, QComboBox, QCheckBox, 
                             QTextEdit, QLabel, QToolBar, QStatusBar, QMessageBox, 
                             QDockWidget, QPushButton, QStyle, QSpinBox, QSlider)
from PySide6.QtGui import QAction
from PySide6.QtCore import Qt

# Importamos los componentes creados 
from SandovalCristina_componentes import CampoNombreAula, IndicadorEstadoAula
from SandovalCristina_dialogos import DialogoConfiguracion

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Panel de Control - Aula Inteligente")
        self.resize(1050, 700)
        
        self.crear_acciones()
        self.crear_menus()
        self.crear_toolbar()
        self.crear_statusbar()
        self.crear_dock_lateral() 
        self.configurar_area_central()
        
        # --- CONEXIONES  ---
        self.input_nombre.validacion_cambiada.connect(self.al_cambiar_validacion_nombre)
        self.combo_modos.currentIndexChanged.connect(self.actualizar_estado_aula)
        self.check_luz.toggled.connect(self.actualizar_estado_aula)
        self.check_proyector.toggled.connect(self.actualizar_estado_aula)
        self.check_clima.toggled.connect(self.actualizar_estado_aula)

        # Fuerce del estado inicial 
        self.restablecer_aula()

    def crear_acciones(self):
        self.accion_restablecer = QAction(self.style().standardIcon(QStyle.StandardPixmap.SP_BrowserReload), "Restablecer aula", self)
        self.accion_restablecer.triggered.connect(self.restablecer_aula)

        self.accion_salir = QAction(self.style().standardIcon(QStyle.StandardPixmap.SP_DialogCloseButton), "Salir", self)
        self.accion_salir.triggered.connect(self.confirmar_salida)

        self.accion_acerca = QAction("Acerca de...", self)
        self.accion_acerca.triggered.connect(self.mostrar_acerca_de)

        self.accion_config = QAction("Configuración avanzada", self)
        self.accion_config.triggered.connect(self.abrir_configuracion)

    def crear_menus(self):
        barra = self.menuBar()
        archivo = barra.addMenu("&Archivo")
        archivo.addAction(self.accion_restablecer)
        archivo.addAction(self.accion_salir)
        ayuda = barra.addMenu("Ayuda")
        ayuda.addAction(self.accion_acerca)

    def crear_toolbar(self):
        toolbar = QToolBar("Principal")
        self.addToolBar(toolbar)
        toolbar.addAction(self.accion_restablecer)
        toolbar.addAction(self.accion_config)

    def crear_statusbar(self):
        self.barra_estado = QStatusBar()
        self.setStatusBar(self.barra_estado)
        self.lbl_estado_permanente = QLabel("Estado: Iniciando...")
        self.barra_estado.addPermanentWidget(self.lbl_estado_permanente)

    # --- DOCK LATERAL ---
    def crear_dock_lateral(self):
      
        self.dock = QDockWidget("Configuración rápida", self)
        self.dock.setAllowedAreas(Qt.DockWidgetArea.RightDockWidgetArea | Qt.DockWidgetArea.LeftDockWidgetArea)
        
        #Se pude acoplar y desacoplar
        self.dock.setFeatures(QDockWidget.DockWidgetFeature.DockWidgetMovable | 
                              QDockWidget.DockWidgetFeature.DockWidgetFloatable)

        contenido = QWidget()
        layout = QVBoxLayout()
        layout.setSpacing(15)
        layout.setContentsMargins(15, 15, 15, 15)

        #  Número de alumnos
        lbl_alumnos = QLabel("<b>Número de alumnos:</b>")
        layout.addWidget(lbl_alumnos)
        
        self.spin_alumnos = QSpinBox()
        self.spin_alumnos.setRange(0, 100)
        self.spin_alumnos.setMinimumHeight(45) 
        layout.addWidget(self.spin_alumnos)

        # Nº Equipos
        lbl_equipos = QLabel("<b>Nº de Equipos:</b>")
        layout.addWidget(lbl_equipos)
        
        self.spin_equipos = QSpinBox()
        self.spin_equipos.setRange(0, 50)
        self.spin_equipos.setValue(25)
        self.spin_equipos.setMinimumHeight(45)
        layout.addWidget(self.spin_equipos)

        # 3. Slider Intensidad
        layout.addSpacing(10)
        layout.addWidget(QLabel("<b>Intensidad Luz:</b>"))
        
        h_layout = QHBoxLayout()
        self.slider = QSlider(Qt.Orientation.Horizontal)
        self.lbl_porcentaje = QLabel("0%")
        self.lbl_porcentaje.setFixedWidth(40)
        self.slider.valueChanged.connect(lambda v: self.lbl_porcentaje.setText(f"{v}%"))
        
        h_layout.addWidget(self.slider)
        h_layout.addWidget(self.lbl_porcentaje)
        layout.addLayout(h_layout)

        layout.addStretch()

        # 4. Botón Alerta
        self.btn_alerta = QPushButton("⚠ AVISAR A CONSEJERÍA")
        self.btn_alerta.setObjectName("btnAlerta")
        self.btn_alerta.setMinimumHeight(50)
        self.btn_alerta.setCursor(Qt.CursorShape.PointingHandCursor)
        self.btn_alerta.clicked.connect(self.enviar_alerta_consejeria)
        layout.addWidget(self.btn_alerta)

        contenido.setLayout(layout)
        self.dock.setWidget(contenido)
        self.addDockWidget(Qt.DockWidgetArea.RightDockWidgetArea, self.dock)

    # --- ÁREA CENTRAL ---
    def configurar_area_central(self):
        central = QWidget()
        self.setCentralWidget(central)
        layout_main = QHBoxLayout(central)

        # Columna Izquierda (Formulario)
        col_izq = QWidget()
        layout_izq = QVBoxLayout(col_izq)
        
        form = QFormLayout()
        self.input_nombre = CampoNombreAula()
        self.combo_modos = QComboBox()
        self.combo_modos.addItems(["Clase normal", "Examen", "Presentación", "Charla"]) 
        self.combo_modos.setMinimumHeight(35)
        
        form.addRow("Nombre Aula:", self.input_nombre)
        form.addRow("Modo:", self.combo_modos)
        layout_izq.addLayout(form)

        layout_izq.addSpacing(20)

        self.check_luz = QCheckBox("Iluminación encendida")
        self.check_proyector = QCheckBox("Proyector activo")
        self.check_clima = QCheckBox("Climatización activa")
        layout_izq.addWidget(self.check_luz)
        layout_izq.addWidget(self.check_proyector)
        layout_izq.addWidget(self.check_clima)

        layout_izq.addSpacing(20)
        layout_izq.addWidget(QLabel("Observaciones:"))
        
        self.txt_obs = QTextEdit()
        layout_izq.addWidget(self.txt_obs)

        # --- NUEVO BOTÓN ENVIAR ---
        self.btn_enviar = QPushButton("Enviar")
        self.btn_enviar.setMinimumHeight(40) 
        # Conectamos a la función que saca el mensaje
        self.btn_enviar.clicked.connect(self.enviar_informacion) 
        layout_izq.addWidget(self.btn_enviar)

        # Columna Derecha (Visual)
        col_der = QWidget()
        layout_der = QVBoxLayout(col_der)
        layout_der.setAlignment(Qt.AlignmentFlag.AlignTop | Qt.AlignmentFlag.AlignHCenter)
        
        lbl_titulo = QLabel("ESTADO DEL AULA")
        lbl_titulo.setStyleSheet("font-weight: bold; font-size: 14px;")
        layout_der.addWidget(lbl_titulo)
        
        self.indicador = IndicadorEstadoAula()
        layout_der.addWidget(self.indicador)

        layout_main.addWidget(col_izq, stretch=60)
        layout_main.addWidget(col_der, stretch=40)

    # --- LÓGICA DE CONTROL ---

    def al_cambiar_validacion_nombre(self, es_valido):
        """ Se ejecuta cuando el input cambia de color/validez """
        if es_valido:
            self.barra_estado.showMessage("Nombre de aula correcto.")
        else:
            self.barra_estado.showMessage("Error: Nombre demasiado corto.")
        
        
        self.actualizar_estado_aula()

    def actualizar_estado_aula(self):
        nombre_ok = self.input_nombre.es_valido
        modo = self.combo_modos.currentText()
        luz = self.check_luz.isChecked()
        proyector = self.check_proyector.isChecked()

        # 1. Prioridad ABSOLUTA: Si el nombre no es válido
        if not nombre_ok:
            self.indicador.set_estado("Preparando")
            self.lbl_estado_permanente.setText("Estado: Esperando nombre válido...")
            return

        # 2. Lógica específica por MODO
        if modo == "Presentación":
            if not proyector:
                # Si estamos en presentación y NO hay proyector -> INCIDENCIA
                self.indicador.set_estado("Incidencia")
                self.lbl_estado_permanente.setText("INCIDENCIA: Hay que encender el proyector")
            elif (proyector and not luz) or (luz and not proyector):
                # Si falta uno de los dos (o proyector o luz) -> PREPARANDO
                self.indicador.set_estado("Preparando")
                self.lbl_estado_permanente.setText("Preparando: Ajustando ambiente...")
            else:
                # Si Proyector ON y Luz ON -> CORRECTO
                self.indicador.set_estado("Correcto")
                self.lbl_estado_permanente.setText("Aula lista para presentación")
        
        elif modo == "Examen":
            # Ejemplo: En examen no queremos proyector
            if proyector:
                self.indicador.set_estado("Incidencia")
                self.lbl_estado_permanente.setText("INCIDENCIA: Apague el proyector para examen")
            else:
                self.indicador.set_estado("Correcto")
                self.lbl_estado_permanente.setText("Aula lista para examen")
        
        else:
            # Clase Normal, Charla, etc. (Comportamiento por defecto)
            self.indicador.set_estado("Correcto")
            self.lbl_estado_permanente.setText("Aula Operativa")

    def enviar_alerta_consejeria(self):
        QMessageBox.warning(self, "Alerta Enviada", 
                            "Se ha notificado a Consejería.\nUn técnico acudirá al aula.")
        
        self.barra_estado.showMessage("ALERTA ENVIADA A CONSEJERÍA", 5000)

    def restablecer_aula(self):
        self.input_nombre.clear() 
        self.combo_modos.setCurrentIndex(0)
        self.check_luz.setChecked(False)
        self.check_proyector.setChecked(False)
        self.check_clima.setChecked(False)
        self.slider.setValue(0)
        
        
        # Ahora reseteamos las nuevas variables:
        if hasattr(self, 'spin_alumnos'): self.spin_alumnos.setValue(0)
        if hasattr(self, 'spin_equipos'): self.spin_equipos.setValue(25)
        
        self.indicador.set_estado("Preparando")
        self.barra_estado.showMessage("Sistema restablecido.")

    def confirmar_salida(self):
        if QMessageBox.question(self, "Salir", "¿Cerrar aplicación?") == QMessageBox.StandardButton.Yes:
            self.close()

    def mostrar_acerca_de(self):
        QMessageBox.information(self, "Acerca de", "Panel Aula Inteligente v1.0")

    def abrir_configuracion(self):
        d = DialogoConfiguracion(self)
        d.exec()

    def enviar_informacion(self):
      # Comprobamos si el nombre es válido
        if not self.input_nombre.es_valido:
            QMessageBox.warning(self, "Faltan datos", "Por favor, introduzca un nombre de aula válido (mínimo 3 letras) antes de enviar.")
            return 
            
        # Si pasa la comprobación, enviamos y limpiamos
        QMessageBox.information(self, "Enviado", "Información enviada correctamente.")
        self.txt_obs.clear()

if __name__ == "__main__":
    app = QApplication(sys.argv)

    try:
        with open("SandovalCristina_estilos.qss", "r", encoding="utf-8") as f:
            app.setStyleSheet(f.read())
    except FileNotFoundError:
        print("Crea el archivo SandovalCristina_estilos.qss")

    ventana = VentanaPrincipal()
    ventana.show()
    sys.exit(app.exec())