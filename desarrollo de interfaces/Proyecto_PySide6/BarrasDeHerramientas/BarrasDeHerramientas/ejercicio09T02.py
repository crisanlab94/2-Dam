#Cristina Sandoval Laborde 2ºDAM

from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QPushButton,
    QStackedLayout, QLabel, QVBoxLayout, QHBoxLayout
)
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio 9")

        #Layout horizontal
        layout_principal = QHBoxLayout()
        componente_principal = QWidget()
        componente_principal.setLayout(layout_principal)
        self.setCentralWidget(componente_principal)
      
        #Layout izquierdo
        layout_izquierdo = QVBoxLayout()
        layout_izquierdo.addWidget(QPushButton("V1"))
        layout_izquierdo.addWidget(QPushButton("V2"))
        layout_izquierdo.addWidget(QPushButton("V3"))
        layout_izquierdo.addWidget(QPushButton("V4"))

        #Layout derecho
        layout_derecho = QHBoxLayout()
        layout_derecho.addWidget(QPushButton("Botón A"))
        layout_derecho.addWidget(QPushButton("Botón B"))
        layout_derecho.addWidget(QPushButton("Botón C"))
        layout_derecho.addWidget(QPushButton("Botón D"))


        # Añadimos los layouts secundarios al layout principal
        layout_principal.addLayout(layout_izquierdo)
        layout_principal.addLayout(layout_derecho)

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()