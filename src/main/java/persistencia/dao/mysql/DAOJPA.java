package persistencia.dao.mysql;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import persistencia.conexion.EntityManagers;
import persistencia.dao.interfaz.DAO;

public class DAOJPA<T> implements DAO<T> {

    protected EntityManager entityManager;

    protected Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public DAOJPA() {
        this.entityManager = EntityManagers.createEntityManager();

        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
    }

    @Override
    public boolean insert(T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T update(T t) {
        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
        return t;
    }

    @Override
    public boolean delete(T t) {
        try {
            entityManager.getTransaction().begin();
            t = entityManager.merge(t);
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> readAll() {
        entityManager.getTransaction().begin();
        List<T> result = entityManager.createQuery("from " + entityClass.getSimpleName()).getResultList();
        entityManager.getTransaction().commit();
        return result;
    }
    
}
