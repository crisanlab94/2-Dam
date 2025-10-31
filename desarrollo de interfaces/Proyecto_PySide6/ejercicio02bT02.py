# Cristina Sandoval Laborde 2ºDAM

from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio02bT02")

        # Crear botón central
        self.boton = QPushButton("Pulsar")
        self.setCentralWidget(self.boton)

        # Conectar las señales correctas
        self.boton.pressed.connect(self.el_boton_fue_presionado)
        self.boton.released.connect(self.el_boton_fue_liberado)

    def el_boton_fue_presionado(self):
        self.boton.setText("Soltar")
        print("Botón presionado")

    def el_boton_fue_liberado(self):
        self.boton.setText("Pulsar")
        print("Botón liberado")


app = QApplication()
window = VentanaPrincipal()
window.show()
app.exec()
