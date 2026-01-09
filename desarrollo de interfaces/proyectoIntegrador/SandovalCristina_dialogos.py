from PySide6.QtWidgets import (QDialog, QVBoxLayout, QLabel, QSpinBox, 
                               QDialogButtonBox, QCheckBox, QGroupBox, QFormLayout)
from PySide6.QtCore import Qt

class DialogoConfiguracion(QDialog):
    def __init__(self, parent=None):
        super().__init__(parent)
       
        self.setWindowTitle("Panel de Personalización") 
        self.setModal(True)
        self.resize(400, 300)

        layout = QVBoxLayout()

        
        grupo_params = QGroupBox("Parámetros Físicos")
        layout_params = QFormLayout()

        # Opción 1: Capacidad 
        self.spin_capacidad = QSpinBox()
        self.spin_capacidad.setRange(10, 100)
        self.spin_capacidad.setValue(30)
        layout_params.addRow("Capacidad máxima alumnos:", self.spin_capacidad)

        # Opción 2: Temperatura 
        self.spin_temp = QSpinBox()
        self.spin_temp.setRange(18, 30)
        self.spin_temp.setValue(24)
        self.spin_temp.setSuffix(" ºC")
        layout_params.addRow("Temperatura predeterminada:", self.spin_temp)
        
        grupo_params.setLayout(layout_params)
        layout.addWidget(grupo_params)

        # Grupo 2: Sistema
        grupo_sys = QGroupBox("Opciones del Sistema")
        layout_sys = QVBoxLayout()
        
        self.check_ahorro = QCheckBox("Activar modo ahorro de energía")
        self.check_silencio = QCheckBox("Modo silencioso (Sin alertas sonoras)") 
        
        layout_sys.addWidget(self.check_ahorro)
        layout_sys.addWidget(self.check_silencio)
        grupo_sys.setLayout(layout_sys)
        layout.addWidget(grupo_sys)

        # Botones Aceptar/Cancelar
        botones = QDialogButtonBox(QDialogButtonBox.StandardButton.Ok | QDialogButtonBox.StandardButton.Cancel)
        botones.accepted.connect(self.accept)
        botones.rejected.connect(self.reject)
        layout.addWidget(botones)

        self.setLayout(layout)