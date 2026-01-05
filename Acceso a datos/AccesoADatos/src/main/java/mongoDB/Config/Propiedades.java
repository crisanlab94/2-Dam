package mongoDB.Config;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Propiedades {
	private final static String rutaResources = "src\\main\\resources\\";
	private static final Logger logger = LogManager.getLogger(Propiedades.class);
	private Properties props = new Properties();
	public Propiedades(String nombreFichero) throws IOException {
	   props.load(new FileInputStream(rutaResources+nombreFichero));
	}
	
		public String get(String key) {
			return props.getProperty(key);
		}
	}
	


