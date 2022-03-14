package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContacto;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersonaInsert;
import presentacion.vista.VentanaPersonaUpdate;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.VentanaUbicaciones;
import presentacion.vista.Vista;

public class Controlador implements ActionListener
{
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<PaisDTO> paisesEnLista;
	private List<ProvinciaDTO> provinciasEnLista;
	private List<LocalidadDTO> localidadesEnLista;
	private List<TipoContacto> tiposContactoEnLista;
	private VentanaPersonaInsert ventanaPersonaInsert;
	private VentanaPersonaUpdate ventanaPersonaUpdate;
	private VentanaUbicaciones ventanaUbicaciones;
	private VentanaTipoContacto ventanaTipoContacto;
	private Agenda agenda;
	
	public Controlador(Vista vista, Agenda agenda)
	{
		// Vista mapping
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
		this.vista.getBtnEditar().addActionListener(a->ventanaEditarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
		this.vista.getMenuItemUbicaciones().addActionListener(e->ventanaUbicaciones(e));
		this.vista.getMenuItemTipoContacto().addActionListener(m->ventanaTipoContacto(m));

		// Ventana Persona mapping
		this.ventanaPersonaInsert = VentanaPersonaInsert.getInstance();
		this.ventanaPersonaUpdate = VentanaPersonaUpdate.getInstance();
		this.ventanaPersonaInsert.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
		this.ventanaPersonaUpdate.getBtnEditarPersona().addActionListener(p->editarPersona(p));
		this.agenda = agenda;

		// Ventana Ubicaciones mapping
		this.ventanaUbicaciones = VentanaUbicaciones.getInstance();
		this.ventanaUbicaciones.getBtnAgregarPais().addActionListener(p->guardarPais(p));
		this.ventanaUbicaciones.getBtnEditarPais().addActionListener(p->editarPais(p));
		this.ventanaUbicaciones.getBtnEliminarPais().addActionListener(p->borrarPais(p));
		this.ventanaUbicaciones.getBtnAgregarProvincia().addActionListener(p->guardarProvincia(p));
		this.ventanaUbicaciones.getBtnEditarProvincia().addActionListener(p->editarProvincia(p));
		this.ventanaUbicaciones.getBtnEliminarProvincia().addActionListener(p->borrarProvincia(p));
		this.ventanaUbicaciones.getBtnAgregarLocalidad().addActionListener(l->guardarLocalidad(l));
		this.ventanaUbicaciones.getBtnEditarLocalidad().addActionListener(l->editarLocalidad(l));
		this.ventanaUbicaciones.getBtnEliminarLocalidad().addActionListener(l->borrarLocalidad(l));

		// Ventana Tipo Contacto
		this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
		this.ventanaTipoContacto.getBtnAgregar().addActionListener(t -> guardarTipoContacto(t));
		this.ventanaTipoContacto.getBtnEditar().addActionListener(t -> editarTipoContacto(t));
		this.ventanaTipoContacto.getBtnEliminar().addActionListener(t -> eliminarTipoContacto(t));
	}
	
	private void ventanaAgregarPersona(ActionEvent a) {
		this.refrescarListaTipoContactoEnVentanaPersonaInsert();
		this.refrescarListaLocalidadesEnVentanaPersonaInsert();
		this.ventanaPersonaInsert.mostrarVentana();
	}
	
	private void ventanaEditarPersona(ActionEvent a) {
		PersonaDTO personaAEditar = obtenerPersonaSeleccionada();
		this.refrescarListaTipoContactoEnVentanaPersonaUpdate();
		this.refrescarListaLocalidadesEnVentanaPersonaUpdate();
		this.ventanaPersonaUpdate.mostrarVentana(personaAEditar);
	}

	private void ventanaUbicaciones(ActionEvent e) {
		this.refrescarListaPaises();
		this.ventanaUbicaciones.mostrarVentana();
	}

	private void ventanaTipoContacto(ActionEvent m) {
		this.refrescarListaTipoContacto();
		this.ventanaTipoContacto.mostrarVentana();
	}
	
	private void guardarPersona(ActionEvent p) {
		String nombre = this.ventanaPersonaInsert.getTxtNombre().getText();
		String tel = this.ventanaPersonaInsert.getTxtTelefono().getText();
		String email = this.ventanaPersonaInsert.getTxtEmail().getText();
		Date nacimiento = this.ventanaPersonaInsert.getDateChooser().getDate();
		TipoContacto tipoContacto = (TipoContacto) this.ventanaPersonaInsert.getComboTipoContacto().getSelectedItem();
		String calle = this.ventanaPersonaInsert.getTxtCalle().getText();
		String altura = this.ventanaPersonaInsert.getTxtAltura().getText();
		String piso = this.ventanaPersonaInsert.getTxtPiso().getText();
		String depto = this.ventanaPersonaInsert.getTxtDepto().getText();
		LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersonaInsert.getJComboLocalidad().getSelectedItem();
		DomicilioDTO domicilio = new DomicilioDTO(calle, altura, piso, depto, localidad);
		PersonaDTO nuevaPersona = new PersonaDTO(nombre, tel, email, nacimiento, tipoContacto, domicilio);
		this.agenda.agregarPersona(nuevaPersona);
		this.refrescarTabla();
		this.ventanaPersonaInsert.cerrar();
	}

	private void editarPersona(ActionEvent p) {
		String nombre = this.ventanaPersonaUpdate.getTxtNombre().getText();
		String tel = this.ventanaPersonaUpdate.getTxtTelefono().getText();
		String email = this.ventanaPersonaUpdate.getTxtEmail().getText();
		Date nacimiento = this.ventanaPersonaUpdate.getDateChooser().getDate();
		String calle = this.ventanaPersonaUpdate.getTxtCalle().getText();
		String altura = this.ventanaPersonaUpdate.getTxtAltura().getText();
		String piso = this.ventanaPersonaUpdate.getTxtPiso().getText();
		String depto = this.ventanaPersonaUpdate.getTxtDepto().getText();
		TipoContacto tipoContacto = (TipoContacto) this.ventanaPersonaUpdate.getComboTipoContacto().getSelectedItem();
		LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersonaUpdate.getJComboLocalidad().getSelectedItem();
		PersonaDTO persona = obtenerPersonaSeleccionada();
		persona.setNombre(nombre);
		persona.setTelefono(tel);
		persona.setEmail(email);
		persona.setNacimiento(nacimiento);
		persona.setTipoContacto(tipoContacto);
		persona.getDomicilio().setCalle(calle);
		persona.getDomicilio().setAltura(altura);
		persona.getDomicilio().setPiso(piso);
		persona.getDomicilio().setDepto(depto);
		persona.getDomicilio().setLocalidad(localidad);
		this.agenda.actualizarPersona(persona);
		this.refrescarTabla();
		this.ventanaPersonaUpdate.cerrar();
	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();	
	}

	public void borrarPersona(ActionEvent s)
	{
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}
		
		this.refrescarTabla();
	}

	public PersonaDTO obtenerPersonaSeleccionada() {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		return this.personasEnTabla.get(filasSeleccionadas[0]);
	}

	private void guardarPais(ActionEvent p) {
		String nombrePais = this.ventanaUbicaciones.getTextNombrePais().getText();
		PaisDTO pais = new PaisDTO(nombrePais);
		this.agenda.agregarPais(pais);
		this.refrescarListaPaises();
	}

	private void editarPais(ActionEvent p) {
		String nuevoNombre = this.ventanaUbicaciones.getTextNombrePais().getText();
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		pais.setNombre(nuevoNombre);
		this.agenda.actualizarPais(pais);
		this.refrescarListaPaises();
	}

	private void borrarPais(ActionEvent p) {
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		this.agenda.borrarPais(pais);
		this.refrescarListaPaises();
	}

	private void guardarProvincia(ActionEvent p) {
		String nombreProvincia = this.ventanaUbicaciones.getTextNombreProvincia().getText();
		ProvinciaDTO provincia = new ProvinciaDTO(nombreProvincia);
		this.agenda.agregarProvincia(provincia);
		this.refrescarListaProvincias();
	}

	private void editarProvincia(ActionEvent p) {
		String nuevoNombre = this.ventanaUbicaciones.getTextNombreProvincia().getText();
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		provincia.setNombre(nuevoNombre);
		this.agenda.actualizarProvincia(provincia);
		this.refrescarListaProvincias();
	}

	private void borrarProvincia(ActionEvent p) {
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		this.agenda.borrarProvincia(provincia);
		this.refrescarListaProvincias();
	}

	private void guardarLocalidad(ActionEvent p) {
		String nombreLocalidad = this.ventanaUbicaciones.getTextNombreLocalidad().getText();
		LocalidadDTO localidad = new LocalidadDTO(nombreLocalidad);
		this.agenda.agregarLocalidad(localidad);
		this.refrescarListaLocalidades();
	}

	private void editarLocalidad(ActionEvent p) {
		String nuevoNombre = this.ventanaUbicaciones.getTextNombreLocalidad().getText();
		LocalidadDTO localidad = this.ventanaUbicaciones.getListaLocalidades().getSelectedValue();
		localidad.setNombre(nuevoNombre);
		this.agenda.actualizarLocalidad(localidad);
		this.refrescarListaLocalidades();
	}

	private void borrarLocalidad(ActionEvent p) {
		LocalidadDTO localidad = this.ventanaUbicaciones.getListaLocalidades().getSelectedValue();
		this.agenda.borrarLocalidad(localidad);
		this.refrescarListaLocalidades();
	}

	private void guardarTipoContacto(ActionEvent t) {
		String description = this.ventanaTipoContacto.getTxtDescription().getText();
		TipoContacto tipoContacto = new TipoContacto(description);
		this.agenda.agregarTipoContacto(tipoContacto);
		this.refrescarListaTipoContacto();
	}

	private void editarTipoContacto(ActionEvent t) {
		String description = this.ventanaTipoContacto.getTxtDescription().getText();
		TipoContacto tipoContacto = this.ventanaTipoContacto.getListaTipoContacto().getSelectedValue();
		tipoContacto.setDescription(description);
		this.agenda.actualizarTipoContacto(tipoContacto);
		this.refrescarListaTipoContacto();
	}

	private void eliminarTipoContacto(ActionEvent t) {
		TipoContacto tipoContactoSeleccionado = this.ventanaTipoContacto.getListaTipoContacto().getSelectedValue();

		if (tipoContactoSeleccionado == null)
			return;

		if (!this.personasEnTabla.stream().anyMatch(p -> p.getTipoContacto().equals(tipoContactoSeleccionado)))
			this.agenda.borrarTipoContacto(tipoContactoSeleccionado);
		else
			JOptionPane.showMessageDialog(this.ventanaTipoContacto, String.format("No se puede eliminar la entidad '%s' porque al menos una persona pertenece a este Tipo de Contacto.", tipoContactoSeleccionado.getDescription()));

		this.refrescarListaTipoContacto();
	}

	private void refrescarListaTipoContacto() {
		this.tiposContactoEnLista = agenda.obtenerTiposContacto();
		this.ventanaTipoContacto.llenarLista(this.tiposContactoEnLista);
	}

	private void refrescarListaTipoContactoEnVentanaPersonaInsert() {
		this.tiposContactoEnLista = agenda.obtenerTiposContacto();
		this.ventanaPersonaInsert.llenarComboTipoContacto(this.tiposContactoEnLista);
	}

	private void refrescarListaLocalidadesEnVentanaPersonaInsert() {
		this.localidadesEnLista = agenda.obtenerLocalidades();
		this.ventanaPersonaInsert.llenarComboLocalidades(this.localidadesEnLista);
	}

	private void refrescarListaTipoContactoEnVentanaPersonaUpdate() {
		this.tiposContactoEnLista = agenda.obtenerTiposContacto();
		this.ventanaPersonaUpdate.llenarComboTipoContacto(this.tiposContactoEnLista);
	}

	private void refrescarListaLocalidadesEnVentanaPersonaUpdate() {
		this.localidadesEnLista = agenda.obtenerLocalidades();
		this.ventanaPersonaUpdate.llenarComboLocalidades(localidadesEnLista);
	}
	
	private void refrescarListaPaises() {
		this.paisesEnLista = agenda.obtenerPaises();
		this.ventanaUbicaciones.llenarListaPais(paisesEnLista);
	}

	private void refrescarListaProvincias() {
		this.provinciasEnLista = agenda.obtenerProvincias();
		this.ventanaUbicaciones.llenarListaProvincias(provinciasEnLista);
	}

	private void refrescarListaLocalidades() {
		this.localidadesEnLista = agenda.obtenerLocalidades();
		this.ventanaUbicaciones.llenarListaLocalidades(localidadesEnLista);
	}
	
	public void inicializar()
	{
		this.refrescarTabla();
		this.refrescarListaPaises();
		this.refrescarListaProvincias();
		this.refrescarListaLocalidades();
		this.refrescarListaTipoContacto();
		this.vista.show();
	}
	
	private void refrescarTabla()
	{
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) { }
		
}
