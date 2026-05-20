package Interfaz;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Control.ControladoraWallRose;
import Logica.Cliente;
import Logica.Orden;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VerCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaCliente;
	private String idCliente;

	/**
	 * Create the panel.
	 */
	public VerCliente(String idCliente) {
		this.idCliente = idCliente;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(32, 28, 91, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(32, 73, 44, 12);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(32, 95, 44, 12);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(32, 117, 44, 12);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("(Temp)");
		lblNewLabel_4.setBounds(53, 73, 44, 12);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("(temp)");
		lblNewLabel_5.setBounds(79, 95, 44, 12);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Temp");
		lblNewLabel_6.setBounds(64, 117, 44, 12);
		add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 139, 267, 136);
		add(scrollPane);
		
		tablaCliente = new JTable();
		tablaCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00FAmero", "Fecha", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tablaCliente);
		
		JButton btnNewButton = new JButton("Todas");
		btnNewButton.setBounds(335, 140, 84, 20);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Iniciadas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIniciadas();
			}
		});
		btnNewButton_1.setBounds(335, 170, 84, 20);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pendientes");
		btnNewButton_2.setBounds(335, 200, 84, 20);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Terminadas");
		btnNewButton_3.setBounds(335, 230, 84, 20);
		add(btnNewButton_3);
		
		JLabel lblNewLabel_7 = new JLabel("Total pendiente:");
		lblNewLabel_7.setBounds(32, 295, 84, 12);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("temp total");
		lblNewLabel_8.setBounds(114, 295, 109, 12);
		add(lblNewLabel_8);

	}
	
	private void filtrarIniciadas() {
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
        model.setRowCount(0);
        try {
            List<Orden> ordenes = control.obtenerListadoOrdenesIniciadasCliente(idCliente);
            for (Orden orden : ordenes) {
                Object[] fila = new Object[] {orden.getNumero(), orden.getFecha(), orden.getEstado()};
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tablaCliente, "Error al filtrar ordenes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
