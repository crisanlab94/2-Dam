#Cristina Sandoval Laborde 2ªDAM
from PySide6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout,
    QPushButton, QFileDialog, QLabel, QColorDialog, QFontDialog, QMessageBox
)
from PySide6.QtGui import QColor
import sys

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Gestion de archivo y preferencia")

        self.label = QLabel("Aquí se mostrará el contenido del archivo.")
       #self.label.setStyleSheet("background-color: white; padding: 10px;")
        #self.label.setWordWrap(True)

        central = QWidget()
        layout = QVBoxLayout()

        boton_abrir = QPushButton("Abrir archivo de texto")
        boton_guardar = QPushButton("Guardar archivo como...")
        boton_color = QPushButton("Elegir color de fondo")
        boton_fuente = QPushButton("Cambiar fuente del texto")

        boton_abrir.clicked.connect(self.abrir_archivo)
        boton_guardar.clicked.connect(self.guardar_archivo)
        boton_color.clicked.connect(self.elegir_color)
        boton_fuente.clicked.connect(self.cambiar_fuente)

        layout.addWidget(boton_abrir)
        layout.addWidget(boton_guardar)
        layout.addWidget(boton_color)
        layout.addWidget(boton_fuente)
        layout.addWidget(self.label)

        central.setLayout(layout)
        self.setCentralWidget(central)

    def abrir_archivo(self):
        abrir_archivo = QFileDialog.getOpenFileName(
        self,
        caption="Abrir archivo ...",
        dir="./",
        filter="Documentos de texto (*.txt);;Documentos PDF (*.pdf)",
        selectedFilter="Documentos de texto (*.txt)"
    )

        archivo = abrir_archivo[0]   # Ruta del archivo seleccionado
        # print(archivo) // Para comprobar que va 

    def guardar_archivo(self):
        guardar_archivo = QFileDialog.getSaveFileName(
        self,
        caption="Guardar archivo ...",
        dir="./",
        filter="Documentos de texto (*.txt);;Documentos PDF (*.pdf)",
        selectedFilter="Documentos de texto (*.txt)"
    )

        archivo = guardar_archivo[0]

    def elegir_color(self):
        # Abrir diálogo para elegir color
        color = QColorDialog.getColor()  
         # Comprobar si el usuario seleccionó un color válido
        if color.isValid():             
            self.label.setStyleSheet(
                # Aplicar color de fondo al QLabel
                f"background-color: {color.name()}; padding: 10px;"
            )  

    def cambiar_fuente(self):
        seleccionada, fuente = QFontDialog.getFont(self)
        if seleccionada:
            self.label.setFont(fuente)  

app =QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()

        

        