package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Logica.Cliente;
import Control.ControladoraWallRose;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private String idClienteEditar;
	private String nombreClienteEditar;

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
        this(null, null);
    }
	public AgregarCliente(String idCliente, String nombreCliente) {
		setModal(true);
		setResizable(false);
		this.idClienteEditar = idCliente;
		this.nombreClienteEditar = nombreCliente;
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
			lblNewLabel_2.setBounds(20, 122, 54, 12);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Email:");
			lblNewLabel_3.setBounds(20, 171, 44, 12);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textFieldId = new JTextField();
			textFieldId.setBounds(40, 71, 219, 18);
			contentPanel.add(textFieldId);
			textFieldId.setColumns(10);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(76, 119, 183, 18);
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
			guardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					botonGuardar();
				}
			});
			guardar.setBounds(20, 213, 106, 29);
			contentPanel.add(guardar);
		}
		if (idCliente != null) {
			textFieldId.setText(idCliente);
			textFieldId.setEditable(false);
            try {
            	ControladoraWallRose control = ControladoraWallRose.getInstance();
            	Cliente c = control.obtenerCliente(idCliente);
                textFieldNombre.setText(c.getNombre());
                textFieldEmail.setText(c.getEmail());
                if (!c.getOrdenes().isEmpty()) {
                    textFieldNombre.setEditable(false);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(contentPanel, "No se pudo cargar el cliente" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
		}
    }
	
	private void botonGuardar() {
        String id = textFieldId.getText().trim();
        String nombre = textFieldNombre.getText().trim();
        String email = textFieldEmail.getText().trim();
        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(contentPanel, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(contentPanel, "El email no es válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(contentPanel, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        try {
			if (idClienteEditar == null) {
				control.crearCliente(id, nombre, email);
				JOptionPane.showMessageDialog(contentPanel, "Cliente agregado correctamente");
			} else {
				control.actualizarCliente(idClienteEditar, nombre, email);
				JOptionPane.showMessageDialog(contentPanel, "Cliente actualizado correctamente");
			}
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
