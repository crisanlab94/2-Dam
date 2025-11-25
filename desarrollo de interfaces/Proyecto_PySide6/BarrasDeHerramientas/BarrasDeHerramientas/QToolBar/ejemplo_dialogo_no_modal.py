from PySide6.QtWidgets import QApplication,QMainWindow,QPushButton,QLabel

class OtraVentana(QLabel):
    def __init__(self):
        super().__init__()
        self.setText("La otra ventana")

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Aplicación con dos ventanas")

        self.otra_ventana = None

        self.boton = QPushButton("Mostrar/ocultar otra ventana")
        self.boton.clicked.connect(self.mostrar_otra_ventana)
        self.setCentralWidget(self.boton)

    def mostrar_otra_ventana(self):
         # Si todavía no existe, la creamos
        if self.otra_ventana is None:
            self.otra_ventana = OtraVentana()
            self.otra_ventana.show()
        else:
            # Si existe pero está oculta, la mostramos
            if self.otra_ventana.isHidden():
                self.otra_ventana.show()
            # Si está visible, la ocultamos
            else:
                self.otra_ventana.hide()

app = QApplication()
ventana_principal=VentanaPrincipal()
ventana_principal.show()
app.exec()

