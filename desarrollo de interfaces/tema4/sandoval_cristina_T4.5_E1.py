#Cristina Sandoval Laborde 2ºDAM

import sys
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QPushButton
from PyQt5.QtCore import QUrl, QDir
from PyQt5.QtGui import QDesktopServices

class Ejercicio1(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio 1: Apertura Externa - Cristina Sandoval")
        self.resize(400, 200)
        self.initUI()

    def initUI(self):
        layout = QVBoxLayout()

        # Creamos los botones uno a uno, que es lo más habitual
        self.btn1 = QPushButton("Abrir DI_U05_A02_03.html en Navegador")
        self.btn2 = QPushButton("Abrir DI_U05_A02_08.html en Navegador")
        self.btn3 = QPushButton("Abrir DI_U05_A03_11.html en Navegador")

        # Conectamos cada botón a una función que se encarga de su informe
        self.btn1.clicked.connect(self.abrir_informe_1)
        self.btn2.clicked.connect(self.abrir_informe_2)
        self.btn3.clicked.connect(self.abrir_informe_3)

        layout.addWidget(self.btn1)
        layout.addWidget(self.btn2)
        layout.addWidget(self.btn3)

        self.setLayout(layout)

    # Funciones específicas para cada informe
    def abrir_informe_1(self):
        self.lanzar_navegador("DI_U05_A02_03.html")

    def abrir_informe_2(self):
        self.lanzar_navegador("DI_U05_A02_08.html")

    def abrir_informe_3(self):
        self.lanzar_navegador("DI_U05_A03_11.html")

    def lanzar_navegador(self, nombre_fichero):
        ruta_absoluta = QDir().absoluteFilePath(nombre_fichero) 
        QDesktopServices.openUrl(QUrl.fromLocalFile(ruta_absoluta)) 

if __name__ == "__main__":
    app = QApplication(sys.argv)
    ex = Ejercicio1()
    ex.show()
    sys.exit(app.exec_())