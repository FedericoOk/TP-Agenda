package presentacion.vista;

import javax.swing.JButton;

public class VentanaPersonaInsert extends VentanaPersonaBase 
{

	// private static VentanaPersona INSTANCE; // TODO: mejor dejarlo como singleton

	// public static VentanaPersona getInstance()
	// {
	// 	if(INSTANCE == null)
	// 	{
	// 		INSTANCE = new VentanaPersona(); 	
	// 		return new VentanaPersona();
	// 	}
	// 	else
	// 		return INSTANCE;
	// }

	VentanaPersonaInsert() 
	{
		super();
		
		btnAceptar.setText("Agregar");
		
		this.setVisible(false);
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAceptar;
	}
	
}

