package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContacto;

public abstract class VentanaPersonaBase extends JFrame 
{
	protected static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField txtNombre;
	protected JTextField txtTelefono;
	protected JTextField txtEmail;
	protected JDateChooser jDateChooser;
	protected JComboBox<TipoContacto> jComboBoxTipoContacto;
	protected JTextField txtCalle;
	protected JTextField txtAltura;
	protected JTextField txtPiso;
	protected JTextField txtDepto;
	protected JComboBox<LocalidadDTO> jComboBoxLocalidad;
	protected JButton btnAceptar;

	protected VentanaPersonaBase(String title) 
	{
		super(title);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 451);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 93, 113, 14);
		panel.add(lblEmail);

		JLabel lblFechaNacimiento = new JLabel("Nacimiento");
		lblFechaNacimiento.setBounds(10, 134, 113, 14);
		panel.add(lblFechaNacimiento);

		JLabel lblTipoContacto = new JLabel("Tipo Contacto");
		lblTipoContacto.setBounds(10, 175, 113, 14);
		panel.add(lblTipoContacto);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 216, 113, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 257, 113, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 298, 113, 14);
		panel.add(lblPiso);

		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(10, 339, 113, 14);
		panel.add(lblDepto);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 380, 113, 14);
		panel.add(lblLocalidad);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 90, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		jDateChooser = new JDateChooser("dd/MM/yyyy", "##/##/##", '_');
		jDateChooser.setBounds(133, 131, 164, 20);
		panel.add(jDateChooser);
		
		jComboBoxTipoContacto = new JComboBox<>();
		jComboBoxTipoContacto.setBounds(133, 172, 164, 20);
		panel.add(jComboBoxTipoContacto);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, 213, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, 254, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JTextField();
		txtPiso.setBounds(133, 295, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepto = new JTextField();
		txtDepto.setBounds(133, 336, 164, 20);
		panel.add(txtDepto);
		txtDepto.setColumns(10);

		jComboBoxLocalidad = new JComboBox<>();
		jComboBoxLocalidad.setBounds(133, 377, 164, 20);
		panel.add(jComboBoxLocalidad);

		btnAceptar = new JButton();
		btnAceptar.setBounds(208, 420, 89, 23);
		panel.add(btnAceptar);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana(PersonaDTO personaDTO)
	{
		this.txtNombre.setText(personaDTO.getNombre());
		this.txtTelefono.setText(personaDTO.getTelefono());
		this.txtEmail.setText(personaDTO.getEmail());
		this.jDateChooser.setDate(personaDTO.getNacimiento());
		this.jComboBoxTipoContacto.setSelectedItem(personaDTO.getTipoContacto());
		this.txtCalle.setText(personaDTO.getDomicilio().getCalle());
		this.txtAltura.setText(personaDTO.getDomicilio().getAltura());
		this.txtPiso.setText(personaDTO.getDomicilio().getPiso());
		this.txtDepto.setText(personaDTO.getDomicilio().getDepto());
		this.jComboBoxLocalidad.setSelectedItem(personaDTO.getDomicilio().getLocalidad());
		this.setVisible(true);
	}

	public void mostrarVentana()
	{
		this.jComboBoxTipoContacto.setSelectedIndex(-1);
		this.jComboBoxLocalidad.setSelectedIndex(-1);
		this.setVisible(true);
	}

	public void llenarComboTipoContacto(List<TipoContacto> tiposContactoEnLista) {
		this.jComboBoxTipoContacto.removeAllItems();
		for (TipoContacto tipoContacto : tiposContactoEnLista)
			this.jComboBoxTipoContacto.addItem(tipoContacto);
	}

	public void llenarComboLocalidades(List<LocalidadDTO> localidadesEnLista) {
		this.jComboBoxLocalidad.removeAllItems();
		for (LocalidadDTO localidad : localidadesEnLista)
			this.jComboBoxLocalidad.addItem(localidad);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JTextField getTxtEmail() 
	{
		return txtEmail;
	}

	public JDateChooser getDateChooser() 
	{
		return jDateChooser;
	}

	public JComboBox<TipoContacto> getComboTipoContacto() {
		return this.jComboBoxTipoContacto;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepto() {
		return txtDepto;
	}

	public JComboBox<LocalidadDTO> getJComboLocalidad() {
		return jComboBoxLocalidad;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtEmail.setText(null);
		this.jDateChooser.setDate(null);
		this.jComboBoxTipoContacto.setSelectedIndex(-1);
		this.dispose();
	}
	
}

