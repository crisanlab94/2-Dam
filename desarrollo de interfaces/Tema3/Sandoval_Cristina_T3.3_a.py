# Cristina Sandoval Laborde 2º DAM

import sys
from PySide6.QtWidgets import (
    QApplication,
    QWidget,
    QMainWindow,
    QVBoxLayout,
    QHBoxLayout, 
    QPushButton,
    QLabel 
)
from PySide6.QtGui import QPainter, QColor, QPen, QBrush
from PySide6.QtCore import QRect, Qt, QTimer 

class semaforo(QWidget):
    # Colores del semaforo
    colores =["rojo","amarillo","verde"] 

    def __init__(self, parent =None):
        super().__init__(parent)
        
        # Tamaño fijo del semaforo
        self.setFixedSize(120, 380)

        # Iniciar en rojo
        self.__estado_actual = self.colores[0] 

        # Boton cambiar
        self.__boton_cambiar = QPushButton("Cambiar",self) 

        # Conecto el boton cambiar a la logica
        self.__boton_cambiar.clicked.connect(self.__cambiar_estado)

        # AMPLIACIÓN: Implementación de QTimer
        # Añade un QTimer como atributo del componente 
        self.__timer = QTimer(self) 
        
        # Configura el temporizador para 1000 ms (1 segundo) 
        self.__timer.setInterval(1000) 
        
        # Conecta la señal timeout al método de cambio de estado 
        self.__timer.timeout.connect(self.__cambiar_estado) 
        
        # Iniciar el temporizador al crear el componente
        self.__timer.start()
        

        # Diseño de un QVBoxLayout
        layout = QVBoxLayout(self) 

        # Stretch empuja el boton hacia abajo
        layout.addStretch(1)

        # Boton abajo del todo
        layout.addWidget(self.__boton_cambiar, alignment=Qt.AlignCenter)

        
    # Métodos públicos obligatorios
    def estado(self):
        return self.__estado_actual
    
    def reiniciar(self):
        # Vuelve al estado 'rojo'
        if self.__estado_actual != self.colores[0]:
            self.__estado_actual = self.colores[0]
            self.update() 

    # Logica
    def __cambiar_estado(self):
        
        # Este método se llama tanto por el botón como por el QTimer 
        indice_actual=self.colores.index(self.__estado_actual)
        indice_siguiente =(indice_actual + 1) % len(self.colores)

        self.__estado_actual = self.colores[indice_siguiente]
        self.update() 

    # Metodo que dibuja
    def paintEvent(self, event):
        # QPainter es el objeto que permite dibujar dentro del widget
        painter = QPainter(self)

        # suavizar
        painter.setRenderHint(QPainter.Antialiasing) 

        
        # Cuerpo del semaforo centrado
        cuerpo_ancho = 90
        cuerpo_x = (self.width() - cuerpo_ancho) // 2 
        cuerpo_y = 10 
        cuerpo_alto = 250 
        
        # Qrect define el area del cuerpo
        cuerpo_rect = QRect(cuerpo_x, cuerpo_y, cuerpo_ancho,cuerpo_alto)
        
        
        # Dibujar el cuerpo gris oscuro
        painter.setBrush(QColor("#272626")) 
        painter.setPen(Qt.NoPen) 
        painter.drawRect(cuerpo_rect) 

        # Definiciones para las luces
        luz_diametro = 60
        luz_radio = luz_diametro // 2
        centro_x = cuerpo_x + (cuerpo_ancho // 2)

        # luces
        luces = [
            # Arriba (roja)
            (cuerpo_y + 40, QColor("red"),"rojo"),
            # En el centro (amarillo)
            (cuerpo_y + 125 , QColor("yellow"),"amarillo"),
            # Abajo (verde)
            (cuerpo_y + 210, QColor("green"),"verde")
                                
        ]
        
        color_apagado =QColor("#5c5c5c") # Luces apagadas en gris
        painter.setPen(QPen(QColor("#7f8c8d"),1)) 


        # Iterar para cada luz
        for centro_y, color_luz,nombre_estado in luces:

            # Calcular posición del cuadrante que contiene el círculo 
            luz_x = centro_x - luz_radio
            luz_y = centro_y - luz_radio
            
            
            if nombre_estado == self.__estado_actual:
                painter.setBrush(color_luz) 
            else:
                painter.setBrush(color_apagado) 
            
            
            painter.drawEllipse(luz_x, luz_y, luz_diametro, luz_diametro)

        # Finalizar la pintura
        painter.end()

        
class VentanaPrincipal(QMainWindow):
  
    def __init__(self):
        super().__init__()

        self.setWindowTitle("TAREA 3.3 Ampliación - Semaforo Automático")
        self.resize(300, 450)

        # Creamos una instancia del componente
        self.semaforo = semaforo()

        # Etiqueta para mostrar el estado actual
        self.label_estado = QLabel(f"Estado: {self.semaforo.estado()}")
        self.label_estado.setAlignment(Qt.AlignCenter) # Centrar texto en la label

        # Botón dedicado para llamar al método reiniciar()
        self.boton_reiniciar = QPushButton("Reiniciar (Volver a Rojo)")

        # Conexión: El timer y el botón interno llaman a la actualización de la interfaz externa
        self.semaforo._semaforo__timer.timeout.connect(self.__actualizar_interfaz_externa) 
        self.semaforo._semaforo__boton_cambiar.clicked.connect(self.__actualizar_interfaz_externa)

        # Conexión: Al pulsar el botón de reinicio, llamamos al método reiniciar() del semáforo
        self.boton_reiniciar.clicked.connect(self.__reiniciar_semaforo)

        # Creamos un layout central
        central_widget = QWidget()
        layout = QVBoxLayout(central_widget)
        
        # Centramos el componente semáforo
        layout.addWidget(self.semaforo, alignment=Qt.AlignCenter)
        
        # Añadimos la etiqueta de estado DEBAJO (vertical)
        layout.addWidget(self.label_estado)
        
        # Añadimos el botón de reinicio DEBAJO (vertical)
        layout.addWidget(self.boton_reiniciar)

        self.setCentralWidget(central_widget)

    def __actualizar_interfaz_externa(self):
        # Actualiza el texto de la etiqueta para reflejar el estado actual del semáforo.
        self.label_estado.setText(f"Estado: {self.semaforo.estado()}")

    def __reiniciar_semaforo(self):
        # Llama al método público reiniciar y actualiza la interfaz.
        self.semaforo.reiniciar()
        self.__actualizar_interfaz_externa()


app = QApplication([])
ventana = VentanaPrincipal()
ventana.show()
app.exec()