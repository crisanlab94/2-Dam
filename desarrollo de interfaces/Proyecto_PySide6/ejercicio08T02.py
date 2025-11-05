#Cristina Sandoval Laborde 2º DAM 
from PySide6.QtWidgets import QApplication,QMainWindow,QTextEdit
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Editor de texto")

        
        texto =QTextEdit()
        self.texto=texto
        texto.setPlaceholderText("Escribe aqui tu mensaje...")
        self.setCentralWidget(texto)

        #Mensaje que sale al principio
        texto.setPlainText("Bienvenido/a al editor de texto")
        texto.textChanged.connect(self.texto_modificado)

    def texto_modificado(self):
        texto_ahora = self.texto.toPlainText()
        print("Texto modificado", texto_ahora)

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()