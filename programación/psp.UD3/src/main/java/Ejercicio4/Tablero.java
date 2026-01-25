package Ejercicio4;

public class Tablero {
    private String[][] matriz = new String[3][4];
    private boolean[][] reclamados = new boolean[3][4]; // Para saber si ya se entregó
    private int premiosRestantes = 4;

    public Tablero() {
        matriz[0][0] = "CRUCERO";
        matriz[1][2] = "ENTRADAS";
        matriz[2][0] = "MASAJE";
        matriz[2][3] = "1000€";
    }

    public synchronized int getPremiosRestantes() {
        return premiosRestantes;
    }

    public synchronized String intentarCapturar(int f, int c) {
        if (f < 1 || f > 3 || c < 1 || c > 4) return "ERROR";
        
        String premio = matriz[f-1][c-1];

        if (premio == null) {
            return "VACIO";
        } else if (reclamados[f-1][c-1]) {
            // Si ya está marcado como reclamado, devolvemos un código especial + el nombre
            return "REPETIDO:" + premio; 
        } else {
            // ¡Premio nuevo! Lo marcamos
            reclamados[f-1][c-1] = true;
            premiosRestantes--;
            return premio;
        }
    }
}