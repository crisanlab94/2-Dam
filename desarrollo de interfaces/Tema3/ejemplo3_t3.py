from PySide6.QtWidgets import (
    QApplication,
    QMainWindow,
    QWidget,
    QVBoxLayout,
    QLabel,
    QPushButton
)
from PySide6.QtCore import Signal

class BotonContador(QPushButton):

    # Señal personalizada que enviará un entero (snake_case)
    contador_actualizado = Signal(int)

    def __init__(self, parent=None):
        super().__init__("Pulsado 0 veces", parent)
        self.__contador = 0

        # Cuando se pulsa el botón estándar de Qt
        self.clicked.connect(self.__incrementar)

    def __incrementar(self):
        self.__contador = self.__contador + 1
        nuevo_texto = " Pulsado "+ str(self.__contador) + " veces"
        self.setText(nuevo_texto)

        # Emitimos la señal con el nuevo valor
        self.contador_actualizado.emit(self.__contador)  
    
    def contador(self):
        return self.__contador
    
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Prueba de BotonContador con señal personalizada")

        contenedor = QWidget()
        layout= QVBoxLayout()
        
        self.boton = BotonContador()

        self.etiqueta = QLabel("Aun no has pulsado el boton")

          # Conectamos la señal personalizada del botón (snake_case)
        self.boton.contador_actualizado.connect(self.actualizar_etiqueta)

        # Añadimos al layout
        layout.addWidget(self.boton)
        layout.addWidget(self.etiqueta)

        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)

    def actualizar_etiqueta(self, valor):
        self.etiqueta.setText("La señal avisó: contador = " + str(valor))

app = QApplication()
ventana = VentanaPrincipal()
ventana.show()
app.exec()




