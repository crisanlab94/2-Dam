# Cristina Sandoval Laborde 2ºCFGS DAM

#1.Crea tres listas con nombres de productos: ordenadores, periféricos y accesorios.

#Creo 3 listas con diferentes objetos dentro de [] entre "" separados por coma
lista1 = ["ordenador", "portatil", "PC"]
lista2 = ["cable de red", "cable SATA", "auricular"]
lista3 = ["mochila", "soporte", "webcam"]

#Imprimo cada lista
print(lista1) 
print(lista2)
print(lista3)

#2.Crea una tupla con tres precios base distintos.

#Las tuplas se delimitan por parentesis y se separan por comas
tupla=(720, 1500, 320)
#Imprimo tupla
print(tupla)

#3.Crea un diccionario llamado catalogo donde cada clave sea una categoría
#  (“Ordenadores”, “Periféricos”, “Accesorios”) y su valor sea la lista correspondiente.

#Los diccionarips se delimitan por {} y los elementos se separan por comas menos el último que no lleva nada, 
#la clave del valor se separa mediante :
catalogo = {
    "Ordenadores" : lista1,
    "Perifericos" : lista2,
    "Accesorios"  : lista3
}
print(catalogo)
#Segundo periférico

#En el print le digo que acceda a catalogo, dentro de el a la lista de perifericos y dentro de esto al objeto que está en la posición 1, que es el segundo elemento de la lista
print("Segundo periferico: ",catalogo["Perifericos"][1])