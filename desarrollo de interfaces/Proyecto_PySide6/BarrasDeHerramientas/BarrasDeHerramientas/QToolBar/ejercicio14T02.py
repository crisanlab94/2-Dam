#Cristina Sandoval Laborde 2ºDAM 

from PySide6.QtWidgets import (
    QMainWindow, QApplication, QDialog, QDialogButtonBox, 
    QVBoxLayout, QLabel, QPushButton
)
from PySide6.QtCore import Qt


class DialogoModo(QDialog):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.setWindowTitle("Seleccionar modo")

      
        self.boton_pulsado = None

      
        etiqueta = QLabel("Selecciona el modo de operación que quieres activar:")

     
        botones = (
            QDialogButtonBox.Yes |
            QDialogButtonBox.No |
            QDialogButtonBox.Help
        )
        caja = QDialogButtonBox(botones)

        # Conexiones de los botones
        caja.button(QDialogButtonBox.Yes).clicked.connect(self.pulsar_yes)
        caja.button(QDialogButtonBox.No).clicked.connect(self.pulsar_no)
        caja.button(QDialogButtonBox.Help).clicked.connect(self.pulsar_help)

        # Layout
        layout = QVBoxLayout()
        layout.addWidget(etiqueta)
        layout.addWidget(caja)
        self.setLayout(layout)

   

    def pulsar_yes(self):
        self.boton_pulsado = "YES"
        self.accept()  

    def pulsar_no(self):
        self.boton_pulsado = "NO"
        self.accept()   

    def pulsar_help(self):
        self.boton_pulsado = "HELP"
        self.reject()   



class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Selección de modo de operación")

        boton = QPushButton("Elegir modo")
        boton.clicked.connect(self.mostrar_dialogo)
        self.setCentralWidget(boton)

    def mostrar_dialogo(self):
        dialogo = DialogoModo(self)
        resultado = dialogo.exec()

       
        if resultado == QDialog.Accepted:
            if dialogo.boton_pulsado == "YES":
                print("Modo A activado")
            elif dialogo.boton_pulsado == "NO":
                print("Modo B activado")

        else:
            print("Se ha solicitado ayuda")




app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()
