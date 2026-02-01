package mapaSobreescribeSuma;


public class LanzadorLogistica {
    public static void main(String[] args) {
        // C-1 empieza con Paco y hace 100km
        new Thread(new ClienteCamion("C-1", "Paco", 100)).start();
        
        // C-2 empieza con Ana y hace 200km
        new Thread(new ClienteCamion("C-2", "Ana", 200)).start();
        
        // C-1 ahora lo lleva Luis y hace 50km más
        new Thread(new ClienteCamion("C-1", "Luis", 50)).start();
    }
}
