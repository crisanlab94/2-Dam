package repaso7;

public class GestionaExcavacion {
    public static void main(String[] args) {
        Yacimiento yacimiento = new Yacimiento();

        // 10 arqueólogos trabajando simultáneamente.
        for (int i = 1; i <= 10; i++) {
            new Thread(new Arqueologo("Arqueólogo-" + i, yacimiento)).start();
        }
    }
}