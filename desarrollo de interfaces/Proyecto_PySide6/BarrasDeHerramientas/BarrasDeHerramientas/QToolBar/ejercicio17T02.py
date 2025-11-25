#Cristina Sandoval Laborde 2ªDAM

from PySide6.QtWidgets import (
    QApplication, QDialog, QMainWindow, QLabel,
    QVBoxLayout, QLineEdit, QPushButton, QMessageBox
)
from PySide6.QtCore import Qt


class DialogoLogin(QDialog):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Inicio de sesión")

    
        layout = QVBoxLayout()

        self.usuario = QLineEdit()
        self.usuario.setPlaceholderText("Usuario")
        layout.addWidget(self.usuario)

        self.password = QLineEdit()
        self.password.setPlaceholderText("Contraseña")
        self.password.setEchoMode(QLineEdit.Password)
        layout.addWidget(self.password)

     
        boton_validar = QPushButton("Iniciar sesión")
        boton_validar.clicked.connect(self.validar)
        layout.addWidget(boton_validar)

        self.setLayout(layout)

    def validar(self):
        if self.usuario.text() == "admin" and self.password.text() == "admin":
            self.accept() 
        else:
            QMessageBox.warning(
                self,
                "Error",
                "El usuario o la contraseña son incorrectos"
            )


class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ventana principal")
        etiqueta = QLabel("Ventana principal")
        etiqueta.setAlignment(Qt.AlignCenter)
        self.setCentralWidget(etiqueta)


#primero hay que mostrar un diálogo de login
app = QApplication([])
# Crear el diálogo de login
login = DialogoLogin()
# Aquí exec() devuelve Accepted si el usuario es admin
if login.exec() == QDialog.Accepted:   
    ventana = VentanaPrincipal()
    # Ventana principal a pantalla completa
    ventana.showMaximized()  
     # Ejecutar la app        
    app.exec()                        
else:
    # Salir si login incorrecto
    app.quit()




