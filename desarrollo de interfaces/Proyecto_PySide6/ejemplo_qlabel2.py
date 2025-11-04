from PySide6.QtWidgets import QApplication, QMainWindow, QLabel
from PySide6.QtGui import QPixmap

class VentanaPincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Mi aplicación")

        etiqueta = QLabel("Hola!")
        etiqueta.setPixmap(QPixmap("gato.jpg"))
        #Para que sea escalable(la imagen se adapta a la ventana)
        etiqueta.setScaledContents(True)

        self.setCentralWidget(etiqueta)

app =QApplication([])
ventana = VentanaPincipal()
ventana.show()

app.exec()