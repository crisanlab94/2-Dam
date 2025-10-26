# Cristina Sandoval Laborde  2ºCFGS DAM
#Creamos la clase Autor y le pasamos los atributos 
#Creamos el método
class Autor:
    def __init__(self,nombre,apellidos):
        self.nombre = nombre
        self.apellidos=apellidos
    def mostrar_autor(self):
        print(self.nombre," ",self.apellidos)

#Clase Libro
class Libro:
    def __init__(self, titulo, isbn):
        self.titulo = titulo
        self.isbn = isbn
        self.autor = None

    def añadir_autor(self, autor):
        self.autor = autor

    def mostrar_libro(self):
        if self.autor:
            print("Título:", self.titulo, "| ISBN:", self.isbn, "| Autor:", end=" ")
            self.autor.mostrar_autor()
        else:
            print("Título:", self.titulo, "| ISBN:", self.isbn, "| Autor: Desconocido")

# Clase Biblioteca
class Biblioteca:
    def __init__(self):
        self.lista_libros = []

    def numero_libros(self):
        print("Número de libros en la biblioteca:", len(self.lista_libros))

    def añadir_libro(self, libro):
        self.lista_libros.append(libro)
        print("Libro añadido correctamente.")

    def borrar_libro(self, titulo):
        encontrado = False
        for libro in self.lista_libros:
            if libro.titulo.lower() == titulo.lower():
                self.lista_libros.remove(libro)
                encontrado = True
                print("Libro borrado correctamente.")
                break
        if not encontrado:
            print("No se encontró un libro con ese título.")
    def mostrar_biblioteca(self):
        if len(self.lista_libros) == 0:
            print("La biblioteca está vacía.")
        else:
            print("LIBROS EN LA BIBLIOTECA ")
            for libro in self.lista_libros:
                libro.mostrar_libro()

#Menú
def mostrar_menu():
    print("\n==============================")
    print("      MENÚ BIBLIOTECA")
    print("==============================")
    print("1) Añadir libro a la biblioteca")
    print("2) Mostrar biblioteca")
    print("3) Borrar libro de la biblioteca")
    print("4) Mostrar el número de libros")
    print("5) Salir")

#Añadir libro a biblioteca
def añadir_libro_a_biblioteca(biblioteca):
    titulo = input("Introduce el título del libro: ")
    isbn = input("Introduce el ISBN del libro: ")
    nombre_autor = input("Introduce el nombre del autor: ")
    apellidos_autor = input("Introduce los apellidos del autor: ")

    autor = Autor(nombre_autor, apellidos_autor)
    libro = Libro(titulo, isbn)
    libro.añadir_autor(autor)

    biblioteca.añadir_libro(libro)

# Programa principal
def main():
    biblioteca = Biblioteca()
    salir = False

    while not salir:
        mostrar_menu()
        opcion = input("Selecciona una opción: ")

        if opcion == "1":
            añadir_libro_a_biblioteca(biblioteca)
        elif opcion == "2":
            biblioteca.mostrar_biblioteca()
        elif opcion == "3":
            titulo = input("Introduce el título del libro a borrar: ")
            biblioteca.borrar_libro(titulo)
        elif opcion == "4":
            biblioteca.numero_libros()
        elif opcion == "5":
            print("Fin del programa.")
            salir = True
        else:
            print("Opción no válida. Inténtalo de nuevo.")


# Ejecutar programa
main()