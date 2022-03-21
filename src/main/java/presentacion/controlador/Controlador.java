package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

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

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<PaisDTO> paisesEnLista;
	private List<TipoContacto> tiposContactoEnLista;
	private VentanaPersonaInsert ventanaPersonaInsert;
	private VentanaPersonaUpdate ventanaPersonaUpdate;
	private VentanaUbicaciones ventanaUbicaciones;
	private VentanaTipoContacto ventanaTipoContacto;
	private Agenda agenda;
	Pattern ptremail = Pattern.compile(
			"(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");

	public Controlador(Vista vista, Agenda agenda) {
		// Vista mapping
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnEditar().addActionListener(a -> ventanaEditarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte1().addActionListener(r -> mostrarReporte(r, "ReporteAgenda1.jasper"));
		this.vista.getBtnReporte2().addActionListener(r -> mostrarReporte(r, "ReporteAgenda2.jasper"));
		this.vista.getMenuItemUbicaciones().addActionListener(e -> ventanaUbicaciones(e));
		this.vista.getMenuItemTipoContacto().addActionListener(m -> ventanaTipoContacto(m));

		// Ventana Persona mapping
		this.ventanaPersonaInsert = VentanaPersonaInsert.getInstance();
		this.ventanaPersonaUpdate = VentanaPersonaUpdate.getInstance();
		this.ventanaPersonaInsert.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
		this.ventanaPersonaUpdate.getBtnEditarPersona().addActionListener(p->editarPersona(p));
		this.ventanaPersonaInsert.getJComboPais().addActionListener(l -> this.filtrarProvincias(l));
		this.ventanaPersonaInsert.getJComboPais().addActionListener(l -> this.limpiarFormularioProvinciaVentanaPersonaInsert(l)); // TODO: usar una sola ventana
		this.ventanaPersonaInsert.getJComboPais().addActionListener(l -> this.limpiarFormularioLocalidadVentanaPersonaInsert(l));
		this.ventanaPersonaUpdate.getJComboPais().addActionListener(l -> this.filtrarProvincias(l));
		this.ventanaPersonaUpdate.getJComboPais().addActionListener(l -> this.limpiarFormularioProvinciaVentanaPersonaUpdate(l));
		this.ventanaPersonaUpdate.getJComboPais().addActionListener(l -> this.limpiarFormularioLocalidadVentanaPersonaUpdate(l));
		this.ventanaPersonaInsert.getJComboProvincia().addActionListener(l -> this.filtrarLocalidades(l));
		this.ventanaPersonaInsert.getJComboProvincia().addActionListener(l -> this.limpiarFormularioLocalidadVentanaPersonaInsert(l));
		this.ventanaPersonaUpdate.getJComboProvincia().addActionListener(l -> this.filtrarLocalidades(l));
		this.ventanaPersonaUpdate.getJComboProvincia().addActionListener(l -> this.limpiarFormularioLocalidadVentanaPersonaUpdate(l));
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
		this.ventanaUbicaciones.getListaPaises().addListSelectionListener(l -> this.actualizarFormularioPais(l));
		this.ventanaUbicaciones.getListaPaises().addListSelectionListener(l -> this.filtrarProvincias(l));
		this.ventanaUbicaciones.getListaPaises().addListSelectionListener(l -> this.limpiarFormularioLocalidad(l));
		this.ventanaUbicaciones.getListaProvincias().addListSelectionListener(l -> this.actualizarFormularioProvincia(l));
		this.ventanaUbicaciones.getListaProvincias().addListSelectionListener(l -> this.filtrarLocalidades(l));
		this.ventanaUbicaciones.getListaLocalidades().addListSelectionListener(l -> this.actualizarFormularioLocalidad(l));

		// Ventana Tipo Contacto
		this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
		this.ventanaTipoContacto.getBtnAgregar().addActionListener(t -> guardarTipoContacto(t));
		this.ventanaTipoContacto.getBtnEditar().addActionListener(t -> editarTipoContacto(t));
		this.ventanaTipoContacto.getBtnEliminar().addActionListener(t -> eliminarTipoContacto(t));
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.refrescarListaTipoContactoEnVentanaPersonaInsert();
		this.ventanaPersonaInsert.llenarComboPaises(this.paisesEnLista);
		this.ventanaPersonaInsert.mostrarVentana();
	}

	private void ventanaEditarPersona(ActionEvent a) {
		PersonaDTO personaAEditar = obtenerPersonaSeleccionada();
		this.refrescarListaTipoContactoEnVentanaPersonaUpdate();
		this.ventanaPersonaUpdate.llenarComboPaises(this.paisesEnLista);
		this.ventanaPersonaUpdate.mostrarVentana(personaAEditar);
	}

	private void ventanaUbicaciones(ActionEvent e) {
		this.ventanaUbicaciones.llenarListaPais(this.paisesEnLista);
		this.ventanaUbicaciones.mostrarVentana();
	}

	private void ventanaTipoContacto(ActionEvent m) {
		this.refrescarListaTipoContacto();
		this.ventanaTipoContacto.mostrarVentana();
	}

	private void guardarPersona(ActionEvent p) {
		// creamos la persona
		String nombre = this.ventanaPersonaInsert.getTxtNombre().getText();
		String tel = this.ventanaPersonaInsert.getTxtTelefono().getText();
		String email = this.ventanaPersonaInsert.getTxtEmail().getText();
		Date nacimiento = this.ventanaPersonaInsert.getDateChooser().getDate();
		TipoContacto tipoContacto = (TipoContacto) this.ventanaPersonaInsert.getComboTipoContacto().getSelectedItem();
		String calle = this.ventanaPersonaInsert.getTxtCalle().getText();
		String altura = this.ventanaPersonaInsert.getTxtAltura().getText();
		String piso = this.ventanaPersonaInsert.getTxtPiso().getText();
		String depto = this.ventanaPersonaInsert.getTxtDepto().getText();
		String plataformaAlmacenamiento = this.ventanaPersonaInsert.getTxtPlataformaAlmacenamiento().getText();
		LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersonaInsert.getJComboLocalidad().getSelectedItem();
		DomicilioDTO domicilio = new DomicilioDTO(calle, altura, piso, depto, localidad);
		String mesNacimiento = (String) this.ventanaPersonaInsert.getComboMesNacimiento().getSelectedItem();

		PersonaDTO nuevaPersona = new PersonaDTO(nombre, tel, email, nacimiento, tipoContacto, domicilio, plataformaAlmacenamiento, mesNacimiento);
		if (nombre.isEmpty() || tel.equals("(___) ____-____") || email.isEmpty()) {
			JOptionPane.showMessageDialog(this.ventanaPersonaInsert,
					"El contacto debe tener al menos Nombre , Telefono y Email.");
		} else if (!ptremail.matcher(email).matches()) {
			JOptionPane.showMessageDialog(this.ventanaPersonaInsert, "Formato invalido de Email.");
		} else {
			// guardamos la persona en la agenda
			nuevaPersona = this.agenda.agregarPersona(nuevaPersona);

			// actualizamos la lista en memoria
			// actualizamos la lista en la ventana
			this.refrescarTabla();
			this.ventanaPersonaInsert.cerrar();
		}
	}

	private void editarPersona(ActionEvent p) {
		// actializamos la persona
		String nombre = this.ventanaPersonaUpdate.getTxtNombre().getText();
		String tel = this.ventanaPersonaUpdate.getTxtTelefono().getText();
		String email = this.ventanaPersonaUpdate.getTxtEmail().getText();
		Date nacimiento = this.ventanaPersonaUpdate.getDateChooser().getDate();
		String calle = this.ventanaPersonaUpdate.getTxtCalle().getText();
		String altura = this.ventanaPersonaUpdate.getTxtAltura().getText();
		String piso = this.ventanaPersonaUpdate.getTxtPiso().getText();
		String depto = this.ventanaPersonaUpdate.getTxtDepto().getText();
		String mesNacimiento = (String) this.ventanaPersonaUpdate.getComboMesNacimiento().getSelectedItem();
		String plataformaAlmacenamiento = this.ventanaPersonaUpdate.getTxtPlataformaAlmacenamiento().getText();
		TipoContacto tipoContacto = (TipoContacto) this.ventanaPersonaUpdate.getComboTipoContacto().getSelectedItem();
		LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersonaUpdate.getJComboLocalidad().getSelectedItem();
		PersonaDTO persona = obtenerPersonaSeleccionada();
		persona.setNombre(nombre);
		persona.setTelefono(tel);
		persona.setEmail(email);
		persona.setNacimiento(nacimiento);
		persona.setTipoContacto(tipoContacto);
		persona.setMesNacimiento(mesNacimiento);
		persona.setPlataformaAlmacenamiento(plataformaAlmacenamiento);
		persona.getDomicilio().setCalle(calle);
		persona.getDomicilio().setAltura(altura);
		persona.getDomicilio().setPiso(piso);
		persona.getDomicilio().setDepto(depto);
		persona.getDomicilio().setLocalidad(localidad);
		
		if (nombre.isEmpty() || tel.equals("(___) ____-____") || email.isEmpty()) {
			JOptionPane.showMessageDialog(this.ventanaPersonaInsert,
					"El contacto debe tener al menos Nombre , Telefono y Email.");
		} else if (!ptremail.matcher(email).matches()) {
			JOptionPane.showMessageDialog(this.ventanaPersonaInsert, "Formato invalido de Email.");
		} else {
			// actualizamos la persona en la agenda
			this.agenda.actualizarPersona(persona);

			// actualizamos la lista en memoria
			// actualizamos la lista en la ventana
			this.refrescarTabla();
			this.ventanaPersonaUpdate.cerrar();
		}
	}

	private void mostrarReporte(ActionEvent r, String reportepath) {
		String ordenamiento = (String) this.vista.getComboSort().getSelectedItem();
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas(), reportepath, ordenamiento);
		reporte.mostrar();
	}

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}
		
		this.refrescarTabla(); // TODO: aliminar de la lista sin volver a consultar a la base de datos?
	}

	public PersonaDTO obtenerPersonaSeleccionada() {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		return this.personasEnTabla.get(filasSeleccionadas[0]);
	}

	private void guardarPais(ActionEvent p) {
		// creamos el nuevo país
		String nombrePais = this.ventanaUbicaciones.getTextNombrePais().getText();
		PaisDTO pais = new PaisDTO(nombrePais);

		// guardamos el país en la agenda
		pais = this.agenda.agregarPais(pais);

		// actualizamos la lista en memoria
		this.paisesEnLista = agenda.obtenerPaises(); // así ya viene ordenado por nombre

		// actualizamos la lista en la ventana
		int idx = this.paisesEnLista.indexOf(pais);
		this.ventanaUbicaciones.getModeloPaises().add(idx, pais);

		// TODO: ver si limpio la selección de las listas de provincias y localidades acá o en la ventana
		// O si el Listener de la lista me toma la actualización y resetea las otras seleccionadas
	}

	private void editarPais(ActionEvent p) {
		// actualizamos el país
		String nuevoNombre = this.ventanaUbicaciones.getTextNombrePais().getText();
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		pais.setNombre(nuevoNombre);

		// actualizamos el país en la agenda
		pais = this.agenda.actualizarPais(pais);

		// actualizamos la lista en memoria
		this.paisesEnLista = agenda.obtenerPaises(); // TODO: ver si esto es necesario porque si se actualiza arriba y todos usan el mismo no debería ser necesario

		// actualizamos la lista en la ventana
		int idx = this.paisesEnLista.indexOf(pais);
		this.ventanaUbicaciones.getModeloPaises().set(idx, pais);
	}

	private void borrarPais(ActionEvent p) {
		// obtenemos el país seleccionado
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();

		// eliminamos el país de la agenda
		this.agenda.borrarPais(pais);

		// actualizamos la lista en memoria
		this.paisesEnLista = agenda.obtenerPaises();

		// actualizamos la lista en la ventana
		this.ventanaUbicaciones.getModeloPaises().removeElement(pais);
	}

	private void guardarProvincia(ActionEvent p) {
		// creamos la nueva provincia
		String nombreProvincia = this.ventanaUbicaciones.getTextNombreProvincia().getText();
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		ProvinciaDTO provincia = new ProvinciaDTO(nombreProvincia, pais);

		// guardamos la provincia en la agenda
		provincia = this.agenda.agregarProvincia(provincia);

		// actualizamos la lista en memoria

		// actualizamos la lista en la ventana
		int idx = pais.getProvincias().indexOf(provincia);
		this.ventanaUbicaciones.getModeloProvincias().add(idx, provincia);
	}

	private void editarProvincia(ActionEvent p) {
		// actualizamos la provincia
		String nuevoNombre = this.ventanaUbicaciones.getTextNombreProvincia().getText();
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		int oldIdx = pais.getProvincias().indexOf(provincia);
		provincia.setNombre(nuevoNombre);

		// actualizamos la provincia en la agenda
		provincia = this.agenda.actualizarProvincia(provincia);

		// actualizamos la lista en memoria
		pais.ordenarProvinciasPorNombre();
		
		// actualizamos la lista en la ventana
		int idx = pais.getProvincias().indexOf(provincia); // hay un bug acá.
		// this.ventanaUbicaciones.getModeloProvincias().remove(oldIdx);
		// this.ventanaUbicaciones.getModeloProvincias().set(idx, provincia);
		// this.ventanaUbicaciones.getModeloProvincias().setElementAt(provincia, idx);
		this.ventanaUbicaciones.getModeloProvincias().remove(oldIdx);
		this.ventanaUbicaciones.getModeloProvincias().add(idx, provincia);
	}

	private void borrarProvincia(ActionEvent p) {
		// obtenemos la provincia seleccionada
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();

		// eliminamos la provincia de la agenda
		this.agenda.borrarProvincia(provincia);

		// actualizamos la lista en memoria
		provincia.getPais().removeProvincia(provincia);

		// actualizamos la lista en la ventana
		this.ventanaUbicaciones.getModeloProvincias().removeElement(provincia);
		// this.refrescarListaProvincias();
	}

	private void guardarLocalidad(ActionEvent p) {
		// creamos la nueva localidad
		String nombreLocalidad = this.ventanaUbicaciones.getTextNombreLocalidad().getText();
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		LocalidadDTO localidad = new LocalidadDTO(nombreLocalidad, provincia);

		// guardamos la localidad en la agenda
		localidad = this.agenda.agregarLocalidad(localidad);

		// actualizamos la lista en memoria

		// actualizamos la lista en la ventana
		int idx = provincia.getLocalidades().indexOf(localidad);
		this.ventanaUbicaciones.getModeloLocalidades().add(idx, localidad);
		// this.refrescarListaLocalidades();
	}

	private void editarLocalidad(ActionEvent p) {
		// actualizamos la localidad
		String nuevoNombre = this.ventanaUbicaciones.getTextNombreLocalidad().getText();
		LocalidadDTO localidad = this.ventanaUbicaciones.getListaLocalidades().getSelectedValue();
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		int oldIdx = provincia.getLocalidades().indexOf(localidad);
		localidad.setNombre(nuevoNombre);

		// actualizamos la localidad en la agenda
		localidad = this.agenda.actualizarLocalidad(localidad);

		// actualizamos la lista en memoria
		provincia.ordenarLocalidadesPorNombre();

		// actualizamos la lista en la ventana
		int idx = provincia.getLocalidades().indexOf(localidad);
		this.ventanaUbicaciones.getModeloLocalidades().remove(oldIdx);
		this.ventanaUbicaciones.getModeloLocalidades().add(idx, localidad);
	}

	private void borrarLocalidad(ActionEvent p) {
		// obtenemos la localidad seleccionada
		LocalidadDTO localidad = this.ventanaUbicaciones.getListaLocalidades().getSelectedValue();

		// eliminamos la provincia de la agenda
		this.agenda.borrarLocalidad(localidad);

		// actualizamos la lista en memoria
		localidad.getProvincia().removeLocalidad(localidad);

		// actualizamos la lista en la ventana
		this.ventanaUbicaciones.getModeloLocalidades().removeElement(localidad);
	}

	private void guardarTipoContacto(ActionEvent t) {
		String nombre = this.ventanaTipoContacto.getTxtNombre().getText();
		TipoContacto tipoContacto = new TipoContacto(nombre);
		this.agenda.agregarTipoContacto(tipoContacto);
		this.refrescarListaTipoContacto();
	}

	private void editarTipoContacto(ActionEvent t) {
		String nombre = this.ventanaTipoContacto.getTxtNombre().getText();
		TipoContacto tipoContacto = this.ventanaTipoContacto.getListaTipoContacto().getSelectedValue();
		tipoContacto.setNombre(nombre);
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
			JOptionPane.showMessageDialog(this.ventanaTipoContacto, String.format(
					"No se puede eliminar la entidad '%s' porque al menos una persona pertenece a este Tipo de Contacto.",
					tipoContactoSeleccionado.getNombre()));

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

	private void refrescarListaTipoContactoEnVentanaPersonaUpdate() {
		this.tiposContactoEnLista = agenda.obtenerTiposContacto();
		this.ventanaPersonaUpdate.llenarComboTipoContacto(this.tiposContactoEnLista);
	}
	
	private void refrescarUbicaciones() {
		this.paisesEnLista = agenda.obtenerPaises();
	}

	private void actualizarFormularioPais(ListSelectionEvent l) {
		PaisDTO pais = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		String data = pais == null ? "" : pais.getNombre();
		this.ventanaUbicaciones.getTextNombrePais().setText(data);

		// TODO: ver dónde hacer esto
		// this.ventanaUbicaciones.getListaProvincias().clearSelection();
		// this.ventanaUbicaciones.getListaLocalidades().clearSelection();
	}

	private void actualizarFormularioProvincia(ListSelectionEvent l) {
		ProvinciaDTO provincia = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		String data = provincia == null ? "" : provincia.getNombre();
		this.ventanaUbicaciones.getTextNombreProvincia().setText(data);
	}

	private void actualizarFormularioLocalidad(ListSelectionEvent l) {
		LocalidadDTO localidad = this.ventanaUbicaciones.getListaLocalidades().getSelectedValue();
		String data = localidad == null ? "" : localidad.getNombre();
		this.ventanaUbicaciones.getTextNombreLocalidad().setText(data);
	}

	private void filtrarProvincias(ListSelectionEvent l) {
		PaisDTO paisSeleccionado = this.ventanaUbicaciones.getListaPaises().getSelectedValue();
		if (paisSeleccionado != null)
			this.ventanaUbicaciones.llenarListaProvincias(paisSeleccionado.getProvincias());
	}

	private void filtrarLocalidades(ListSelectionEvent l) {
		ProvinciaDTO provinciaSeleccionada = this.ventanaUbicaciones.getListaProvincias().getSelectedValue();
		if (provinciaSeleccionada != null)
			this.ventanaUbicaciones.llenarListaLocalidades(provinciaSeleccionada.getLocalidades());
	}

	private void limpiarFormularioLocalidad(ListSelectionEvent l) {
		this.ventanaUbicaciones.getModeloLocalidades().clear();
		this.ventanaUbicaciones.getTextNombreLocalidad().setText("");
	}

	private void filtrarProvincias(ActionEvent l) {
		PaisDTO paisSeleccionado = (PaisDTO) this.ventanaPersonaInsert.getJComboPais().getSelectedItem();
		if (paisSeleccionado != null)
			this.ventanaPersonaInsert.llenarComboProvincias(paisSeleccionado.getProvincias());
	}

	private void filtrarLocalidades(ActionEvent l) {
		ProvinciaDTO provinciaSeleccionada = (ProvinciaDTO) this.ventanaPersonaInsert.getJComboProvincia().getSelectedItem();
		if (provinciaSeleccionada != null)
			this.ventanaPersonaInsert.llenarComboLocalidades(provinciaSeleccionada.getLocalidades());
	}

	private void limpiarFormularioProvinciaVentanaPersonaInsert(ActionEvent l) {
		this.ventanaPersonaInsert.getJComboProvincia().setSelectedIndex(-1);
	}

	private void limpiarFormularioLocalidadVentanaPersonaInsert(ActionEvent l) {
		this.ventanaPersonaInsert.getJComboLocalidad().setSelectedIndex(-1);
	}

	private void limpiarFormularioProvinciaVentanaPersonaUpdate(ActionEvent l) {
		this.ventanaPersonaUpdate.getJComboProvincia().setSelectedIndex(-1);
	}

	private void limpiarFormularioLocalidadVentanaPersonaUpdate(ActionEvent l) {
		this.ventanaPersonaUpdate.getJComboLocalidad().setSelectedIndex(-1);
	}

	private void refrescarListaComboMesNacimiento() {
		this.ventanaPersonaInsert.llenarComboMesNacimiento();
		this.ventanaPersonaUpdate.llenarComboMesNacimiento();
	}

	public void inicializar() {
		this.refrescarTabla();
		this.refrescarListaTipoContacto();
		this.refrescarListaComboMesNacimiento();
		this.refrescarUbicaciones();
		this.vista.show();
	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
