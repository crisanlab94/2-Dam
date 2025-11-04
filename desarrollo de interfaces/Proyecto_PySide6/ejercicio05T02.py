from PySide6.QtWidgets import QApplication,QMainWindow,QCheckBox
from PySide6.QtCore import Qt

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        
        self.setWindowTitle("Preferencias del usuario")

        self.checkBok=QCheckBox("Esto es un checkbox")
        #Etiqueta normal (Sin estado inicial)
        #self.checkBok.setCheckState(Qt.Unchecked)

        #Por defecto aparece como marcado
        #self.checkBok.setCheckState(Qt.Checked)

        #tri-state
        self.checkBok.setTristate(True)
        self.checkBok.setCheckState(Qt.PartiallyChecked)
        

        #Señal que avisa si cambia el estado del check
        self.checkBok.stateChanged.connect(self.mostrar_estado)

        self.setCentralWidget(self.checkBok)

    def mostrar_estado (self,s):
        state=Qt.CheckState(s)
        if state == Qt.Checked:
            print("Marcado completamente")
            print(s)
        elif state == Qt.Unchecked:
            print("Desmarcado")
            print(s)
        else:
            print("Marcado parcialmente")
            print(s)

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()