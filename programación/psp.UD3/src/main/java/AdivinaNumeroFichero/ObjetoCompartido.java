package AdivinaNumeroFichero;

public class ObjetoCompartido {

    int numeroSecreto;
    boolean acertado;
    String ultimaRespuesta;

    public ObjetoCompartido() {
        numeroSecreto = (int)(Math.random() * 21);
        acertado = false;
        ultimaRespuesta = "";
        System.out.println("SERVIDOR: Número secreto generado = " + numeroSecreto);
    }

    public synchronized void comprobarNumero(int numero) {

        if (acertado) {
            ultimaRespuesta = "FIN";
        } else {
            if (numero == numeroSecreto) {
                acertado = true;
                ultimaRespuesta = "ACIERTO";
            } else if (numero < numeroSecreto) {
                ultimaRespuesta = "MAYOR";
            } else {
                ultimaRespuesta = "MENOR";
            }
        }
    }
}
