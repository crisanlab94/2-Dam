from PySide6.QtWidgets import QApplication, QMessageBox,QMainWindow,QPushButton

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Aplicación con mensaje critico")
        boton=QPushButton("Haz click aqui para ver el mensaje critico")
        boton.clicked.connect(self.mostrar_dialogo)
        self.setCentralWidget(boton)
    
    def mostrar_dialogo(self):
        boton_pulsado=QMessageBox.critical(
            self,
            #Titulo de la ventana 
            "Ejemplo de cuadro de mensaje critico ",
            # Texto de la ventana
            "Ha ocurrido un problema al realizar la acción",
            #Botones que queremos que aparezcan en el cuadro critico
            buttons=QMessageBox.Discard | QMessageBox.NoToAll | QMessageBox.Ignore,
            defaultButton=QMessageBox.Discard
        )

        #Comprobamos que boton ha seleccionado el usuario
        if boton_pulsado == QMessageBox.Discard:
            print("Descartado")
        elif boton_pulsado == QMessageBox.NoToAll:
            print("No a todo")
        else:
            print("Ignorado")


app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()
