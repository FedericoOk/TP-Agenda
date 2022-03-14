package persistencia.dao.mysql;

import java.util.List;

import dto.PaisDTO;
import persistencia.dao.interfaz.PaisDAO;

public class PaisDAOJPA extends DAOJPA<PaisDTO> implements PaisDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<PaisDTO> readAll() {
        entityManager.getTransaction().begin();
        List<PaisDTO> result = entityManager.createQuery("from " + entityClass.getSimpleName() + " p order by nombre").getResultList();
        entityManager.getTransaction().commit();
        return result;
    }
    
}
