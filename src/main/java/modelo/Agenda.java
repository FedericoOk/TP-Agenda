package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContacto;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;


public class Agenda 
{
	private PersonaDAO persona;
	private DomicilioDAO domicilioDAO;
	private PaisDAO paisDAO;
	private ProvinciaDAO provinciaDAO;
	private LocalidadDAO localidadDAO;
	private TipoContactoDAO tipoContactoDAO;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoContactoDAO = metodo_persistencia.createTipoContactoDAO();
		this.paisDAO = metodo_persistencia.createPaisDAO();
		this.provinciaDAO = metodo_persistencia.createProvinciaDAO();
		this.localidadDAO = metodo_persistencia.createLocalidadDAO();
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

	public void agregarPais(PaisDTO pais) {
		this.paisDAO.insert(pais);
	}
	
	public void actualizarPais(PaisDTO pais) {
		this.paisDAO.update(pais);
	}

	public void borrarPais(PaisDTO pais) {
		this.paisDAO.delete(pais);
	}

	public List<PaisDTO> obtenerPaises() {
		return this.paisDAO.readAll();
	}

	public void agregarProvincia(ProvinciaDTO provincia) {
		this.provinciaDAO.insert(provincia);
	}
	
	public void actualizarProvincia(ProvinciaDTO provincia) {
		this.provinciaDAO.update(provincia);
	}

	public void borrarProvincia(ProvinciaDTO provincia) {
		this.provinciaDAO.delete(provincia);
	}

	public List<ProvinciaDTO> obtenerProvincias() {
		return this.provinciaDAO.readAll();
	}

	public void agregarLocalidad(LocalidadDTO localidad) {
		this.localidadDAO.insert(localidad);
	}
	
	public void actualizarLocalidad(LocalidadDTO localidad) {
		this.localidadDAO.update(localidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad) {
		this.localidadDAO.delete(localidad);
	}

	public List<LocalidadDTO> obtenerLocalidades() {
		return this.localidadDAO.readAll();
	}
	
}
