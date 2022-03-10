package persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagers {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ar.edu.ungs");

    private EntityManagers() {
    }

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
    
}
