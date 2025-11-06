#Cristina Sandoval Laborde 2º DAM

from PySide6.QtWidgets import QApplication,QMainWindow,QRadioButton

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("QRadioButton")
        self.texto=texto
        #Aparece predeterminado y setText(para modificar esa información)
        texto =QRadioButton("Activar función") 
        self.setCentralWidget(texto)
        texto.toggle()
        texto.connect(self.toggle)


    def toggle(self):
        self.setWindowTitle("Funcion")


app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()

