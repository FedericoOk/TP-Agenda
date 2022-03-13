package modelo;

import java.util.List;
import dto.PersonaDTO;
import dto.TipoContacto;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;


public class Agenda 
{
	private PersonaDAO persona;
	private TipoContactoDAO tipoContactoDAO;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoContactoDAO = metodo_persistencia.createTipoContactoDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}

	public void actualizarPersona(PersonaDTO persona)
	{
		this.persona.update(persona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}

	public void agregarTipoContacto(TipoContacto tipoContacto) {
		this.tipoContactoDAO.insert(tipoContacto);
	}
	
	public void actualizarTipoContacto(TipoContacto tipoContacto) {
		this.tipoContactoDAO.update(tipoContacto);
	}

	public void borrarTipoContacto(TipoContacto tipoContacto) {
		this.tipoContactoDAO.delete(tipoContacto);
	}

	public List<TipoContacto> obtenerTiposContacto() {
		return this.tipoContactoDAO.readAll();
	}
	
}
