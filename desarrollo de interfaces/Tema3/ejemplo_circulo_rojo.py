
from PySide6.QtWidgets import (
    QApplication,
    QWidget,
    QMainWindow,
   
)
from PySide6.QtGui import QPainter, QColor, QPen
from PySide6.QtCore import Qt


class CirculoRojo(QWidget):
    #No lleva init porque solo vamos a pintar

    #Siempre self,event
    def paintEvent(self, event):
        # QPainter es el objeto encargado de dibujar dentro del widget.
        #Es el lienzo
        painter = QPainter(self)

        # Color de relleno del círculo (rojo).
        painter.setBrush(QColor("red"))

        # Color del borde (negro) usando un QPen, grosor por defecto.
        painter.setPen(QPen(Qt.black))

        # Dibujamos una elipse que ocupa todo el área del widget.
        # Si el widget es cuadrado, la elipse será un círculo perfecto.
        painter.drawEllipse(self.rect())
        
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Ejemplo 1: Círculo Rojo")
        #tamaño ventana
        self.resize(300, 300)
        #digo que la clase circuloRojo este en el centro
        self.setCentralWidget(CirculoRojo())

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()
