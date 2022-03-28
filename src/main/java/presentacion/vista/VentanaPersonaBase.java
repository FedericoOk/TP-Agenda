package presentacion.vista;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.toedter.calendar.JDateChooser;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContacto;

public abstract class VentanaPersonaBase extends JFrame {
	protected static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField txtNombre;
	protected JFormattedTextField txtTelefono;
	protected JTextField txtEmail;
	protected JDateChooser jDateChooser;
	protected JComboBox<TipoContacto> jComboBoxTipoContacto;
	protected JTextField txtCalle;
	protected JFormattedTextField txtAltura;
	protected JFormattedTextField txtPiso;
	protected JTextField txtDepto;
	protected JTextField txtPlataformaAlmacenamiento;
	protected JComboBox<String> jComboBoxMesNacimiento;
	protected JComboBox<PaisDTO> jComboBoxPais;
	private DefaultComboBoxModel<PaisDTO> modelPaises;
	protected JComboBox<ProvinciaDTO> jComboBoxProvincia;
	private DefaultComboBoxModel<ProvinciaDTO> modelProvincias;
	protected JComboBox<LocalidadDTO> jComboBoxLocalidad;
	private DefaultComboBoxModel<LocalidadDTO> modelLocalidades;
	protected JButton btnAceptar;
	protected MaskFormatter mask;

	protected VentanaPersonaBase(String title)
	{
		super(title);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 615);
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

		JLabel lblPais = new JLabel("País");
		lblPais.setBounds(10, 380, 113, 14);
		panel.add(lblPais);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 421, 113, 14);
		panel.add(lblProvincia);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 462, 113, 14);
		panel.add(lblLocalidad);

		JLabel lblPlataformaAlmacenamiento = new JLabel("Plataforma de Alm.");
		lblPlataformaAlmacenamiento.setBounds(10, 421, 113, 14);
		panel.add(lblPlataformaAlmacenamiento);

		JLabel lblMesNacimiento = new JLabel("Mes de Nacimiento");
		lblMesNacimiento.setBounds(10, 461, 113, 14);
		panel.add(lblMesNacimiento);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		try {
			mask = new MaskFormatter("(###) ####-####");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mask.setPlaceholderCharacter('_');

		txtTelefono = new JFormattedTextField(mask);
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

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);

		txtAltura = new JFormattedTextField(format);
		txtAltura.setBounds(133, 254, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JFormattedTextField(format);
		txtPiso.setBounds(133, 295, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepto = new JTextField();
		txtDepto.setBounds(133, 336, 164, 20);
		panel.add(txtDepto);
		txtDepto.setColumns(10);

		modelPaises = new DefaultComboBoxModel<PaisDTO>();
		jComboBoxPais = new JComboBox<>(modelPaises);
		jComboBoxPais.setBounds(133, 377, 164, 20);
		panel.add(jComboBoxPais);

		jComboBoxPais.addActionListener(l -> this.filtrarProvinciasPorPais());

		modelProvincias = new DefaultComboBoxModel<ProvinciaDTO>();
		jComboBoxProvincia = new JComboBox<>(modelProvincias);
		jComboBoxProvincia.setBounds(133, 418, 164, 20);
		panel.add(jComboBoxProvincia);

		jComboBoxProvincia.addActionListener(l -> this.filtrarLocalidadesPorProvincia());

		modelLocalidades= new DefaultComboBoxModel<LocalidadDTO>();
		jComboBoxLocalidad = new JComboBox<>(modelLocalidades);
		jComboBoxLocalidad.setBounds(133, 459, 164, 20);
		panel.add(jComboBoxLocalidad);

		txtPlataformaAlmacenamiento = new JTextField();
		txtPlataformaAlmacenamiento.setBounds(133, 418, 164, 20);
		panel.add(txtPlataformaAlmacenamiento);
		txtPlataformaAlmacenamiento.setColumns(10);

		jComboBoxMesNacimiento = new JComboBox<>();
		jComboBoxMesNacimiento.setBounds(133, 459, 164, 20);
		panel.add(jComboBoxMesNacimiento);

		btnAceptar = new JButton();
		btnAceptar.setBounds(208, 502, 89, 23);
		panel.add(btnAceptar);

		this.setVisible(false);
	}

	private void filtrarProvinciasPorPais() {
		PaisDTO paisSeleccionado = (PaisDTO) this.jComboBoxPais.getSelectedItem();

		if (paisSeleccionado == null)
			return;

		DefaultComboBoxModel<ProvinciaDTO> model = (DefaultComboBoxModel<ProvinciaDTO>) this.jComboBoxProvincia.getModel();
		model.removeAllElements();

		for (ProvinciaDTO provincia : paisSeleccionado.getProvincias())
			model.addElement(provincia);
	}

	private void filtrarLocalidadesPorProvincia() {
		ProvinciaDTO provinciaSeleccionada = (ProvinciaDTO) this.jComboBoxProvincia.getSelectedItem();

		if (provinciaSeleccionada == null)
			return;

		DefaultComboBoxModel<LocalidadDTO> model = (DefaultComboBoxModel<LocalidadDTO>) this.jComboBoxLocalidad.getModel();
		model.removeAllElements();

		for (LocalidadDTO localidad : provinciaSeleccionada.getLocalidades())
			model.addElement(localidad);
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
		this.txtPlataformaAlmacenamiento.setText(personaDTO.getPlataformaAlmacenamiento());
		this.jComboBoxPais.setSelectedItem(personaDTO.getDomicilio().getLocalidad().getProvincia().getPais());
		this.jComboBoxProvincia.setSelectedItem(personaDTO.getDomicilio().getLocalidad().getProvincia());
		this.jComboBoxLocalidad.setSelectedItem(personaDTO.getDomicilio().getLocalidad());
		this.jComboBoxMesNacimiento.setSelectedItem(personaDTO.getMesNacimiento());
		this.setVisible(true);
	}

	public void mostrarVentana() {
		this.jComboBoxTipoContacto.setSelectedIndex(-1);
		this.jComboBoxPais.setSelectedIndex(-1);
		this.jComboBoxProvincia.setSelectedIndex(-1);
		this.jComboBoxLocalidad.setSelectedIndex(-1);
		this.setVisible(true);
	}

	public void llenarComboTipoContacto(List<TipoContacto> tiposContactoEnLista) {
		this.jComboBoxTipoContacto.removeAllItems();
		for (TipoContacto tipoContacto : tiposContactoEnLista)
			this.jComboBoxTipoContacto.addItem(tipoContacto);
	}

	public void llenarComboPaises(List<PaisDTO> paisesEnLista) {
		this.modelPaises.removeAllElements();
		for (PaisDTO pais : paisesEnLista)
			this.modelPaises.addElement(pais);
	}

	public void llenarComboProvincias(List<ProvinciaDTO> provinciasEnLista) {
		this.modelProvincias.removeAllElements();
		for (ProvinciaDTO provincia : provinciasEnLista)
			this.modelProvincias.addElement(provincia);
	}

	public void llenarComboLocalidades(List<LocalidadDTO> localidadesEnLista) {
		this.modelLocalidades.removeAllElements();
		for (LocalidadDTO localidad : localidadesEnLista)
			this.modelLocalidades.addElement(localidad);
	}

	public void llenarComboMesNacimiento() {
		this.jComboBoxMesNacimiento.removeAllItems();
		String[] Meses = new String[] { null, "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre", "Diciembre" };
		for (String mes : Meses)
			jComboBoxMesNacimiento.addItem(mes);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JDateChooser getDateChooser() {
		return jDateChooser;
	}

	public JComboBox<TipoContacto> getComboTipoContacto() {
		return this.jComboBoxTipoContacto;
	}

	public JComboBox<String> getComboMesNacimiento() {
		return this.jComboBoxMesNacimiento;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JFormattedTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepto() {
		return txtDepto;
	}

	public JTextField getTxtPlataformaAlmacenamiento() {
		return txtPlataformaAlmacenamiento;
	}
	
	public JComboBox<PaisDTO> getJComboPais() {
		return jComboBoxPais;
	}

	public JComboBox<ProvinciaDTO> getJComboProvincia() {
		return jComboBoxProvincia;
	}

	public JComboBox<LocalidadDTO> getJComboLocalidad() {
		return jComboBoxLocalidad;
	}

	public DefaultComboBoxModel<PaisDTO> getModeloPaises() {
		return this.modelPaises;
	}

	public DefaultComboBoxModel<ProvinciaDTO> getModeloProvincias() {
		return this.modelProvincias;
	}

	public DefaultComboBoxModel<LocalidadDTO> getModeloLocalidades() {
		return this.modelLocalidades;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtEmail.setText(null);
		this.jDateChooser.setDate(null);
		this.jComboBoxTipoContacto.setSelectedIndex(-1);
		this.jComboBoxMesNacimiento.setSelectedIndex(-1);
		this.txtCalle.setText(null);
		this.txtDepto.setText(null);
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtPlataformaAlmacenamiento.setText(null);
		this.jComboBoxPais.setSelectedIndex(-1);
		this.jComboBoxProvincia.setSelectedIndex(-1);
		this.jComboBoxLocalidad.setSelectedIndex(-1);
		this.dispose();
	}

}
