package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ControladoraWallRose;
import Logica.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DetallesProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textnombre;
	private JTextField textexist;
	private JTextField textPrec;
	private Integer codigoProducto;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetallesProducto dialog = new DetallesProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DetallesProducto() {
        this(null);
    }
	/**
	 * Create the dialog.
	 */
	public DetallesProducto(Integer codigoProducto) {
		setModal(true);
		this.codigoProducto = codigoProducto;
		setBounds(100, 100, 284, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Detalle producto");
			lblNewLabel.setBounds(10, 10, 109, 21);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Código:");
			lblNewLabel_1.setBounds(10, 57, 52, 12);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("temp");
			lblNewLabel_2.setBounds(60, 57, 44, 12);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre:");
			lblNewLabel_3.setBounds(10, 86, 52, 12);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Existencias:");
			lblNewLabel_4.setBounds(10, 120, 80, 12);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Unidad:");
			lblNewLabel_5.setBounds(10, 156, 52, 12);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Precio:");
			lblNewLabel_6.setBounds(10, 191, 52, 12);
			contentPanel.add(lblNewLabel_6);
		}
		
		comboBox = new JComboBox<>(new String[]{"kg", "l", "m", "cm", "unidades"});
		comboBox.setBounds(60, 152, 120, 20);
        contentPanel.add(comboBox);
		
		textnombre = new JTextField();
		textnombre.setBounds(60, 83, 165, 18);
		contentPanel.add(textnombre);
		textnombre.setColumns(10);
		
		textexist = new JTextField();
		textexist.setBounds(81, 117, 144, 18);
		contentPanel.add(textexist);
		textexist.setColumns(10);
		
		textPrec = new JTextField();
		textPrec.setBounds(60, 188, 165, 18);
		contentPanel.add(textPrec);
		textPrec.setColumns(10);
		
		JButton guardarProd = new JButton("Guardar");
		guardarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonGuardar();
			}
		});
		guardarProd.setBounds(81, 226, 96, 27);
		contentPanel.add(guardarProd);
		
		if (codigoProducto != null) {
            try {
                ControladoraWallRose control = ControladoraWallRose.getInstance();
                Producto p = control.obtenerProducto(codigoProducto);
                textnombre.setText(p.getNombre());
                textexist.setText(String.valueOf(p.getExistencias()));
                textPrec.setText(String.valueOf(p.getPrecio()));
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    if (comboBox.getItemAt(i).equals(p.getUnidad())) {
                        comboBox.setSelectedIndex(i);
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(contentPanel, "Error al cargar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
}
