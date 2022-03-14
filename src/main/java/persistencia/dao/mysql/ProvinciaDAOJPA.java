package persistencia.dao.mysql;

import java.util.List;

import dto.ProvinciaDTO;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOJPA extends DAOJPA<ProvinciaDTO> implements ProvinciaDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<ProvinciaDTO> readAll() {
        entityManager.getTransaction().begin();
        List<ProvinciaDTO> result = entityManager.createQuery("from " + entityClass.getSimpleName() + " p order by nombre").getResultList();
        entityManager.getTransaction().commit();
        return result;
    }
    
}
