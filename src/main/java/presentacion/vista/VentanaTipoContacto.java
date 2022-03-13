package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import dto.TipoContacto;

public class VentanaTipoContacto extends JFrame {

    private JTextField txtDescription;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JList<TipoContacto> listaTipoContacto;
    private DefaultListModel<TipoContacto> modeloListaContacto;
    private JSplitPane splitPane;

    private static VentanaTipoContacto INSTANCE;

    public static VentanaTipoContacto getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VentanaTipoContacto();
        return INSTANCE;
    }

    public VentanaTipoContacto() {
        super();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 450, 350);

        getContentPane().setLayout(new BorderLayout(0, 0));

        splitPane = new JSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        splitPane.setLeftComponent(scrollPane);

        modeloListaContacto = new DefaultListModel<>();
        listaTipoContacto = new JList<TipoContacto>(modeloListaContacto);
        listaTipoContacto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(listaTipoContacto);
        listaTipoContacto.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof TipoContacto) {
                    TipoContacto elemento = (TipoContacto) value;
                    setText(elemento.getDescription());
                    setToolTipText(elemento.getDescription());
                }

                return this;
            }
        });

        JLabel lblTipoContactos = new JLabel("Tipos de Contactos");
		lblTipoContactos.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTipoContactos.setOpaque(true);
		lblTipoContactos.setBackground(Color.LIGHT_GRAY);
		scrollPane.setColumnHeaderView(lblTipoContactos);
		
		JPanel panelForm = new JPanel();
		splitPane.setRightComponent(panelForm);
		GridBagLayout gbl_panelForm = new GridBagLayout();
		gbl_panelForm.columnWidths = new int[]{365, 0};
		gbl_panelForm.rowHeights = new int[]{202, 56, 0};
		gbl_panelForm.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelForm.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelForm.setLayout(gbl_panelForm);
		
		JPanel panelData = new JPanel();
		GridBagConstraints gbc_panelData = new GridBagConstraints();
		gbc_panelData.insets = new Insets(0, 0, 5, 0);
		gbc_panelData.gridx = 0;
		gbc_panelData.gridy = 0;
		panelForm.add(panelData, gbc_panelData);
		panelData.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNombre = new JLabel("Nombre");
		panelData.add(lblNombre);
		
		txtDescription = new JTextField();
		panelData.add(txtDescription);
		txtDescription.setColumns(10);
		
		JPanel panelOperaciones = new JPanel();
		GridBagConstraints gbc_panelOperaciones = new GridBagConstraints();
		gbc_panelOperaciones.gridx = 0;
		gbc_panelOperaciones.gridy = 1;
		panelForm.add(panelOperaciones, gbc_panelOperaciones);
		panelOperaciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAgregar = new JButton("Agregar");
		panelOperaciones.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		panelOperaciones.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		panelOperaciones.add(btnEliminar);
				
		this.setVisible(false);
		
		pack();
		restoreDefaults();
    }

    private void restoreDefaults() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                splitPane.setDividerLocation(0.4);
            }
        });
    }

    public void llenarLista(List<TipoContacto> tiposContactoEnLista) {
        this.modeloListaContacto.clear();

        for (TipoContacto t : tiposContactoEnLista)
            this.modeloListaContacto.addElement(t);
    }

    public JTextField getTxtDescription() {
        return this.txtDescription;
    }

    public JList<TipoContacto> getListaTipoContacto() {
        return this.listaTipoContacto;
    }

    public JButton getBtnAgregar() {
        return this.btnAgregar;
    }

    public JButton getBtnEditar() {
        return this.btnEditar;
    }

    public JButton getBtnEliminar() {
        return this.btnEliminar;
    }

    public void mostrarVentana() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.txtDescription.setText(null);
        this.dispose();
    }
    
}
