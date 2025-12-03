from PySide6.QtWidgets import QPushButton,QApplication,QMainWindow

class BotonContador(QPushButton):
    def __init__(self, parent=None):
        texto_inicial ="Pulsado 0 veces"
        super().__init__(texto_inicial, parent)
        self.__contador=0
        self.clicked.connect(self._incrementar)

    def _incrementar(self):
        self.__contador=self.__contador + 1
        nuevo_texto = "Pulsado " + str(self.__contador) + " veces"
        self.setText(nuevo_texto)

class VentanaPrinciapl(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Prueba de BotonContador")

        #Creamos el boton
        boton=BotonContador(self)

        #Lo establecemos como widget central
        self.setCentralWidget(boton)

app=QApplication()
ventana= VentanaPrinciapl()
ventana.show()
app.exec()
