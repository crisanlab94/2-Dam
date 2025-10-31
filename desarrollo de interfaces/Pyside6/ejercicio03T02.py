# Cristina Sandoval Laborde 2ºDAM

from PySide6.QtWidgets import QApplication, QMainWindow, QLabel
from PySide6.QtCore import Qt

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio03T02")

        self.etiqueta=QLabel("Sistema en espera")
        fuente = self.etiqueta.font()
        fuente.setPointSize(24)
        self.etiqueta.setFont(fuente)

        self.etiqueta.setAlignment(Qt.AlignHCenter | Qt.AlignBottom)
        self.setCentralWidget(self.etiqueta)
        self.etiqueta.setText("Sistema operativo iniciado")

app = QApplication()
window = VentanaPrincipal()
window.show()
app.exec()
