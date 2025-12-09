#Cristina Sandoval Laborde 2º DAM


from PySide6.QtWidgets import (
    QApplication,
    QWidget,
    QMainWindow,
    QVBoxLayout,
    QPushButton
)
from PySide6.QtGui import QPainter, QColor, QPen,QBrush
from PySide6.QtCore import QRect, Qt

class semaforo(QWidget):
    def __init__(self, parent =None):
        super().__init__(parent)
        #Tamaño del semaforo
        self.setFixedSize(120, 380)

    def paintEvent(self, event):
        # QPainter es el objeto que permite dibujar dentro del widget.
        painter = QPainter(self)

        #suavizar
        painter.setRenderHint(QPainter.Antialiasing)

      
        #Cuerpo del semaforo centrado
        cuerpo_ancho = 90
        cuerpo_x = (self.width() - cuerpo_ancho) // 2 
        cuerpo_y = 10 
        
        cuerpo_rect = QRect(cuerpo_x, cuerpo_y, cuerpo_ancho,280)

        #Dibujar el cuerpo gris oscuro
        
        painter.setBrush(QColor("#272626"))
     
        #Bordes redondos

        radio_esquina = 15
        painter.drawRoundedRect(
            cuerpo_rect, 
            radio_esquina,  
            radio_esquina  
        )
      
        


class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Semaforo")
        self.resize(300, 300)

        # Creamos una instancia del componente
        self.semaforo = semaforo()
        
        # Creamos un layout para centrar el componente en la ventana
        central_widget = QWidget()
        layout = QVBoxLayout(central_widget)
        
        # Centramos el componente
        layout.addWidget(self.semaforo, alignment=Qt.AlignCenter)

   
        self.setCentralWidget(central_widget)

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()