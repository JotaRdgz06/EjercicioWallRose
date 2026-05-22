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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Control.ControladoraWallRose;
import Logica.Cliente;
import Logica.Orden;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetalleOrdenCompra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static String idCliente;
	private Integer numeroOrden;	
	private JLabel id;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel numorden;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleOrdenCompra dialog = new DetalleOrdenCompra(idCliente);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleOrdenCompra(String idCliente) {
		this.idCliente = idCliente;
		setModal(true);
		setBounds(100, 100, 450, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Detalle orden de compra");
			lblNewLabel.setBounds(10, 21, 158, 12);
			contentPanel.add(lblNewLabel);
		}
		{
			id = new JLabel("tempID");
			id.setBounds(41, 38, 97, 12);
			contentPanel.add(id);
		}
		{
			lblNewLabel_1 = new JLabel("tempNom");
			lblNewLabel_1.setBounds(64, 56, 71, 12);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Número de orden:");
			lblNewLabel_3.setBounds(10, 78, 103, 12);
			contentPanel.add(lblNewLabel_3);
		}
		{
			numorden = new JLabel("tempNum");
			numorden.setBounds(124, 78, 44, 12);
			contentPanel.add(numorden);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Estado:");
			lblNewLabel_5.setBounds(265, 78, 56, 12);
			contentPanel.add(lblNewLabel_5);
		}
		{
			lblNewLabel_2 = new JLabel("tempEstado");
			lblNewLabel_2.setBounds(318, 78, 71, 12);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JButton btnagregar = new JButton("Agregar");
			btnagregar.setBounds(331, 112, 84, 20);
			contentPanel.add(btnagregar);
		}
		{
			JButton btneditar = new JButton("Editar");
			btneditar.setBounds(331, 142, 84, 20);
			contentPanel.add(btneditar);
		}
		{
			JButton btnborrar = new JButton("Borrar");
			btnborrar.setBounds(331, 172, 84, 20);
			contentPanel.add(btnborrar);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 101, 311, 152);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Cod producto", "Nombre producto", "Cantidad", "Costo"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(177);
				table.getColumnModel().getColumn(1).setPreferredWidth(216);
				table.getColumnModel().getColumn(2).setPreferredWidth(118);
				table.getColumnModel().getColumn(3).setPreferredWidth(136);
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Costo:");
			lblNewLabel_7.setBounds(172, 272, 56, 12);
			contentPanel.add(lblNewLabel_7);
		}
		{
			lblNewLabel_4 = new JLabel("₡0");
			lblNewLabel_4.setBounds(221, 272, 78, 12);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Impuesto: ");
			lblNewLabel_9.setBounds(161, 294, 65, 12);
			contentPanel.add(lblNewLabel_9);
		}
		{
			lblNewLabel_6 = new JLabel("₡0");
			lblNewLabel_6.setBounds(221, 294, 68, 12);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_11 = new JLabel("Total: ");
			lblNewLabel_11.setBounds(171, 316, 44, 12);
			contentPanel.add(lblNewLabel_11);
		}
		{
			lblNewLabel_8 = new JLabel("₡0");
			lblNewLabel_8.setBounds(221, 316, 78, 12);
			contentPanel.add(lblNewLabel_8);
		}
		{
			JButton btnNewButton = new JButton("Pendiente");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarAPendiente();
				}
			});
			btnNewButton.setBounds(318, 267, 97, 20);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Terminada");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarATerminada();
				}
			});
			btnNewButton_1.setBounds(318, 293, 97, 20);
			contentPanel.add(btnNewButton_1);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("ID: ");
			lblNewLabel_10.setBounds(10, 38, 44, 12);
			contentPanel.add(lblNewLabel_10);
		}
		{
			JLabel lblNewLabel_12 = new JLabel("Nombre:");
			lblNewLabel_12.setBounds(10, 56, 70, 12);
			contentPanel.add(lblNewLabel_12);
		}
		crearOrden();
		cargarDatosOrden();
	}
	
	private void crearOrden() {
	    ControladoraWallRose control = ControladoraWallRose.getInstance();
	    try {
	        control.crearOrdenVacia(idCliente);
	        List<Orden> ordenes = control.obtenerListadoOrdenesCliente(idCliente);
	        Orden ordenNueva = ordenes.get(ordenes.size() - 1);
	        numeroOrden = ordenNueva.getNumero();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(contentPanel, "Error al crear la orden", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void cargarDatosOrden() {
	    ControladoraWallRose control = ControladoraWallRose.getInstance();
	    try {
	        Cliente cliente = control.obtenerCliente(idCliente);
	        id.setText(cliente.getId());
	        lblNewLabel_1.setText(cliente.getNombre());

	        Orden orden = control.obtenerOrden(numeroOrden);
	        numorden.setText(String.valueOf(numeroOrden));
	        lblNewLabel_2.setText(orden.getEstado().toString());
	        actualizarTotales(orden);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(contentPanel, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void actualizarTotales(Orden orden) {
		lblNewLabel_4.setText(String.format("₡%.2f", orden.calcularMonto()));
		lblNewLabel_6.setText(String.format("₡%.2f", orden.calcularMontoImpuesto()));
		lblNewLabel_8.setText(String.format("₡%.2f", orden.calcularMontoTotal()));
	}
	
	private void cambiarAPendiente() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		try {
			control.establecerOrdenPendiente(numeroOrden);
			lblNewLabel_2.setText("PENDIENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Error al cambiar estado", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cambiarATerminada() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		try {
			control.establecerOrdenTerminada(numeroOrden);
			lblNewLabel_2.setText("TERMINADA");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Error al cambiar estado", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
