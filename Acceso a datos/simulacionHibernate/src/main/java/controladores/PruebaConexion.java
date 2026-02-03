package controladores;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebaConexion {
	public static void main(String[] args) throws Exception {
        Connection c = DriverManager.getConnection(
            "jdbc:mysql://localhost:3307/simulacionHibernate",
            "usuario2",
            "usuario"
        );
        System.out.println("Conectado OK");
        c.close();
    }
}



