class Cartera(object):

    def __init__(self, saldo_inicial=0):
        # Si el saldo inicial no es un entero positivo, empezamos en 0
        if type(saldo_inicial) != int or saldo_inicial < 0:
            self.saldo = 0
        else:
            self.saldo = saldo_inicial

    def ingresar(self, cantidad):
        # Devuelve el nuevo saldo. Si la cantidad no es un entero positivo, devuelve None.
        nuevo_saldo = None
        if type(cantidad) == int and cantidad >= 0:
            self.saldo = self.saldo + cantidad
            nuevo_saldo = self.saldo
        return nuevo_saldo

    def gastar(self, cantidad):
        # Devuelve el nuevo saldo. Si no hay saldo suficiente o la cantidad no es vÃ¡lida, devuelve None.
        nuevo_saldo = None
        if type(cantidad) == int and cantidad >= 0:
            if self.saldo >= cantidad:
                self.saldo = self.saldo - cantidad
                nuevo_saldo = self.saldo
        return nuevo_saldo