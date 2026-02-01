package dosmapasbiblioteca;



public class LanzadorBiblioteca {
    public static void main(String[] args) {
        // Juan coge 2 libros diferentes
        new Thread(new ClienteBiblioteca("123A", "El Quijote","Juan", 15)).start();
        new Thread(new ClienteBiblioteca("123A", "Harry Potter","Juan", 7)).start();
        
        // Maria coge 1 libro
        new Thread(new ClienteBiblioteca("456B", "Clean Code","Maria", 30)).start();
    }
}