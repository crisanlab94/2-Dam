#Cristina Sandoval Laborde 2º DAM

from PySide6.QtWidgets import QApplication,QMainWindow,QRadioButton

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("QRadioButton")
        
        #Aparece predeterminado y setText(para modificar esa información)
        self.texto =QRadioButton("Activar función") 
        self.setCentralWidget(self.texto)

         # Activarlo por defecto
        self.texto.setChecked(True)
        
         # Conectar señal correctamente
        self.texto.toggled.connect(self.toggle)


    def toggle(self, checked):
        if checked:
            self.setWindowTitle("Función activada")
        else:
            self.setWindowTitle("Función desactivada")


app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()

