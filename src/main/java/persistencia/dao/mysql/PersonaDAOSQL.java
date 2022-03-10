package persistencia.dao.mysql;

import java.util.List;

import javax.persistence.EntityManager;

import dto.PersonaDTO;
import persistencia.conexion.EntityManagers;
import persistencia.dao.interfaz.PersonaDAO;

public class PersonaDAOSQL implements PersonaDAO { // TODO: hacer DAO<T>

	private EntityManager entityManager;

	public PersonaDAOSQL() {
		this.entityManager = EntityManagers.createEntityManager();
	}
		
	public boolean insert(PersonaDTO persona)
	{
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(persona);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		try {
			entityManager.getTransaction().begin();
			persona_a_eliminar = entityManager.merge(persona_a_eliminar);
			entityManager.remove(persona_a_eliminar);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonaDTO> readAll()
	{
		entityManager.getTransaction().begin();
		List<PersonaDTO> result = entityManager.createQuery("SELECT p FROM PersonaDTO p").getResultList();
		entityManager.getTransaction().commit();
		return result;
	}

}
