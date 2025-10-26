#Cristina Sandoval Laborde  2ºCFGS DAM
#Pedimos una frase o palabra
frase=input("Introduce una frase o palabra:")

#Formato del texto
print("\n----Formato del texto----")
print("Capitalizada:", frase.capitalize())
print("Mayúsculas:", frase.upper())
print("Minúsculas:", frase.lower())
print("Invertida:", frase.swapcase())

#Análisis del contenido
print("\n----Análisis del contenido----")
print("¿Solo letras?:", frase.isalpha())
print("¿Solo números?:", frase.isdigit())
print("¿Tiene letras y números?:", frase.isalnum())
print("¿Está en minúsculas?:", frase.islower())
print("¿Esta en mayúsculas?:", frase.isupper())

#Tamaño/longitud
num_caracteres=len(frase)
caracteres_reales=len(frase.replace(" ",""))
print("\n----Longitud----")
print("Número total de caracteres:", num_caracteres)
print("Caracteres reales(no contiene espacios)", caracteres_reales)

#Limpieza
print("\n----Limpieza----")
print("Sin espacios al principio:", frase.lstrip())
print("Sin espacios al final:", frase.rstrip())
print("Sin espacios ni delante ni detrás:", frase.strip())

#Reemplazar palabra
buscar_palabra= input("Palabra a buscar: ")
nueva_palabra = input("Palabra nueva: ")
frase_nueva = frase.replace(buscar_palabra,nueva_palabra)
print("Frase modificada:", frase_nueva)

#Caracteres
print("\n----Caracteres----")
print("Carácter mayor:", max(frase))
print("Carácter menor:", min(frase))

#Lista de palabras
lista_palabras=frase.split()
print("\n----Lista de palabras----")
print("Lista:", lista_palabras)
print("Número de palabras:", len(lista_palabras))

#División por '/'
dividido = frase.split()
print("\n----División por '/'----")
print("Resultado del split('/'):", dividido)

print("\n----Análisis completo finalizado----")