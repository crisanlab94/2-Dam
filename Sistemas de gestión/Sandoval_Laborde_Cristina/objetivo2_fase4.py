#Cristina Sandoval Laborde  2ºCFGS DAM
#Crea un programa que pida tres números al usuario y 
# muestre en pantalla los resultados de estas expresiones

# Pedimos los tres números (de tipo entero) al usuario
a = int(input("Introduce el primer número: "))
b = int(input("Introduce el segundo número: "))
c = int(input("Introduce el tercer número: "))


resultado1 = (a < b) and (b < c)
resultado2 = (a == b) or (b == c)
resultado3 = not (a > c)

# Imprimimos los resultados
print("(a < b) and (b < c)",resultado1)
print("(a == b) or (b == c)",resultado2)
print("not (a > c)",resultado3)
