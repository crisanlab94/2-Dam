package Ejercicio4;

public class Tablero {
    private String[][] matriz = new String[3][4];
    private boolean[][] reclamados = new boolean[3][4]; // Para saber si ya se entregó
    private int premiosRestantes;

    public Tablero(int premiosRestantes) {
        matriz[0][0] = "CRUCERO";
        matriz[1][2] = "ENTRADAS";
        matriz[2][0] = "MASAJE";
        matriz[2][3] = "1000€";
        this.premiosRestantes=4;
    }
    
    public Tablero() {
        matriz[0][0] = "CRUCERO";
        matriz[1][2] = "ENTRADAS";
        matriz[2][0] = "MASAJE";
        matriz[2][3] = "1000€";
        this.premiosRestantes=4;
    }
    

    public synchronized int getPremiosRestantes() {
        return this.
        		premiosRestantes;
    }

    public synchronized String intentarCapturar(int f, int c) {
        String resultado = "ERROR";

        // 1. Verificamos límites primero
        if (f >= 1 && f <= 3 && c >= 1 && c <= 4) {
            String premio = matriz[f-1][c-1];

            // 2. Evaluamos el estado de la celda
            if (premio == null) {
                resultado = "VACIO";
            } else if (reclamados[f-1][c-1]) {
                resultado = "REPETIDO:" + premio;
            } else {
                // 3. Captura exitosa
                reclamados[f-1][c-1] = true;
                premiosRestantes--;
                resultado = premio;
            }
        }

        return resultado;
    }
    }
