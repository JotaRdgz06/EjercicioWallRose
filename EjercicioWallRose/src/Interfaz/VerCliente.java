package Interfaz;

import javax.swing.JDialog;
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

public class VerCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable tablaCliente;
	private String idCliente;
	private JLabel textid;
	private JLabel texnom;
	private JLabel texema;
	private JLabel texpago;

	/**
	 * Create the panel.
	 */
	public VerCliente(String idCliente) {
		setResizable(false);
		setModal(true);
		this.idCliente = idCliente;
		setBounds(100, 100, 450, 330);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(32, 10, 91, 18);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(32, 56, 29, 12);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(32, 78, 64, 12);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(32, 100, 44, 12);
		getContentPane().add(lblNewLabel_3);
		
		textid = new JLabel("(Temp)");
		textid.setBounds(52, 56, 246, 12);
		getContentPane().add(textid);
		
		texnom = new JLabel("(temp)");
		texnom.setBounds(89, 78, 219, 12);
		getContentPane().add(texnom);
		
		texema = new JLabel("Temp");
		texema.setBounds(67, 100, 231, 12);
		getContentPane().add(texema);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 122, 267, 136);
		getContentPane().add(scrollPane);
		
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
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaCliente.getColumnModel().getColumn(0).setResizable(false);
		tablaCliente.getColumnModel().getColumn(0).setPreferredWidth(61);
		scrollPane.setViewportView(tablaCliente);
		
		JButton btnNewButton = new JButton("Todas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarTodas();
			}
		});
		btnNewButton.setBounds(309, 125, 105, 20);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Iniciadas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarIniciadas();
			}
		});
		btnNewButton_1.setBounds(309, 155, 105, 20);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Pendientes");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarPendientes();
			}
		});
		btnNewButton_2.setBounds(309, 185, 105, 20);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Terminadas");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarTerminadas();
			}
		});
		btnNewButton_3.setBounds(309, 215, 105, 20);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_7 = new JLabel("Total pendiente: ₡");
		lblNewLabel_7.setBounds(32, 268, 98, 12);
		getContentPane().add(lblNewLabel_7);
		
		texpago = new JLabel("₡0.00");
		texpago.setBounds(127, 268, 219, 12);
		getContentPane().add(texpago);
		
		cargarDatosCliente();
		cargarOrdenesCliente();
	}
	
	private void filtrarIniciadas() {
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
        model.setRowCount(0);
        try {
            List<Orden> ordenes = control.obtenerListadoOrdenesIniciadasCliente(idCliente);
            for (Orden orden : ordenes) {
                Object[] fila = new Object[] {orden.getNumero(), orden.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), orden.getEstado()};
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tablaCliente, "Error al filtrar ordenes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void filtrarPendientes() {
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
        model.setRowCount(0);
        try {
            List<Orden> ordenes = control.obtenerListadoOrdenesPendientesCliente(idCliente);
            for (Orden orden : ordenes) {
                Object[] fila = new Object[] {orden.getNumero(), orden.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), orden.getEstado()};
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tablaCliente, "Error al filtrar ordenes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void filtrarTerminadas() {
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
        model.setRowCount(0);
        try {
            List<Orden> ordenes = control.obtenerListadoOrdenesTerminadasCliente(idCliente);
            for (Orden orden : ordenes) {
                Object[] fila = new Object[] {orden.getNumero(), orden.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), orden.getEstado()};
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tablaCliente, "Error al filtrar ordenes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void filtrarTodas() {
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
        model.setRowCount(0);
        try {
            List<Orden> ordenes = control.obtenerListadoOrdenesCliente(idCliente);
            for (Orden orden : ordenes) {
                Object[] fila = new Object[] {orden.getNumero(), orden.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), orden.getEstado()};
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tablaCliente, "Error al filtrar ordenes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void cargarDatosCliente() {
		try {
			ControladoraWallRose control = ControladoraWallRose.getInstance();
			Cliente cliente = control.obtenerCliente(idCliente);
			textid.setText(cliente.getId());
			texnom.setText(cliente.getNombre());
			texema.setText(cliente.getEmail());
			double totalPendiente = 0;
			for (Orden orden : cliente.getOrdenes().values()) {
				if (orden.getEstado() == Logica.EstadoOrden.PENDIENTE) {
					totalPendiente += orden.calcularMontoTotal();
				}
			}
			texpago.setText(String.format("₡%.2f", totalPendiente));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar datos del cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarOrdenesCliente() {
		ControladoraWallRose control = ControladoraWallRose.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
		model.setRowCount(0);
		double totalPendiente = 0;
		List<Orden> listaOrden;
		try {
			listaOrden = control.obtenerListadoOrdenesCliente(idCliente);
		
		for (Orden orden: listaOrden) {
			Object[] fila = new Object[] {orden.getNumero(), orden.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), orden.getEstado()};
			model.addRow(fila);
			if (orden.getEstado() == Logica.EstadoOrden.PENDIENTE) {
	            totalPendiente += orden.calcularMontoTotal();
	        }
		}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar datos del cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}
		texpago.setText(String.format("₡%.2f", totalPendiente));
		filtrarTodas();
	}
}
