from PySide6.QtWidgets import QApplication, QMainWindow,QPushButton

class VentanaPrincipal (QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Mi aplicación")


        boton=QPushButton("Púlsame")

        #Quiero que mi bootn se convierta en interrruptor(se enciende la pantalla, se pone azul) y emita la señal
        #de si esta true o false
        boton.setCheckable(True)

        boton.clicked.connect(self.el_boton_fue_pulsado)

        #Saber si el boton esta encendido
        boton.clicked.connect(self.el_boton_esta_on)

        self.setCentralWidget(boton)

    def el_boton_fue_pulsado(self):
        print("Pulsado!")

#Le paso el boton y el parametro(puede llamarse como quieras, en este caso checked)
    def el_boton_esta_on(self,checked):
        print("Está ON?", checked)

app = QApplication()
window= VentanaPrincipal()
window.show()
app.exec()