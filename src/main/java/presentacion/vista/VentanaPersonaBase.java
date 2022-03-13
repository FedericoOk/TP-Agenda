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
	protected JButton btnAceptar;

	protected VentanaPersonaBase(String title) 
	{
		super(title);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 246);
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

		btnAceptar = new JButton();
		btnAceptar.setBounds(208, 215, 89, 23);
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
		this.setVisible(true);
	}

	public void mostrarVentana()
	{
		this.jComboBoxTipoContacto.setSelectedIndex(-1);
		this.setVisible(true);
	}

	public void llenarComboTipoContacto(List<TipoContacto> tiposContactoEnLista) {
		this.jComboBoxTipoContacto.removeAllItems();
		for (TipoContacto tipoContacto : tiposContactoEnLista)
			this.jComboBoxTipoContacto.addItem(tipoContacto);
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

