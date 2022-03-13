package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dto.PersonaDTO;
import dto.TipoContacto;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersonaInsert;
import presentacion.vista.VentanaPersonaUpdate;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private List<TipoContacto> tiposContactoEnLista;
		private VentanaPersonaInsert ventanaPersonaInsert;
		private VentanaPersonaUpdate ventanaPersonaUpdate;
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
			this.vista.getMenuItemTipoContacto().addActionListener(m->ventanaTipoContacto(m));

			// Ventana Persona mapping
			this.ventanaPersonaInsert = VentanaPersonaInsert.getInstance();
			this.ventanaPersonaUpdate = VentanaPersonaUpdate.getInstance();
			this.ventanaPersonaInsert.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersonaUpdate.getBtnEditarPersona().addActionListener(p->editarPersona(p));
			this.agenda = agenda;

			// Ventana Tipo Contacto
			this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
			this.ventanaTipoContacto.getBtnAgregar().addActionListener(t -> guardarTipoContacto(t));
			this.ventanaTipoContacto.getBtnEditar().addActionListener(t -> editarTipoContacto(t));
			this.ventanaTipoContacto.getBtnEliminar().addActionListener(t -> eliminarTipoContacto(t));
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.refrescarListaTipoContactoEnVentanaPersonaInsert();
			this.ventanaPersonaInsert.mostrarVentana();
		}
		
		private void ventanaEditarPersona(ActionEvent a) {
			PersonaDTO personaAEditar = obtenerPersonaSeleccionada();
			this.refrescarListaTipoContactoEnVentanaPersonaUpdate();
			this.ventanaPersonaUpdate.mostrarVentana(personaAEditar);
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
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, email, nacimiento, tipoContacto);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersonaInsert.cerrar();
		}

		private void editarPersona(ActionEvent p) {
			String nombre = this.ventanaPersonaUpdate.getTxtNombre().getText();
			String tel = this.ventanaPersonaUpdate.getTxtTelefono().getText();
			String email = this.ventanaPersonaUpdate.getTxtEmail().getText();
			Date nacimiento = this.ventanaPersonaUpdate.getDateChooser().getDate();
			TipoContacto tipoContacto = (TipoContacto) this.ventanaPersonaUpdate.getComboTipoContacto().getSelectedItem();
			PersonaDTO persona = obtenerPersonaSeleccionada();
			persona.setNombre(nombre);
			persona.setTelefono(tel);
			persona.setEmail(email);
			persona.setNacimiento(nacimiento);
			persona.setTipoContacto(tipoContacto);
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

		private void refrescarListaTipoContactoEnVentanaPersonaUpdate() {
			this.tiposContactoEnLista = agenda.obtenerTiposContacto();
			this.ventanaPersonaUpdate.llenarComboTipoContacto(this.tiposContactoEnLista);
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
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
