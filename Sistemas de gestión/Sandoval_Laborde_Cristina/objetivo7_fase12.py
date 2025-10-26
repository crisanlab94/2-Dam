# Cristina Sandoval Laborde  2ºCFGS DAM
# Clase base Vehiculo
class Vehiculo:
    def __init__(self, marca, velocidad=0):
        self.marca = marca
        self.velocidad = velocidad

    def acelerar(self, v):
        self.velocidad += v

    def desacelerar(self, v):
        self.velocidad -= v
        if self.velocidad < 0:
            self.velocidad = 0  

    def mostrar_velocidad(self):
        print("Tu velocidad actual es:", self.velocidad, "km/h")


# Subclase Coche
class Coche(Vehiculo):
    def __init__(self, marca, velocidad=0, bocina="¡tuuut!"):
        super().__init__(marca, velocidad)
        self.bocina = bocina

    def tocar_claxon(self):
        print(self.bocina)


# Uso del programa
coche_1 = Coche("Peugeot 207", 30)
print("La velocidad inicial del coche es:", coche_1.velocidad)

coche_1.acelerar(60)
coche_1.desacelerar(25)
coche_1.mostrar_velocidad()

coche_1.tocar_claxon()
