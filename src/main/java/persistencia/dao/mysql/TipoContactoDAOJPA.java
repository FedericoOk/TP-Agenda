package persistencia.dao.mysql;

import java.util.List;

import dto.TipoContacto;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOJPA extends DAOJPA<TipoContacto> implements TipoContactoDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<TipoContacto> readAll() {
        entityManager.getTransaction().begin();
        List<TipoContacto> result = entityManager.createQuery("from " + entityClass.getSimpleName() + " order by nombre").getResultList();
        entityManager.getTransaction().commit();
        return result;
    }
    
}
