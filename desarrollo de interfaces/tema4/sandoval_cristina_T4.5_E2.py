#Cristina Sandoval LABORDE 2ºDAM

import sys
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QComboBox
from PyQt5.QtWebEngineWidgets import QWebEngineView 
from PyQt5.QtCore import QUrl, QDir

class Ejercicio2(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ejercicio 2: Cristina Sandoval") 
        self.resize(1000, 750)
        self.initUI()

    def initUI(self):
        layout = QVBoxLayout()

        # Selector para cambiar el informe visualizado 
        self.combo = QComboBox() 
        self.informes = ["DI_U05_A02_03.html", "DI_U05_A02_08.html", "DI_U05_A03_11.html"] 
        self.combo.addItems(self.informes)
        self.combo.currentTextChanged.connect(self.actualizar_vista)
        layout.addWidget(self.combo)

        # Componente para incrustar el contenido web 
        self.browser = QWebEngineView() 
        layout.addWidget(self.browser)

        self.setLayout(layout)
        # Carga inicial [cite: 51]
        self.actualizar_vista(self.combo.currentText())

    def actualizar_vista(self, nombre_fichero):
        # Carga el informe seleccionado directamente en el componente 
        ruta = QDir().absoluteFilePath(nombre_fichero)
        self.browser.load(QUrl.fromLocalFile(ruta)) 

if __name__ == "__main__":
    app = QApplication(sys.argv)
    ex = Ejercicio2()
    ex.show()
    sys.exit(app.exec_())