from PySide6.QtWidgets import QLineEdit, QWidget
from PySide6.QtCore import Signal, Qt
from PySide6.QtGui import QPainter, QBrush, QPen, QFont, QColor

class CampoNombreAula(QLineEdit):
    # Señal que avisa a la ventana principal si es válido (True) o no (False)
    validacion_cambiada = Signal(bool)

    def __init__(self, parent=None):
        super().__init__(parent)
        self.setPlaceholderText("Nombre del aula (mín. 3 letras)")
        self.textChanged.connect(self.validar_contenido)
        self.es_valido = False
        # Estilo base
        self.setStyleSheet("background-color: white; color: black; padding: 5px;")

    def validar_contenido(self):
        texto = self.text()
        
        if not texto:
            # Vacío -> Blanco
            self.setStyleSheet("background-color: white; color: black; padding: 5px;")
            self.es_valido = False
        elif len(texto) < 3:
            # < 3 letras -> Rojo clarito
            self.setStyleSheet("background-color: #ffcccc; color: black; padding: 5px;")
            self.es_valido = False
        else:
            # >= 3 letras -> Verde clarito
            self.setStyleSheet("background-color: #ccffcc; color: black; padding: 5px;")
            self.es_valido = True

        # Emitimos la señal para que la Ventana Principal se entere
        self.validacion_cambiada.emit(self.es_valido)

class IndicadorEstadoAula(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.setMinimumSize(250, 250)
        self.estado = "Preparando"  # Estado inicial por defecto
        
        self.colores = {
            "Correcto": QColor("#2ecc71"),   
            "Preparando": QColor("#f1c40f"), 
            "Incidencia": QColor("#e74c3c")  
        }

    def set_estado(self, nuevo_estado):
        if nuevo_estado in self.colores:
            self.estado = nuevo_estado
            self.update() 

    def paintEvent(self, event):
        painter = QPainter(self)
        painter.setRenderHint(QPainter.RenderHint.Antialiasing)

        lado = min(self.width(), self.height())
        radio = (lado // 2) - 10 
        centro = self.rect().center()

        color = self.colores.get(self.estado, Qt.GlobalColor.gray)
        painter.setBrush(QBrush(color))
        painter.setPen(QPen(Qt.GlobalColor.black, 4))

        painter.drawEllipse(centro, radio, radio)

        painter.setPen(Qt.GlobalColor.black)
        rect_texto = self.rect()
        font = QFont("Arial", 24, QFont.Weight.Bold) 
        painter.setFont(font)
        
        painter.drawText(rect_texto, Qt.AlignmentFlag.AlignCenter, self.estado)