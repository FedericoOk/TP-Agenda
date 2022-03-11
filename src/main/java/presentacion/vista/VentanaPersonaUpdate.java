package presentacion.vista;

import javax.swing.JButton;

public class VentanaPersonaUpdate extends VentanaPersonaBase // TODO: mejor dejarlo como singleton
{
	VentanaPersonaUpdate() 
	{
		super();
		
		btnAceptar.setText("Editar");
		
		this.setVisible(false);
	}

	public JButton getBtnEditarPersona() 
	{
		return btnAceptar;
	}
	
}

