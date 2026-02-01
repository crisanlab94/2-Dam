package dosmapasLibreria;



public class LanzadorLibreria {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO SISTEMA DE STOCK ---");

        // Creamos los hilos usando la clase ClienteLibreria
        Thread t1 = new Thread(new ClienteLibreria("111", "Novela", 10));
        Thread t2 = new Thread(new ClienteLibreria("222", "Terror", 5));
        Thread t3 = new Thread(new ClienteLibreria("111", "Novela", -3)); // El mismo libro
        Thread t4 = new Thread(new ClienteLibreria("333", "Ensayo", 2));

        // Los lanzamos
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}