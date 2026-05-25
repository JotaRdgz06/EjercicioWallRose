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
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.IOException;

public class VentanaInterfaz {

	private JFrame frame;
	private JTable tableCliente;
	private JTable tablaProducto;
	private JScrollPane scrollPaneProducto;
	private JTable tablaOrdenes;
	private JLabel totalTotal;

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
				cargarOrdenes();
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
				"ID", "Nombre", "Email"
			}
		));
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(83);
		tableCliente.getColumnModel().getColumn(1).setPreferredWidth(130);
		tableCliente.getColumnModel().getColumn(2).setPreferredWidth(137);
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneProducto.setViewportView(tableCliente);
		
		JPanel Ordenes = new JPanel();
		tabbedPane.addTab("Ordenes", null, Ordenes, null);
		Ordenes.setLayout(null);
		
		JButton agregarbtn = new JButton("Nueva");
		agregarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarOrden();
			}
		});
		agregarbtn.setBounds(456, 82, 84, 20);
		Ordenes.add(agregarbtn);
		
		JButton editarbtn = new JButton("Editar");
		editarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOrden();
			}
		});
		editarbtn.setBounds(456, 124, 84, 20);
		Ordenes.add(editarbtn);
		
		JButton borrarbtn = new JButton("Borrar");
		borrarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarOrden();
				cargarOrdenes();
			}
		});
		borrarbtn.setBounds(456, 166, 84, 20);
		Ordenes.add(borrarbtn);
		
		JScrollPane scrollPaneOrd = new JScrollPane();
		scrollPaneOrd.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
				cargarOrdenes();
			}
		});
		scrollPaneOrd.setBounds(10, 10, 437, 229);
		Ordenes.add(scrollPaneOrd);
		
		tablaOrdenes = new JTable();
		tablaOrdenes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00FAmero", "Fecha", "Estado"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaOrdenes.getColumnModel().getColumn(0).setResizable(false);
		tablaOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneOrd.setViewportView(tablaOrdenes);
		
		JLabel lblNewLabel_1 = new JLabel("Total pendiente:");
		lblNewLabel_1.setBounds(24, 253, 116, 12);
		Ordenes.add(lblNewLabel_1);
		
		totalTotal = new JLabel("₡0.00");
		totalTotal.setBounds(130, 253, 104, 12);
		Ordenes.add(totalTotal);
		
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
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatos();
			}
		});
		btnNewButton.setBounds(432, 208, 103, 20);
		Clientes.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cargar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos();
				cargarProductos();
				cargarClientes();
				cargarOrdenes();
			}
		});
		btnNewButton_1.setBounds(432, 238, 103, 20);
		Clientes.add(btnNewButton_1);
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
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaProducto.getColumnModel().getColumn(0).setResizable(false);
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
					cargarOrdenes();
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
			String nombreCliente = (String) model.getValueAt(numeroFila, 1);
			AgregarCliente ventana = new AgregarCliente(idCliente, nombreCliente);
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
					JOptionPane.showMessageDialog(frame, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void borrarOrden() {
		int numeroFila = tablaOrdenes.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar una orden", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
			Integer numero = (Integer) model.getValueAt(numeroFila, 0);
			int respuesta = JOptionPane.showConfirmDialog(frame, "Se eliminará la orden número " + numero, "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				ControladoraWallRose control = ControladoraWallRose.getInstance();
				try {
					control.borrarOrden(numero);
					cargarOrdenes();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Error al borrar la orden", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void cargarOrdenes() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
		model.setRowCount(0);
		double totalPendiente = 0;
		List<Orden> listaOrden = control.obtenerListadoOrdenes();
		for (Orden orden: listaOrden) {
			Object[] fila = new Object[] {orden.getNumero(), orden.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), orden.getEstado()};
			model.addRow(fila);
			if (orden.getEstado() == Logica.EstadoOrden.PENDIENTE) {
	            totalPendiente += orden.calcularMontoTotal();
	        }
		}
		totalTotal.setText(String.format("₡%.2f", totalPendiente));
	}
	
	private void agregarOrden() {
		SelectClient ventanaDetalleCliente = new SelectClient();
		ventanaDetalleCliente.setVisible(true);
		cargarOrdenes();
	}
	
	private void editarOrden() {
	    int numeroFila = tablaOrdenes.getSelectedRow();
	    if (numeroFila == -1) {
	        JOptionPane.showMessageDialog(frame, "Debe seleccionar una orden", "Error", JOptionPane.ERROR_MESSAGE);
	    } else {
	        DefaultTableModel model = (DefaultTableModel) tablaOrdenes.getModel();
	        Integer numOrden = (Integer) model.getValueAt(numeroFila, 0);
	        DetalleOrdenCompra ventana = new DetalleOrdenCompra(numOrden);
	        ventana.setVisible(true); 
	        cargarOrdenes();
	    }
	}
	
	private void cargarDatos() {
		try {
			ControladoraWallRose.cargarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al cargar los datos" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDatos() {
		try {
			ControladoraWallRose.guardarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al guardar los datos: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
