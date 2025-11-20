from PySide6.QtWidgets import (
    QMainWindow, QApplication, QDialog, QDialogButtonBox, QVBoxLayout,
    QLabel, QPushButton
)
from PySide6.QtCore import QLibraryInfo, QTranslator

class DialogoPersonalizado(QDialog):
    def __init__(self, parent=None): #No tiene padre
        super().__init__(parent)
        self.setWindowTitle("Diálogo personalizado")

        botones = QDialogButtonBox.Ok | QDialogButtonBox.Cancel
        caja = QDialogButtonBox(botones)

        #Nombre de la caja, lo que quiere hacer, conectar y donde lo conectas
        caja.accepted.connect(self.accept) #Boton aceptar, acepta
        caja.rejected.connect(self.reject) #Boton cancelar, rechaza

        layout = QVBoxLayout()
        layout.addWidget(QLabel("¿Quieres realizar esta accion?"))
        layout.addWidget(caja)

        self.setLayout(layout)


class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Aplicación con diálogos personalizado")

        boton = QPushButton("Mostrar diálogo personalizado")
        boton.clicked.connect(self.mostrar_dialogo)
        self.setCentralWidget(boton)

    def mostrar_dialogo(self):
        print("Click recibido, se mostrará el diálogo personalizado.")
        dialogo = DialogoPersonalizado(self)
        dialogo.setWindowTitle("Ventana de diálogo personalizado")

        resultado=dialogo.exec()
        if resultado:
            print("Aceptado")
        else:
            print("Cancelado")

#POner en español
def cargar_traductor(app):
    traductor = QTranslator(app)
    ruta = QLibraryInfo.location(QLibraryInfo.TranslationsPath)
    traductor.load("qt_es", ruta)
    app.installTranslator(traductor)

app = QApplication([])
cargar_traductor(app)
ventana = VentanaPrincipal()
ventana.show()
app.exec()