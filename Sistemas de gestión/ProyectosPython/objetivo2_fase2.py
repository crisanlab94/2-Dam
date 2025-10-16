# Cristina Sandoval Laborde 2ºCFGS DAM

#1.Pide al usuario los siguientes datos con input():
#•Nombre y apellidos
#•Curso
#•Grupo
#•Carpeta del proyecto (solo el nombre, por ejemplo: Python)

nombre_apellidos=input("Escribe tu nombre y apellidos: ") # Aqui pido por teclado el nomnre y apellidos de la persona
curso=input("Escribe tu curso: ") # Aqui pido el curso de la persona
grupo=input("Escribe tu grupo: ") # Aqui pido el grupo de clase de la pesona
carpeta_proyecto=input("Escribe la ruta del proyecto: ") # Aqui pido la ruta del proyecto de la persona
#Ahora saco por pantalla la información que me piden en un solo print
# con \n hago un salto de linea para escribir lo siguiente en la linea de abajo de lo que estoy escribiendo
# Con \t hago un espacio, lo uso para echar a la derecha del curso el grupo
print("--------------------\nFicha del alumno/a\n--------------------\nNombre :",nombre_apellidos,"\nCurso :", curso,"\tGrupo:",grupo,"\nRuta del proyecto:",carpeta_proyecto,"\n--------------------")
