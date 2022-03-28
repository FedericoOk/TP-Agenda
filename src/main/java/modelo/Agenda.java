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

	// -- Personas --
	
	public PersonaDTO agregarPersona(PersonaDTO nuevaPersona)
	{
		return this.persona.insert(nuevaPersona);
	}

	public PersonaDTO actualizarPersona(PersonaDTO persona)
	{
		return this.persona.update(persona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}

	// -- Tipos de Contacto --

	public TipoContacto agregarTipoContacto(TipoContacto tipoContacto) {
		return this.tipoContactoDAO.insert(tipoContacto);
	}
	
	public TipoContacto actualizarTipoContacto(TipoContacto tipoContacto) {
		return this.tipoContactoDAO.update(tipoContacto);
	}

	public void borrarTipoContacto(TipoContacto tipoContacto) {
		this.tipoContactoDAO.delete(tipoContacto);
	}

	public List<TipoContacto> obtenerTiposContacto() {
		return this.tipoContactoDAO.readAll();
	}

	// -- Países --

	public PaisDTO agregarPais(PaisDTO pais) {
		return this.paisDAO.insert(pais);
	}
	
	public PaisDTO actualizarPais(PaisDTO pais) {
		return this.paisDAO.update(pais);
	}

	public void borrarPais(PaisDTO pais) {
		this.paisDAO.delete(pais);
	}

	public List<PaisDTO> obtenerPaises() {
		return this.paisDAO.readAll();
	}

	// -- Provincias --

	public ProvinciaDTO agregarProvincia(ProvinciaDTO provincia) {
		return this.provinciaDAO.insert(provincia);
	}
	
	public ProvinciaDTO actualizarProvincia(ProvinciaDTO provincia) {
		return this.provinciaDAO.update(provincia);
	}

	public void borrarProvincia(ProvinciaDTO provincia) {
		this.provinciaDAO.delete(provincia);
	}

	public List<ProvinciaDTO> obtenerProvincias() {
		return this.provinciaDAO.readAll();
	}

	// -- Localidades --

	public LocalidadDTO agregarLocalidad(LocalidadDTO localidad) {
		return this.localidadDAO.insert(localidad);
	}
	
	public LocalidadDTO actualizarLocalidad(LocalidadDTO localidad) {
		return this.localidadDAO.update(localidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad) {
		this.localidadDAO.delete(localidad);
	}

	public List<LocalidadDTO> obtenerLocalidades() {
		return this.localidadDAO.readAll();
	}
	
}
