package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.PersonaDTO;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersonaInsert;
import presentacion.vista.VentanaPersonaUpdate;
import presentacion.vista.Vista;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private VentanaPersonaInsert ventanaPersonaInsert;
		private VentanaPersonaUpdate ventanaPersonaUpdate;
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			// Vista mapping
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnEditar().addActionListener(a->ventanaEditarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));

			// Ventana Persona mapping
			this.ventanaPersonaInsert = VentanaPersonaInsert.getInstance();
			this.ventanaPersonaUpdate = VentanaPersonaUpdate.getInstance();
			this.ventanaPersonaInsert.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersonaUpdate.getBtnEditarPersona().addActionListener(p->editarPersona(p));
			this.agenda = agenda;
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersonaInsert.mostrarVentana();
		}

		private void ventanaEditarPersona(ActionEvent a) {
			this.ventanaPersonaUpdate.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersonaInsert.getTxtNombre().getText();
			String tel = this.ventanaPersonaInsert.getTxtTelefono().getText();
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersonaInsert.cerrar();
		}

		private void editarPersona(ActionEvent p) {
			String nombre = this.ventanaPersonaUpdate.getTxtNombre().getText();
			String tel = this.ventanaPersonaUpdate.getTxtTelefono().getText();
			PersonaDTO persona = obtenerPersonaSeleccionada();
			persona.setNombre(nombre);
			persona.setTelefono(tel);
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
		
		public void inicializar()
		{
			this.refrescarTabla();
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
