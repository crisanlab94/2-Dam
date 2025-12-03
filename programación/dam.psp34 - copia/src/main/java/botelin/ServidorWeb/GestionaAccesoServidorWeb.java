package botelin.ServidorWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GestionaAccesoServidorWeb {

    public static void main(String[] args) {

        Semaphore sema = new Semaphore(12);

        List<Thread> listaPeticiones = new ArrayList<>();

       
        for (int i = 0; i < 20; i++) {
            listaPeticiones.add(new Thread(
                    new PeticionWeb("Peticion " + (i + 1), sema)
            ));
        }

     
        for (Thread peticion : listaPeticiones) {
            peticion.start();
        }
    }
}
