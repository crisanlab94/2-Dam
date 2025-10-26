# Cristina Sandoval Laborde  2ºCFGS DAM

# Validar número de alumnos
num_alumnos = int(input("Introduce el número de alumnos: "))
while num_alumnos <= 0:
    num_alumnos = int(input("El número debe ser mayor que 0. Introduce el número de alumnos: "))

# Contadores
medias = 0
aprobados = 0
mejorar = 0
suspensos = 0

# Para cada alumno
for i in range(1, num_alumnos + 1):
    print("\nAlumno", i)
    nombre = input("Nombre: ")

    # Validar número de notas
    num_notas = int(input("¿Cuántas notas tiene " + nombre + "? "))
    while num_notas <= 0:
        num_notas = int(input("El número de notas debe ser mayor que 0. ¿Cuántas notas tiene " + nombre + "? "))

    suma_notas = 0
    for j in range(1, num_notas + 1):
        nota = float(input("Introduce la nota " + str(j) + ": "))
        suma_notas += nota

    media = suma_notas / num_notas
    medias += media

    # Clasificación
    if media >= 5:
        print("Media de", nombre, ":", round(media, 2), "-> Aprobado")
        aprobados += 1
    elif 4 <= media < 5:
        print("Media de", nombre, ":", round(media, 2), "-> Necesita mejorar")
        mejorar += 1
    else:
        print("Media de", nombre, ":", round(media, 2), "-> Suspenso")
        suspensos += 1

# Media general del grupo
media_grupo = medias / num_alumnos

# Resumen final
print("\n---- RESUMEN FINAL ----")
print("Media del grupo:", round(media_grupo, 2))
print("Aprobados:", aprobados)
print("Necesita mejorar:", mejorar)
print("Suspensos:", suspensos)
