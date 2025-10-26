#Cristina Sandoval Laborde  2ºCFGS DAM
#1. Evaluar un número
num= int(input ("Introduce un número: "))
if num > 0 :
    print("El número",num, "es positivo")
elif num < 0 :
     print("El número",num, "es negativo")
else:
    print("El número es igual a 0")

#2. Comparar dos números
num1= int(input("Escribe el primer número: "))
num2=int(input("Escribe el segundo número: "))
if num1 > num2 :
    print("El primero es nayor que el segundo")
elif num1 < num2 :
    print("El segundo es mayor que el primero")
else:
    print("Ambos números son iguales")

#3.Comprobar texto dentro de una frase
frase = input("Escribe una frase: ")
palabra= input("Escribe una palabra: ")

if palabra in frase:
    print("La palabra está en la frase")
else:
    print("La palabra no se encuentra")

#4.Verificar el formato de una cadena
texto=input("Escribe un texto: ")
if texto[0].isupper() :
    print("Empieza por mayúscula.")
elif texto[0].isupper():
    print("Empieza por mayúscula")
elif texto[-1] == '.':
    print("Termina en punto")
else:
    print("El texto no cumple con las condiciones")

#5. Clasificar la nota
nota = float(input("Introduce una nota (0-10): "))
if 0 <= nota <= 4:
    print("Insuficiente.")
elif nota == 5:
    print("Suficiente.")
elif nota == 6:
    print("Bien.")
elif 7 <= nota <= 8:
    print("Notable.")
elif 9 <= nota <= 10:
    print("Sobresaliente.")
else:
    print("Nota no válida.")

print("\nFin del programa.")