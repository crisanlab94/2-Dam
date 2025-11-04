from PySide6.QtWidgets import QApplication,QMainWindow,QCheckBox
from PySide6.QtCore import Qt

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Holaa!")

        etiqueta=QCheckBox("Esto es un checkbox")
        #Por defecto aparece como marcado
        etiqueta.setCheckState(Qt.Checked)

        #Señal que avisa si cambia el estado del check
        etiqueta.stateChanged.connect(self.muestra_estado)

        self.setCentralWidget(etiqueta)

    def muestra_estado (self,s):
        state=Qt.CheckState(s)
        print(state == Qt.CheckState.Checked)
        print(s)

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()
