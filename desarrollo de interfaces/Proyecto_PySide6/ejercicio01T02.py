#Cristina Sandoval Laborde 2ºDAM

from PySide6.QtWidgets import QApplication, QMainWindow,QPushButton,QWidget

class VentanaSecundaria(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ventana secundaria")
        self.setFixedSize(300,200)

class VentanaPrincipal (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("ejercicio01T02")
        boton =QPushButton("¡Haz click!")
        # Establecer el widget central de la ventana
        self.setCentralWidget(boton)
       # self.setFixedSize(QSize(800,400))
       #El tamaño de la ventama lo quiero con rango no con tamaño fijo
        self.setMaximumSize(800,600)
        self.setMinimumSize(500,400)

     

app = QApplication([])
window = VentanaPrincipal()
window2 = VentanaSecundaria()
window.show()
window2.show()
app.exec()