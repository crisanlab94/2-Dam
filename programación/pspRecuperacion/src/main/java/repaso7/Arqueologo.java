package repaso7;

public class Arqueologo implements Runnable {
    private String nombre;
    private Yacimiento yacimiento;

    public Arqueologo(String nombre, Yacimiento yacimiento) {
        this.nombre = nombre;
        this.yacimiento = yacimiento;
    }

    @Override
    public void run() {
        try {
            // Simulamos que están excavando (esto no es sincronizado, todos a la vez)
            System.out.println("⚒️ " + nombre + " empieza a excavar...");
            Thread.sleep((long) (Math.random() * 4000)); 
            
            // Cuando encuentran algo, anotan (aquí sí hay sincronización)
            yacimiento.anotarPieza(nombre);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}