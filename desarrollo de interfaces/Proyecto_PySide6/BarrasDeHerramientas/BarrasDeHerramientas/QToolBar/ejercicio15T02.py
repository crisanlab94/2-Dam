#Cristina Sandoval Laborde 2ºDAM

from PySide6.QtWidgets import QApplication, QMessageBox,QMainWindow,QPushButton

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio15")
        boton=QPushButton("Gestionar tarea")
        boton.clicked.connect(self.gestionar_tarea)
        self.setCentralWidget(boton)

    def gestionar_tarea(self):
        boton_pulsado=QMessageBox.question(
            self,
            #Titulo de la ventana 
             "Acción sobre la tarea",
            # Texto de la ventana
            "¿Qué quieres hacer con la tarea seleccionada?",
            #Botones que queremos que aparezcan en el cuadro critico
            buttons=QMessageBox.StandardButton.Yes | QMessageBox.StandardButton.No | QMessageBox.StandardButton.Ignore,
            defaultButton=QMessageBox.StandardButton.Ignore
        )

        if boton_pulsado == QMessageBox.StandardButton.Yes:
            QMessageBox.information(self, "Resultado", "La tarea se ha marcado como completada.")
        elif boton_pulsado == QMessageBox.StandardButton.No:
            QMessageBox.information(self, "Resultado", "La tarea se ha pospuesto para más tarde.")
        else:  # Ignore
            QMessageBox.information(self, "Resultado", "La tarea se mantiene sin cambios.")

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()


