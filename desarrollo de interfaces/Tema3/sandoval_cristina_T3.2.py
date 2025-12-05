#Cristina Sandoval Laborde 2º DAM

from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout,
    QLabel, QPushButton, QTextEdit
)
from PySide6.QtCore import Signal
from PySide6.QtGui import QPalette, QColor



#-----AreaTextoLimitada (deriva de QTextEdit)------

class AreaTextoLimitada(QTextEdit):

    longitud_cambiada = Signal(int)
    limite_superado = Signal(bool)

    def __init__(self, parent=None):
        super().__init__(parent)

        self.__limite = 200

        self.textChanged.connect(self.__procesar_texto)

    def max_caracteres(self):
        return self.__limite

    def __procesar_texto(self):
        texto = self.toPlainText()
        longitud = len(texto)

        # señal personalizada
        self.longitud_cambiada.emit(longitud)

        # Cambios visual con palette()
        pal = self.palette()

        if longitud <= self.__limite * 0.8:
            pal.setColor(QPalette.Base, QColor("white"))

        elif self.__limite * 0.8 < longitud <= self.__limite:
            pal.setColor(QPalette.Base, QColor(255, 255, 180))  

        else:
            pal.setColor(QPalette.Base, QColor(255, 180, 180))  

        self.setPalette(pal)

        # Límite superado
        self.limite_superado.emit(longitud > self.__limite)



# -----EtiquetaContadorCaracteres (QLabel)------

class EtiquetaContadorCaracteres(QLabel):

    def __init__(self, limite, parent=None):
        super().__init__(parent)
        self.__limite = limite
        self.actualizar_contador(0)

    def actualizar_contador(self, longitud):
        self.setText(f"Caracteres: {longitud} / {self.__limite}")

        pal = self.palette()

        if longitud <= self.__limite * 0.8:
            pal.setColor(QPalette.WindowText, QColor("black"))

        elif self.__limite * 0.8 < longitud <= self.__limite:
            pal.setColor(QPalette.WindowText, QColor("orange"))

        else:
            pal.setColor(QPalette.WindowText, QColor("red"))

        self.setPalette(pal)



# -----BotonLimpiarAviso (QPushButton)---------

class BotonLimpiarAviso(QPushButton):

    texto_limpiado = Signal()

    def __init__(self, area_texto, parent=None):
        super().__init__("Limpiar texto", parent)

        self.__area = area_texto

        self.__poner_color(QColor(230, 230, 230))  

        self.clicked.connect(self.__limpiar)

    def __poner_color(self, color):
        pal = self.palette()
        pal.setColor(QPalette.Button, color)
        self.setPalette(pal)

    def __limpiar(self):
        self.__area.clear()

        # Verde suave tras limpiar
        self.__poner_color(QColor(180, 255, 180))

        # Emitimos señal personalizada
        self.texto_limpiado.emit()



# -----Ventana Principal------

class VentanaPrincipal(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("Editor de notas con avisos")

        contenedor = QWidget()
        layout = QVBoxLayout()

        # Instanciar widgets derivados
        self.area = AreaTextoLimitada()
        self.etiqueta = EtiquetaContadorCaracteres(self.area.max_caracteres())
        self.boton = BotonLimpiarAviso(self.area)
        self.mensaje = QLabel("")

        # Conexiones
        self.area.longitud_cambiada.connect(self.etiqueta.actualizar_contador)
        self.boton.texto_limpiado.connect(self.__mostrar_mensaje)

        layout.addWidget(self.etiqueta)
        layout.addWidget(self.area)
        layout.addWidget(self.boton)
        layout.addWidget(self.mensaje)

        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)

    def __mostrar_mensaje(self):
        self.mensaje.setText("Texto limpiado correctamente")

app = QApplication()
ventana = VentanaPrincipal()
ventana.show()
app.exec()
