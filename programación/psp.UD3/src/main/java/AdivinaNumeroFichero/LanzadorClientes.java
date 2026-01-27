package AdivinaNumeroFichero;

public class LanzadorClientes {

    public static void main(String[] args) throws Exception {

        Thread par = new Thread(new ClientePar());
        Thread impar = new Thread(new ClienteImpar());

        par.start();
        impar.start();
    }
}
