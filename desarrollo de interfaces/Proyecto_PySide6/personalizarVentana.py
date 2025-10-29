from PySide6.QtWidgets import QApplication, QMainWindow,QPushButton
from PySide6.QtCore import QSize



class VentanaPrincipal (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Mi aplicación")
        boton =QPushButton("Púlsame!")
        # Establecer el widget central de la ventana
        self.setCentralWidget(boton)
           # Establecer un tamaño fijo para la ventana
        self.setFixedSize(QSize(400,300))

        #Tamaño variable
        self.setMaximumSize(600,400)
        self.setMinimumSize(300,200)

app = QApplication([])
window = VentanaPrincipal()
window.show()
app.exec()