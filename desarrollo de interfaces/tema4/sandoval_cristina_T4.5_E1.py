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
        # La interfaz debe incluir tres botones 
        layout = QVBoxLayout()

        # Nombres de los informes proporcionados en la tarea 
        self.informes = [
            "DI_U05_A02_03.html", 
            "DI_U05_A02_08.html", 
            "DI_U05_A03_11.html"
        ]

        for nombre in self.informes:
            # Cada botón abrirá un informe distinto 
            btn = QPushButton(f"Abrir {nombre} en Navegador")
            btn.clicked.connect(lambda checked, n=nombre: self.abrir_en_navegador(n))
            layout.addWidget(btn)

        self.setLayout(layout)

    def abrir_en_navegador(self, nombre_fichero):
        # Se utiliza QDesktopServices para interactuar con el navegador del sistema 
        # Es fundamental obtener la ruta absoluta para evitar errores 
        ruta_absoluta = QDir().absoluteFilePath(nombre_fichero) 
        
        # Se convierte la ruta local en un objeto QUrl para abrirlo
        QDesktopServices.openUrl(QUrl.fromLocalFile(ruta_absoluta)) 

if __name__ == "__main__":
    app = QApplication(sys.argv)
    ex = Ejercicio1()
    ex.show()
    sys.exit(app.exec_())