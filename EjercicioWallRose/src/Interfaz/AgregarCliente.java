package Interfaz;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Logica.Cliente;
import Control.ControladoraWallRose;

public class AgregarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private String idClienteEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgregarCliente dialog = new AgregarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public AgregarCliente() {
        this(null);
    }
	public AgregarCliente(String idCliente) {
		this.idClienteEditar = idCliente;
		setBounds(100, 100, 308, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Agregar/editar cliente");
			lblNewLabel.setBounds(20, 22, 131, 22);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ID:");
			lblNewLabel_1.setBounds(20, 74, 29, 12);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setBounds(20, 122, 44, 12);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Email:");
			lblNewLabel_3.setBounds(20, 171, 44, 12);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textFieldID = new JTextField();
			textFieldID.setBounds(40, 71, 219, 18);
			contentPanel.add(textFieldID);
			textFieldID.setColumns(10);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(67, 119, 192, 18);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(55, 168, 204, 18);
			contentPanel.add(textFieldEmail);
			textFieldEmail.setColumns(10);
		}
		{
			JButton guardar = new JButton("Guardar");
			guardar.setBounds(20, 213, 106, 29);
			contentPanel.add(guardar);
		}
		if (idCliente != null) {
			textFieldID.setText(idCliente);
			textFieldID.setEditable(false);
            try {
                Cliente c = ControladoraWallRose.getInstance().obtenerCliente(idCliente);
                textFieldNombre.setText(c.getNombre());
                textFieldEmail.setText(c.getEmail());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar el cliente: " + e.getMessage());
            }
        }
        guardar.addActionListener(e -> guardar());
    }
	
	

}
