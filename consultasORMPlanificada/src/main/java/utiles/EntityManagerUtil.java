package utiles;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

	public static EntityManager getEntityManager(String nombreBaseDatos) {
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory(nombreBaseDatos);
		EntityManager manager = factoria.createEntityManager();
		return manager;
	}

}
