from PySide6.QtWidgets import QApplication,QMainWindow,QLabel
from PySide6.QtCore import Qt

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Mi applicación")

        etiqueta=QLabel("Hola")
        fuente = etiqueta.font()
        fuente.setPointSize(30)
        etiqueta.setFont(fuente)

        etiqueta.setAlignment(Qt.AlignHCenter | Qt.AlignVCenter)
        self.setCentralWidget(etiqueta)

app = QApplication()
window = VentanaPrincipal()
window.show()
app.exec()
         
