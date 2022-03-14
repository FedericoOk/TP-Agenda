package persistencia.dao.mysql;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOJPA extends DAOJPA<LocalidadDTO> implements LocalidadDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<LocalidadDTO> readAll() {
        entityManager.getTransaction().begin();
        List<LocalidadDTO> result = entityManager.createQuery("from " + entityClass.getSimpleName() + " l order by nombre").getResultList();
        entityManager.getTransaction().commit();
        return result;
    }
    
}
