from PySide6.QtWidgets import QApplication,QMainWindow,QComboBox

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Ejemplo QComboBox")
        combo = QComboBox()
        combo.addItems(["Uno","Dos","Tres"])

        combo.setEditable(True)
        combo.setInsertPolicy(QComboBox.InsertAlphabetically)
        #Maximo que se puede añadir
        combo.setMaxCount(6)

        #Cambia el indice
        combo.currentIndexChanged.connect(self.cambio_indice)
        #Cambio de texto
        combo.currentTextChanged.connect(self.cambio_texto)

        self.setCentralWidget(combo)

    def cambio_indice(self,i):
        print("Indice seleccionado:", i)

    def cambio_texto(self, s):
        print("Texto seleccionado: ", s)

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()