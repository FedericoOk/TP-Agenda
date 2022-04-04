package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;
import dto.TipoContacto;

import javax.swing.JButton;
import javax.swing.JComboBox;

import persistencia.conexion.Conexion;
import persistencia.conexion.EntityManagers;

public class Vista {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnReporte1;
	private JButton btnReporte2;
	private JMenuItem menuItemTipoContacto;
	private JMenuItem menuItemUbicaciones;
	private JMenuItem menuItemConfiguration;
	private DefaultTableModel modelPersonas;
	private JComboBox<String> jComboBoxSort;
	private String[] nombreColumnas = { "Nombre y apellido", "Telefono", "Email", "Nacimiento", "TipoContacto" };

	public Vista() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1047, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1020, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 1010, 232);
		panel.add(spPersonas);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("ABM");
		menuBar.add(menu);

		menuItemTipoContacto = new JMenuItem("Tipo Contacto");
		menu.add(menuItemTipoContacto);

		menuItemUbicaciones = new JMenuItem("Ubicaciones");
		menu.add(menuItemUbicaciones);

		JMenu configMenu = new JMenu("Configuración");
		menuBar.add(configMenu);

		menuItemConfiguration = new JMenuItem("Conexión DB");
		configMenu.add(menuItemConfiguration);

		modelPersonas = new DefaultTableModel(null, nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(2).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(3).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(4).setResizable(false);

		spPersonas.setViewportView(tablaPersonas);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 300, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(240, 300, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(470, 300, 89, 23);
		panel.add(btnBorrar);

		btnReporte1 = new JButton("1° Reporte");
		btnReporte1.setBounds(700, 300, 95, 23);
		panel.add(btnReporte1);

		JLabel lblOrdenamiento = new JLabel("Ordenamiento");
		lblOrdenamiento.setBounds(760, 258, 95, 23);
		panel.add(lblOrdenamiento);

		jComboBoxSort = new JComboBox<>();
		jComboBoxSort.setBounds(850, 258, 95, 23);
		panel.add(jComboBoxSort);

		this.jComboBoxSort.addItem("Descendente");
		this.jComboBoxSort.addItem("Ascendente");

		btnReporte2 = new JButton("2° Reporte");
		btnReporte2.setBounds(925, 300, 95, 23);
		panel.add(btnReporte2);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();

					EntityManagers.close();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnReporte1() {
		return btnReporte1;
	}

	public JButton getBtnReporte2() {
		return btnReporte2;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public JMenuItem getMenuItemTipoContacto() {
		return menuItemTipoContacto;
	}

	public JMenuItem getMenuItemUbicaciones() {
		return menuItemUbicaciones;
	}

	public JMenuItem getMenuItemConfiguracion() {
		return menuItemConfiguration;
	}

	public JComboBox<String> getComboSort() {
		return this.jComboBoxSort;
	}

	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla) {
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			String email = p.getEmail();
			Date nacimiento = p.getNacimiento();
			TipoContacto tipoContacto = p.getTipoContacto();
			Object[] fila = { nombre, tel, email, nacimiento, tipoContacto };
			this.getModelPersonas().addRow(fila);
		}
	}
}
