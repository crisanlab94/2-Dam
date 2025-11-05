#Cristina Sandoval Laborde 2ºDAM
from PySide6.QtWidgets import QApplication,QMainWindow,QComboBox

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("QComboBox")
        self.combo = QComboBox(self)
        self.combo.addItems(["Python","Java","C++","Kotlin"])

        self.combo.setEditable(True)

    
        self.combo.lineEdit().returnPressed.connect(self.agregar_elemento)
        #Cambio ventana principal 
        self.combo.currentIndexChanged.connect(self.cambio_seleccion)



    def agregar_elemento(self):
        nuevo_texto = self.combo.currentText()
        if nuevo_texto and self.combo.count() < 6:
            index_actual = self.combo.currentIndex()
            self.combo.insertItem(index_actual + 1, nuevo_texto)
            self.combo.setCurrentIndex(index_actual + 1)

    def cambio_seleccion(self):
        i = self.combo.currentIndex()
        if i >= 0:
            texto = self.combo.itemText(i)
            print("Texto seleccionado:", texto)
            self.setWindowTitle(texto)

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()
