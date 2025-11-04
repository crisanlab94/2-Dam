import os
import sys
from PySide6.QtWidgets import QApplication, QMainWindow, QLabel
from PySide6.QtGui import QPixmap

basedir=os.path.dirname(__file__)
 # Carpeta de trabajo actual
print("Carpeta trabajo actual: ", os.getcwd())
  # Ruta base (archivo actual-carpeta actual)
print("Ruta base archivo actual:", basedir)

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Mi aplicación")

        etiqueta = QLabel("Hola Hola!")
        etiqueta.setPixmap(QPixmap(os.path.join(basedir,"gatoAdorable.jpg")))
    
        etiqueta.setScaledContents(True)

        self.setCentralWidget(etiqueta)

app =QApplication(sys.argv)
ventana = VentanaPrincipal()
ventana.show()

app.exec()