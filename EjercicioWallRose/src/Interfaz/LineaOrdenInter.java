package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Control.ControladoraWallRose;
import Logica.Producto;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LineaOrdenInter extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private Integer numeroOrden;
	private Integer numeroLinea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LineaOrdenInter dialog = new LineaOrdenInter(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LineaOrdenInter(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista de orden");
		lblNewLabel.setBounds(10, 10, 92, 27);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setBounds(20, 47, 116, 12);
		contentPanel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 416, 149);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"cod producto", "Nombre", "Precio", "Existencias"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 228, 64, 25);
		contentPanel.add(lblCantidad);
		
		textField = new JTextField();
		textField.setBounds(67, 231, 96, 18);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto();
			}
		});
		btnNewButton.setBounds(342, 230, 84, 20);
		contentPanel.add(btnNewButton);
		cargarProductos();
	}
	
	private void cargarProductos() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Producto> listaProducto = control.obtenerListadoProductos();
		for (Producto producto : listaProducto) {
			Object[] fila = new Object[] {producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getExistencias()};
			model.addRow(fila);
		}
	}
	
	private void agregarProducto() {
	    int fila = table.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    String cantidadTexto = textField.getText().trim();
	    if (cantidadTexto.isEmpty()) {
	        JOptionPane.showMessageDialog(contentPanel, "Debe ingresar una cantidad", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    double cantidad;
	    try {
	        cantidad = Double.parseDouble(cantidadTexto);
	        if (cantidad <= 0) {
	        	JOptionPane.showMessageDialog(contentPanel, "La cantidad debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
	        	return;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(contentPanel, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }	
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    Integer codigoProducto = Integer.valueOf(model.getValueAt(fila, 0).toString());
	    double existencias = Double.parseDouble(model.getValueAt(fila, 3).toString());
	    if (cantidad > existencias) {
	    	JOptionPane.showMessageDialog(contentPanel, "La cantidad no puede superar las existencias", "Error", JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    ControladoraWallRose control = ControladoraWallRose.getInstance();
	    try {
	    	if (numeroLinea == null) {
	    	    control.agregarLineaOrden(numeroOrden, codigoProducto, cantidad);
	    	    dispose();
	    	} else {
	    	    control.actualizarLineaOrden(numeroOrden, numeroLinea, codigoProducto, cantidad);
	    	    dispose();
	    	}
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(contentPanel, "Error al agregar el producto: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
}