#Cristina Sandoval Laborde
from PySide6.QtWidgets import QApplication,QMainWindow,QLineEdit

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Escribe tu ciudad")

        
        texto =QLineEdit()
        texto.setMaxLength(20)
        texto.setPlaceholderText("Escribe tu ciudad")

        texto.returnPressed.connect(self.cambia_titulo)
       
        
        self.setCentralWidget(texto)
        self.texto=texto

        self.texto=texto

  
    def cambia_titulo(self):
       contenido = self.texto.text()
       if contenido:  
            self.setWindowTitle(contenido)
       else:
            self.setWindowTitle("Sin ciudad")
    

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()