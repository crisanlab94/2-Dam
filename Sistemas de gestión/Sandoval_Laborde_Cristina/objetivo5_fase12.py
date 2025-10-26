# Cristina Sandoval Laborde  2ºCFGS DAM
#Calculadora
#Primero importamos math
import math

#Función sumar
#declaramos 2 variables a y b de tipo float donde se guardan los números
#Luego los sumamos y redondeamos a 2 decimales
def sumar():
        a=float(input("Introduce el primer número:"))
        b=float(input("Introduce el segundo número:"))
        print("Resultado de la suma:", round(a+b,2))

#Función restar
#Lo mismo que en la suma, se declaran 2 variables y se hace la resta con redondeo
def restar():
    a=float(input("Introduce el primer número:"))
    b=float(input("Introduce el segundo número:"))
    print("Resultado de la resta:",round(a-b,2))

#Función multiplicar
def multiplicar():
    a = float(input("Introduce el primer número: "))
    b = float(input("Introduce el segundo número: "))
    print("Resultado de la multiplicación:", round(a * b, 2)) 

#Función dividir
def dividir():
    a = float(input("Introduce el dividendo: "))
    b = float(input("Introduce el divisor: "))
    if b == 0:
        print("No se puede dividir entre cero.")
    else:
        print("Resultado de la división:", round(a / b, 2))

#Función potencia
def potencia():
    base = float(input("Base: "))
    exponente = float(input("Exponente: "))
    print("Resultado:", round(base ** exponente, 2))

#Función raiz cuadrada
def raiz_cuadrada():
    num = float(input("Introduce un número: "))
    if num < 0:
        print("No se puede calcular la raíz cuadrada de un número negativo")
    else:
        print("Resultado:", round(math.sqrt(num), 2))

#Función modulo
def modulo():
    a = float(input("Introduce el primer número: "))
    b = float(input("Introduce el segundo número: "))
    print("Resultado del módulo:", round(a % b, 2))

#Menú
def menu():
    salir=False
    while not salir:
        print("\n===========================")
        print("   CALCULADORA ")
        print("===========================")
        print("1) Sumar")
        print("2) Restar")
        print("3) Multiplicar")
        print("4) Dividir")
        print("5) Operaciones avanzadas")
        print("6) Salir")

        opcion = input("Elige una opción: ")

        if opcion == "1":
            sumar()
        elif opcion == "2":
            restar()
        elif opcion == "3":
            multiplicar()
        elif opcion == "4":
            dividir()
        elif opcion == "5":
            submenu_avanzado()
        elif opcion == "6":
            print("Fin del programa. ¡Hasta pronto!")
            salir=True
        else:
            print("Opción no válida. Inténtalo de nuevo.")

#Opciones avanzadas
def submenu_avanzado():
    volver=False
    while not volver:
        print("\nOperaciones avanzadas:")
        print("a) Potencia")
        print("b) Raíz cuadrada")
        print("c) Módulo")
        print("d) Volver")

        opcion = input("Selecciona una opción: ").lower()

        if opcion == "a":
            potencia()
        elif opcion == "b":
            raiz_cuadrada()
        elif opcion == "c":
            modulo()
        elif opcion == "d":
            volver=True
        else:
            print("Opción no válida. Inténtalo de nuevo.")

# --- EJECUCIÓN ---
menu()