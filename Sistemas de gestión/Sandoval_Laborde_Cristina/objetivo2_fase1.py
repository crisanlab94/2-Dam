# Cristina Sandoval Laborde 2ºCFGS DAM

#1.Pide dos enteros y muestra suma, resta, multiplicación y división .
num1=int(input("Dime un número entero: "))
num2=int(input("Dime otro número entero :"))
suma = num1 + num2
resta = num1 -num2
multiplicacion = num1 * num2
division= num1 / num2
print("- 1 -")
print("Suma:",suma)
print("Resta:",resta)
print("Multiplicación:",multiplicacion)
print("División:",division)


#2.Pide tres numeros y calcula el promedio y mueéstralo redondeado a 2 decimales
real1=float(input("Dime un número con dos decimales:"))
real2=float(input("Dime otro número con dos decimales:"))
real3=float(input("Dime otro número con dos decimales:"))
promedio=round((real1+real2+real3)/3,2)
print("- 2 -")
print("El promedio es:",promedio)

#3.Pide dos enteros y muestra:
#si el primero es mayor que el segundo,
#si son iguales,
#si el segundo es distinto de 0.

num3=int(input("Dime un número entero: "))
num4=int(input("Dime otro número entero :"))
print("- 3 -")
print("¿El primero es mayor ?: ",num3 > num4)
print("¿Son iguales?: ",num3 == num4 ) 
print ("¿El segundo es distinto de cero?: ",num2!=0)

#4.Pide dos valores lógicos escritos como True o False y muestra:
#  and, or, not(primero), not (segundo)
valor1=eval(input("Introduce el primer valor lógico (True/False): "))
valor2=eval(input("Introduce el segundo valor lógico (True/False) :"))
print("- 4 -")
print("Resultado de and:",valor1==valor2)
print("Resultado de or:",valor1==valor2 or valor1!=valor2 or valor2!=valor1)
print("Resultado de not primer valor:",valor1==False)
print("Resultado de not segundo valor:",valor2==False)

#5.Pide dos edades como texto, convierte a entero y
#  muestra suma y promedio (1 decimal).
edad1=int(input("Dime la edad de la primera persona: "))
edad2=int(input("Dime la edad de la segunda persona :"))
suma =(edad1+edad2)
promedio=round((suma/2),1)
print("- 5 -")
print("Edad de la primera persona:", edad1)
print("Edad de la segunda persona:", edad2)
print("Suma total:", suma)
print("Promedio:", promedio)

#6.Pide dos números (pueden ser reales) 
num5=int(input("Introduce el primer número: "))
num6=int(input("Introduce el segundo número:"))
print("- 6 -")
print("(a > 10) and (b < 5) :" ,(num5 > 10) and (num6 < 5) )
print("(a == b ) or ( b > 0) :",(num5 == num6 ) or ( num6 > 0))
print ("not ( a < b) :", not (num5 < num6))

#7.Pide dos reales, divide y muestra el resultado redondeado a 1 decimal.
num7=float(input("Introduce el dividendo: "))
num8=float(input("Introduce el divisor:"))
resultado=(num5/num6)
redondeo = round(resultado,1)
print("- 7 -")
print("Dividendo", num7)
print("Divisor", num8)
print("Resultado rdondeado", redondeo)
