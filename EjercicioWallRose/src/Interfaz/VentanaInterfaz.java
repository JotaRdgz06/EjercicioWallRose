package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import Control.ControladoraWallRose;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class VentanaInterfaz {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInterfaz window = new VentanaInterfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInterfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 584, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Clientes = new JPanel();
		tabbedPane.addTab("Clientes", null, Clientes, null);
		Clientes.setLayout(null);
		
		JButton Ver = new JButton("Ver");
		Ver.setBounds(432, 32, 103, 20);
		Clientes.add(Ver);
		
		JButton Editar = new JButton("Editar");
		Editar.setBounds(432, 62, 103, 20);
		Clientes.add(Editar);
		
		JButton Agregar = new JButton("Agregar");
		Agregar.setBounds(432, 92, 103, 20);
		Clientes.add(Agregar);
		
		JButton Borrar = new JButton("Borrar");
		Borrar.setBounds(432, 122, 103, 20);
		Clientes.add(Borrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 397, 245);
		Clientes.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Titulo nombre", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(153);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		table.getColumnModel().getColumn(2).setPreferredWidth(261);
		scrollPane.setViewportView(table);
		
		JPanel Ordenes = new JPanel();
		tabbedPane.addTab("Ordenes", null, Ordenes, null);
		
		JPanel Productos = new JPanel();
		tabbedPane.addTab("Productos", null, Productos, null);
		Productos.setLayout(null);
	}
	
	private void BotonBorrar() {
		
	}
	
	private void cargarClientes() {
		
	}
}
