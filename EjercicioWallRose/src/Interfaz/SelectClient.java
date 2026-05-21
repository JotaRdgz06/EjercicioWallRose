package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Control.ControladoraWallRose;
import Logica.Cliente;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectClient extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectClient dialog = new SelectClient();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectClient() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccionar cliente");
		lblNewLabel.setBounds(21, 22, 130, 12);
		contentPanel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(21, 44, 394, 167);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(163);
		scrollPane.setViewportView(table);
		
		JButton btncrear = new JButton("Crear orden");
		btncrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDetalles();
			}
		});
		btncrear.setBounds(293, 221, 122, 20);
		contentPanel.add(btncrear);
		cargarClientes();
	}
	
	private void cargarClientes() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Cliente> listaClientes = control.obtenerListadoClientes();
		for (Cliente cliente : listaClientes) {
			Object[] fila = new Object[] {cliente.getId(), cliente.getNombre()};
			model.addRow(fila);
		}
	}
	
	private void mostrarDetalles() {
		DetalleOrdenCompra ventanaDetalleCliente = new DetalleOrdenCompra();
		ventanaDetalleCliente.setVisible(true);
		cargarClientes();
	}
}
