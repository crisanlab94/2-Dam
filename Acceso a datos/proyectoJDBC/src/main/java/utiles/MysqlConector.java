package utiles;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import excepciones.MiExcepcion;




public class MysqlConector {
	private Connection connect;
	private String url;
	private String user;
	private String clave;

	public MysqlConector() throws MiExcepcion {
		try {
			Properties properties = new Properties();
			FileInputStream fs = new FileInputStream("src/main/resources/database.properties");
			properties.load(fs);

			this.url = properties.getProperty("url");
			this.user = properties.getProperty("user");
			this.clave = properties.getProperty("clave");
			
			this.connect = DriverManager.getConnection(this.url, this.user, this.clave);
			
		} catch (IOException e) {
			throw new MiExcepcion("Error al conectar a la base de datos" + e.getMessage());
		} catch (SQLException e) {
			throw new MiExcepcion("Error al conectar a la base de datos" + e.getMessage());
		}
	}

	public Connection getConnect() throws MiExcepcion {
        try {
            // Si está nula o cerrada, la reabrimos
            if (connect == null || connect.isClosed()) {
                 this.connect = DriverManager.getConnection(this.url, this.user, this.clave);
            }
            return connect;
        } catch (SQLException e) {
            throw new MiExcepcion("Error al obtener o reabrir la conexión: " + e.getMessage());
        }
    }

	public void release() {
		try {
			System.out.print("--- CERRANDO CONEXION ---");
			if (this.connect != null)
				this.connect.close();
			this.connect = null;
			

		} catch (SQLException e) {
			System.err.println("No se ha podido cerrar la conexion con la BD");
			e.printStackTrace();
		}
	}

}
