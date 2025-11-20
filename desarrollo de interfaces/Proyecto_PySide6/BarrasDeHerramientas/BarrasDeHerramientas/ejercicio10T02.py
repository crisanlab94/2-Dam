# Cristina Sandoval Laborde 
from PySide6.QtWidgets import QApplication, QMainWindow
from PySide6.QtGui import QAction, QKeySequence

# Nuestra ventana principal hereda de QMainWindow
class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ventana principal con menús")

        #Barra de menu
        barra_menu = self.menuBar()

        # Menú &Archivo
        menu = barra_menu.addMenu("&Archivo")

        # Acción 1: mostrar mensaje
        accion = QAction("Mostrar mensaje", self)
        accion.setShortcut(QKeySequence("Ctrl+M"))  
        accion.triggered.connect(self.mostrar_mensaje)  

        # Añadimos la acción al menú
        menu.addAction(accion)

        # Separador
        menu.addSeparator()

        # Acción 2: Cambiar título
        accion_cambiar_titulo = QAction("Cambiar título", self)
        accion_cambiar_titulo.setShortcut(QKeySequence("Ctrl+L"))
        accion_cambiar_titulo.triggered.connect(self.cambiar_titulo)
        menu.addAction(accion_cambiar_titulo)

        # Separador
        menu.addSeparator()

        # Acción 3: Salir
        accion_salir = QAction("Salir", self)
        accion_salir.setShortcut(QKeySequence("Ctrl+Q"))
        accion_salir.triggered.connect(self.close)  
        menu.addAction(accion_salir)

    def mostrar_mensaje(self):
        print("Hola desde el menú")

    def cambiar_titulo(self):
        self.setWindowTitle("Título cambiado desde el menú")

app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()