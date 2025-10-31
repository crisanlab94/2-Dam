#Cristina Sandoval laborde 2ºDAM

from PySide6.QtWidgets import QApplication, QMainWindow,QPushButton

class VentanaPrincipal (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio02T02")

        boton=QPushButton("Encender")
        boton.setCheckable(True)

        boton.clicked.connect(self.el_boton_fue_pulsado)
        boton.clicked.connect(self.cambio_titulo_ventana)

        self.setCentralWidget(boton)

    def el_boton_fue_pulsado(self):
        print("Botón pulsado")

    def cambio_titulo_ventana(self,checked):
        if checked == True:
            self.setWindowTitle("Ventana encendida")
        else:
            self.setWindowTitle("Ventana apagada")

app = QApplication()
window= VentanaPrincipal()
window.show()
app.exec()

