package presentacion.vista;

import javax.swing.JButton;

public class VentanaPersonaInsert extends VentanaPersonaBase 
{

	private static VentanaPersonaInsert INSTANCE;

	public static VentanaPersonaInsert getInstance()
	{
		if (INSTANCE == null)
			INSTANCE = new VentanaPersonaInsert(); 
		return INSTANCE;
	}

	private VentanaPersonaInsert()
	{
		super("Agregar persona");
		
		btnAceptar.setText("Agregar");
		
		this.setVisible(false);
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAceptar;
	}
	
}

