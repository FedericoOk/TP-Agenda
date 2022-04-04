package persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.cfg.Configuration;

public class EntityManagers {

    private static EntityManagerFactory entityManagerFactory;
    private static final String UNIT_NAME = "ar.edu.ungs";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String HBM2DDL_AUTO = "update";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/agenda";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String SHOW_SQL = "true";

    private EntityManagers() {
        Configuration configuration = new Configuration();
        configuration.getProperties().setProperty("hibernate.dialect", DIALECT);
        configuration.getProperties().setProperty("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        configuration.getProperties().setProperty("javax.persistence.jdbc.driver", DRIVER);
        configuration.getProperties().setProperty("javax.persistence.jdbc.url", URL);
        configuration.getProperties().setProperty("javax.persistence.jdbc.user", USER);
        configuration.getProperties().setProperty("javax.persistence.jdbc.password", PASSWORD);
        configuration.getProperties().setProperty("hibernate.show_sql", SHOW_SQL);

        EntityManagers.entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME, configuration.getProperties());
    }

    public static EntityManager createEntityManager() {
        if (EntityManagers.entityManagerFactory == null)
            new EntityManagers();
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
    
}
