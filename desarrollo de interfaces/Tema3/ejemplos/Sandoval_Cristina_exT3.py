
from PySide6.QtWidgets import (
    QApplication,
    QWidget,
    QMainWindow,
    QVBoxLayout,
    QPushButton,
    QLabel
)
from PySide6.QtGui import QPainter, QColor, QPen
from PySide6.QtCore import QRect, Qt
from PySide6.QtCore import Signal


class IndicadorSimple(QWidget):
    def __init__(self, parent =None):
        super().__init__(parent)
        # Texto que se mostrará dentro del círculo.
        # Se puede cambiar desde fuera con setTexto().
        self._texto = "OK"

    def setTexto(self, texto):
        # Guardamos el nuevo texto.
        self._texto = texto
        # update() avisa a Qt de que debe volver a dibujar el widget.
        self.update()

    def paintEvent(self, event):
        # QPainter es el objeto que permite dibujar dentro del widget.
        painter = QPainter(self)

        # Activamos el suavizado de bordes para evitar formas “dentadas”.
        painter.setRenderHint(QPainter.Antialiasing)

        # Configuramos el color de relleno del círculo (verde).
        painter.setBrush(QColor("#F9F9F9"))

        # Borde del círculo en color negro.
        painter.setPen(QPen(Qt.black))

        # Calculamos el tamaño máximo posible de un cuadrado dentro del widget.
        # Esto asegura que el círculo no se deforme aunque el widget no sea cuadrado.
        lado = min(self.width(), self.height())

        # Creamos un rectángulo cuadrado, centrado en el widget.
        recto = QRect(
            (self.width() - lado) // 2,   # posición X centrada
            (self.height() - lado) // 2,  # posición Y centrada
            lado,                         # ancho del cuadrado
            lado                          # alto del cuadrado
        )

        # Dibujamos el círculo dentro del rectángulo calculado.
        painter.drawEllipse(recto)

        # Cambiamos el lápiz para dibujar texto en blanco.
        painter.setPen(QPen(Qt.black))

        # Dibujamos el texto centrado dentro del círculo mediante AlignCenter.
        painter.drawText(recto, Qt.AlignCenter, self._texto)


class BotonContador(QPushButton):

    # Señal personalizada que enviará un entero (snake_case)
    contador_actualizado = Signal(int)
 
      
    def __init__(self, parent=None):
        super().__init__("Añadir incidencia", parent)
        self.__contador = 0

        # Cuando se pulsa el botón estándar de Qt
        self.clicked.connect(self.__incrementar)

    def __incrementar(self):
        self.__contador = self.__contador + 1
       
        self.contador_actualizado.emit(self.__contador)  
    
    def contador(self):
        return self.__contador
    
        
        
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Panel de incidencias")
        self.resize(300, 400)

        contenedor = QWidget(self)
        layout = QVBoxLayout(contenedor)
        self.botonContador = BotonContador()

        self.etiqueta = QLabel("Incidencias Abiertas: 0")
        self.botonContador.contador_actualizado.connect(self.actualizar_etiqueta)

        # Nuestro widget personalizado
        self.indicador = IndicadorSimple(self)

        # Botón opcional para cambiar el texto
        boton = QPushButton("Añadir incidencia")
        boton_reset=QPushButton("Reset")
        boton.clicked.connect(self.cambiar_texto)
        boton_reset.clicked.connect(self.limpiar_incidencias)

        layout.addWidget(self.indicador)
        layout.addWidget(self.etiqueta)
        layout.addWidget(self.botonContador)
        layout.addWidget(boton_reset)
       
        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)

      
     

    def actualizar_etiqueta(self, valor):
        self.etiqueta.setText("Incidencias Abiertas:  " + str(valor))

    

    def cambiar_texto(self):
      self.indicador.setTexto("oK")
       

       
    def limpiar_incidencias(self):
        self.etiqueta.clear() 
        self.etiqueta.setText("Incidencias Abiertas: 0")



app =QApplication()

with open("Sandoval_Cristina_estilos.qss", "r") as f:
    app.setStyleSheet(f.read())

ventana= VentanaPrincipal()
ventana.show()
app.exec()