#Cristina Sandoval Laborde 2ºDAM

from PySide6.QtWidgets import (
    QApplication,
    QMainWindow,
    QWidget,
    QVBoxLayout,
    QLabel,
    QLineEdit,
    QPushButton,
    QCheckBox,
    QComboBox,
    QRadioButton,
    QHBoxLayout
)
from PySide6.QtCore import Qt

class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Ejemplo de personalización componentes")
        self.setMinimumSize(800, 600)  

        # --- Titulo ---
        self.titulo = QLabel("Formulario de registro")
        #Darle un objectName para personalizar el estilo en el qss
        self.titulo.setObjectName("titulo")
        self.titulo.setMinimumHeight(40)  
        self.titulo.setAlignment(Qt.AlignCenter)

        # ---- QLineEdit (campos de texto) ----

        #Nombre
        self.label = QLabel("Nombre:")
        self.input_nombre = QLineEdit()
        self.input_nombre.setPlaceholderText("Introduce tu nombre")

        #Apellidos
        self.label1 = QLabel("Apellidos:")
        self.input_apellidos = QLineEdit()
        self.input_apellidos.setPlaceholderText("Introduce tus apellidos")

        #Numero de telefono
        self.label2 = QLabel("Número de telefono:")
        self.input_numero = QLineEdit()
        self.input_numero.setPlaceholderText("Introduce tu número de telefono")

        #correo electronico
        self.label3 = QLabel("Correo eléctronico:")
        self.input_correo = QLineEdit()
        self.input_correo.setPlaceholderText("Introduce tu correo electronico")

       
      
        # ---- QComboBox  ----
        self.combo_ciudad = QComboBox()
        self.combo_ciudad.addItems(["Madrid", "Barcelona", "Sevilla", "Valencia", "Bilbao"])
        self.combo_ciudad.setEditable(False)

          # ---- QRadioButton ----
        self.radio_hombre = QRadioButton("Hombre")
        self.radio_mujer = QRadioButton("Mujer")
        self.radio_otro = QRadioButton("Otro")

         # ---- QCheckBox  ----
        self.check_terminos = QCheckBox("Acepto los terminos y condiciones")
        self.check_newsletter = QCheckBox("Deseo recibir noticias por email")
        #Darle un objectName para personalizar el estilo en el qss
        self.check_newsletter.setObjectName("newsletterCheck") 

        #Botones
        self.boton_enviar = QPushButton("Enviar")
        self.boton_enviar.setObjectName("btnEnviar")


        self.boton_borrar = QPushButton("Borrar")
        self.boton_borrar.setObjectName("btnBorrar")


        # --- Layout principal ---
        layout = QVBoxLayout()

        #Titulo arriba
        layout.addWidget(self.titulo)

        #Nombre
        layout.addWidget(self.label)
        layout.addWidget(self.input_nombre)

        #Apellidos
        layout.addWidget(self.label1)
        layout.addWidget(self.input_apellidos)

        #Numero
        layout.addWidget(self.label2)
        layout.addWidget(self.input_numero)

        #Correo
        layout.addWidget(self.label3)
        layout.addWidget(self.input_correo)

        #Ubicacion
        layout.addWidget(QLabel("Selecciona la ubicacion de la academia:"))
        layout.addWidget(self.combo_ciudad)

        #Genero
        layout.addWidget(QLabel("Selecciona tu género:"))
        layout.addWidget(self.radio_hombre)
        layout.addWidget(self.radio_mujer)
        layout.addWidget(self.radio_otro)

        # CheckBox --Terminos y noticias--
        layout.addWidget(self.check_terminos)
        layout.addWidget(self.check_newsletter)

        # --- Layout horizontal para los botones ---
        layout_botones = QHBoxLayout()
        layout_botones.addWidget(self.boton_enviar)
        layout_botones.addWidget(self.boton_borrar)
        layout_botones.setSpacing(20)  

        layout.addLayout(layout_botones)

        contenedor = QWidget()
        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)

app =QApplication()

with open("Sandoval_Cristina_estilos_T3.1.qss", "r") as f:
    app.setStyleSheet(f.read())

ventana= MainWindow()
ventana.show()
app.exec()