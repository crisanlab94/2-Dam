import sys
from PySide6.QtWidgets import (
    QApplication, QWidget, QLabel, QPushButton, 
    QHBoxLayout, QVBoxLayout, QFrame
)
from PySide6.QtCore import Signal

class TarjetaUsuario(QWidget):
    # Señal personalizada para enviar el ID al borrar [cite: 999-1001]
    eliminar_usuario = Signal(int)

    def __init__(self, id_usuario, nombre, email, parent=None):
        super().__init__(parent)
        self._id = id_usuario

        # --- Estructura Visual ---
        self.marco = QFrame()
        # IMPORTANTE: Este nombre conecta con QFrame#marcoTarjeta en el QSS
        self.marco.setObjectName("marcoTarjeta")

        # Etiquetas con nombres para el estilo
        self.lbl_nombre = QLabel(nombre)
        self.lbl_nombre.setObjectName("lblNombre")
        
        self.lbl_email = QLabel(email)
        self.lbl_email.setObjectName("lblEmail")
        
        # Botón con nombre para el estilo
        self.btn_borrar = QPushButton("X")
        self.btn_borrar.setObjectName("btnBorrar")
        
        # --- Layouts (Organización) ---
        layout_textos = QVBoxLayout()
        layout_textos.addWidget(self.lbl_nombre)
        layout_textos.addWidget(self.lbl_email)
        layout_textos.setSpacing(2)

        layout_principal = QHBoxLayout(self.marco)
        layout_principal.addLayout(layout_textos)
        layout_principal.addStretch()
        layout_principal.addWidget(self.btn_borrar)

        layout_final = QVBoxLayout(self)
        layout_final.addWidget(self.marco)

        # --- Conexión de Señales ---
        self.btn_borrar.clicked.connect(self.al_pulsar_borrar)

    def al_pulsar_borrar(self):
        # Emitimos la señal personalizada hacia fuera [cite: 1006]
        self.eliminar_usuario.emit(self._id)

# --- Ejecución Principal ---
if __name__ == "__main__":
    app = QApplication(sys.argv)

    # CARGA DEL ARCHIVO QSS [cite: 232-237]
    # Abrimos el fichero 'estilos.qss' en modo lectura ('r')
    try:
        with open("estilos1.qss", "r") as f:
            app.setStyleSheet(f.read())
    except FileNotFoundError:
        print("ADVERTENCIA: No se encontró el archivo estilos.qss")

    # Creamos la ventana de prueba
    ventana = TarjetaUsuario(1, "Carlos Pérez", "carlos@ejemplo.com")
    ventana.setWindowTitle("Ejemplo Separado")
    ventana.resize(300, 100)
    
    # Probamos que la señal funciona
    ventana.eliminar_usuario.connect(lambda id_user: print(f"Borrar ID: {id_user}"))

    ventana.show()
    sys.exit(app.exec())