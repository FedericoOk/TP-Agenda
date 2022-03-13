package presentacion.vista;

import javax.swing.JButton;

public class VentanaPersonaUpdate extends VentanaPersonaBase
{
	
	private static VentanaPersonaUpdate INSTANCE;

	public static VentanaPersonaUpdate getInstance()
	{
		if (INSTANCE == null)
			INSTANCE = new VentanaPersonaUpdate(); 
		return INSTANCE;
	}

	private VentanaPersonaUpdate() 
	{
		super("Actualizar persona");
		
		btnAceptar.setText("Editar");
		
		this.setVisible(false);
	}

	public JButton getBtnEditarPersona() 
	{
		return btnAceptar;
	}
	
}

