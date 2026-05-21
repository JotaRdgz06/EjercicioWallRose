package Interfaz;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import Control.ControladoraWallRose;
import Logica.Cliente;
import Logica.Orden;
import Logica.Producto;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTree;

public class VentanaInterfaz {

	private JFrame frame;
	private JTable tableCliente;
	private JTable tablaProducto;
	private JScrollPane scrollPaneProducto;

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
		tabbedPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarClientes();
			}
		});
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Clientes = new JPanel();
		tabbedPane.addTab("Clientes", null, Clientes, null);
		Clientes.setLayout(null);
		
		JButton Ver = new JButton("Ver");
		Ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCliente();
			}
		});
		Ver.setBounds(432, 10, 103, 20);
		Clientes.add(Ver);
		
		JButton Editar = new JButton("Editar");
		Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		Editar.setBounds(432, 40, 103, 20);
		Clientes.add(Editar);
		
		JButton Agregar = new JButton("Agregar");
		Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCliente();
			}
		});
		Agregar.setBounds(432, 70, 103, 20);
		Clientes.add(Agregar);
		
		JButton Borrar = new JButton("Borrar");
		Borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCliente();
			}
		});
		Borrar.setBounds(432, 100, 103, 20);
		Clientes.add(Borrar);
		
		scrollPaneProducto = new JScrollPane();
		scrollPaneProducto.setBounds(10, 10, 397, 267);
		Clientes.add(scrollPaneProducto);
		
		tableCliente = new JTable();
		tableCliente.setModel(new DefaultTableModel(
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
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(153);
		tableCliente.getColumnModel().getColumn(1).setPreferredWidth(225);
		tableCliente.getColumnModel().getColumn(2).setPreferredWidth(261);
		scrollPaneProducto.setViewportView(tableCliente);
		
		JPanel Ordenes = new JPanel();
		tabbedPane.addTab("Ordenes", null, Ordenes, null);
		
		JPanel Productos = new JPanel();
		Productos.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarProductos();
			}
		});
		tabbedPane.addTab("Productos", null, Productos, null);
		Productos.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Productos");
		lblNewLabel.setBounds(27, 10, 85, 12);
		Productos.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 442, 218);
		Productos.add(scrollPane_1);
		
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaProducto = new JTable();
		tablaProducto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Existencias", "Unidad", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(tablaProducto);
		
		JButton agregarProd = new JButton("Agregar");
		agregarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
				cargarProductos();
			}
		});
		agregarProd.setBounds(462, 48, 93, 20);
		Productos.add(agregarProd);
		
		JButton editarProd = new JButton("Editar");
		editarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarProducto();
				cargarProductos();
			}
		});
		editarProd.setBounds(462, 84, 93, 20);
		Productos.add(editarProd);
		
		JButton borrarProd = new JButton("Borrar");
		borrarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarProducto();
				cargarProductos();
			}
		});
		borrarProd.setBounds(462, 121, 93, 20);
		Productos.add(borrarProd);
	}
	
	private void borrarCliente() {
		int numeroFila = tableCliente.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
			String idCliente = (String)model.getValueAt(numeroFila, 0);
			String nombreCliente = (String)model.getValueAt(numeroFila, 1);
			int respuesta = JOptionPane.showConfirmDialog(frame, "Se eliminará la información del cliente" + nombreCliente + ", id: " + idCliente, "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				ControladoraWallRose control = ControladoraWallRose.getInstance();
				try {
					control.borrarCliente(idCliente);
					cargarClientes();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error al borrar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void cargarClientes() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
		model.setRowCount(0);
		List<Cliente> listaClientes = control.obtenerListadoClientes();
		for (Cliente cliente : listaClientes) {
			Object[] fila = new Object[] {cliente.getId(), cliente.getNombre(), cliente.getEmail()};
			model.addRow(fila);
		}
	}
	
	private void verCliente() {
		int numeroFila = tableCliente.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
			String idCliente = (String)model.getValueAt(numeroFila, 0);
			VerCliente ventanaDetalleCliente = new VerCliente(idCliente);
			ventanaDetalleCliente.setVisible(true);
		}
	}
	
	private void agregarCliente() {
		DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
		AgregarCliente ventanaDetalleCliente = new AgregarCliente();
		ventanaDetalleCliente.setVisible(true);
		cargarClientes();
	}
	
	private void editarCliente() {
		int numeroFila = tableCliente.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
			String idCliente = (String) model.getValueAt(numeroFila, 0);
			AgregarCliente ventana = new AgregarCliente(idCliente);
			ventana.setVisible(true); 
			cargarClientes();
		}
	}
	
	private void cargarProductos() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaProducto.getModel();
		model.setRowCount(0);
		List<Producto> listaProducto = control.obtenerListadoProductos();
		for (Producto producto : listaProducto) {
			Object[] fila = new Object[] {producto.getCodigo(), producto.getNombre(), producto.getExistencias(), producto.getUnidad(), producto.getPrecio()};
			model.addRow(fila);
		}
	}
	
	private void agregarProducto() {
		DetallesProducto ventana = new DetallesProducto();
		ventana.setVisible(true);
		cargarProductos();
	}
	
	private void editarProducto() {
		int numeroFila = tablaProducto.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaProducto.getModel();
			Integer codigo = (Integer) model.getValueAt(numeroFila, 0);
			DetallesProducto ventana = new DetallesProducto(codigo);
			ventana.setVisible(true);
			cargarProductos();
		}
	}
	
	private void borrarProducto() {
		int numeroFila = tablaProducto.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaProducto.getModel();
			Integer codigo = (Integer) model.getValueAt(numeroFila, 0);
			String nombre = (String) model.getValueAt(numeroFila, 1);
			int respuesta = JOptionPane.showConfirmDialog(frame, "Se eliminará el producto: " + nombre + " código: " + codigo, "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				ControladoraWallRose control = ControladoraWallRose.getInstance();
				try {
					control.borrarProducto(codigo);
					cargarProductos();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error al borrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
