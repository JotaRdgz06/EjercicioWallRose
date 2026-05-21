package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import Control.ControladoraWallRose;
import Logica.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesProducto extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JLabel labelCodigo;
    private JTextField textnombre;
    private JTextField textexist;
    private JTextField textprecio;
    private JComboBox comboUnidad;

    private Integer codigoProducto;

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

    public DetallesProducto(Integer codigoProducto) {
        setModal(true);
        this.codigoProducto = codigoProducto;
        setBounds(100, 100, 284, 310);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblTitulo = new JLabel("Detalle producto");
        lblTitulo.setBounds(10, 10, 200, 21);
        contentPanel.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(10, 50, 52, 12);
        contentPanel.add(lblCodigo);

        ControladoraWallRose control = ControladoraWallRose.getInstance();
        String textoCodigo;
        if (codigoProducto == null) {
            Integer siguienteCodigo = control.obtenerSiguienteCodigoProducto();
            textoCodigo = siguienteCodigo.toString();
        } else {
            textoCodigo = codigoProducto.toString();
        }
        labelCodigo = new JLabel(textoCodigo);
        labelCodigo.setBounds(65, 50, 150, 12);
        contentPanel.add(labelCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 83, 52, 12);
        contentPanel.add(lblNombre);

        textnombre = new JTextField();
        textnombre.setBounds(65, 80, 180, 18);
        textnombre.setColumns(10);
        contentPanel.add(textnombre);

        JLabel lblExist = new JLabel("Existencias:");
        lblExist.setBounds(10, 117, 67, 12);
        contentPanel.add(lblExist);

        textexist = new JTextField();
        textexist.setBounds(81, 114, 164, 18);
        textexist.setColumns(10);
        contentPanel.add(textexist);

        JLabel lblUnidad = new JLabel("Unidad:");
        lblUnidad.setBounds(10, 151, 52, 12);
        contentPanel.add(lblUnidad);

        comboUnidad = new JComboBox<>(new String[]{"KG", "L", "M", "CM"});
        comboUnidad.setBounds(65, 147, 120, 20);
        contentPanel.add(comboUnidad);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 186, 52, 12);
        contentPanel.add(lblPrecio);

        textprecio = new JTextField();
        textprecio.setBounds(65, 183, 180, 18);
        textprecio.setColumns(10);
        contentPanel.add(textprecio);

        JButton guardarProd = new JButton("Guardar");
        guardarProd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botonGuardar();
            }
        });
        guardarProd.setBounds(86, 230, 96, 27);
        contentPanel.add(guardarProd);

        if (codigoProducto != null) {
            try {
                Producto p = control.obtenerProducto(codigoProducto);
                textnombre.setText(p.getNombre());
                textexist.setText(String.valueOf(p.getExistencias()));
                textprecio.setText(String.valueOf(p.getPrecio()));
                for (int i = 0; i < comboUnidad.getItemCount(); i++) {
                    if (comboUnidad.getItemAt(i).equals(p.getUnidad())) {
                        comboUnidad.setSelectedIndex(i);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(contentPanel, "Error al cargar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void botonGuardar() {
        String nombre = textnombre.getText().trim();
        String existencia = textexist.getText().trim();
        String precios = textprecio.getText().trim();
        String unidad = (String) comboUnidad.getSelectedItem();

        if (nombre.isEmpty() || existencia.isEmpty() || precios.isEmpty()) {
            JOptionPane.showMessageDialog(contentPanel, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double existencias;
        double precio;
        try {
            existencias = Double.parseDouble(existencia);
            precio = Double.parseDouble(precios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Existencias y precio deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ControladoraWallRose control = ControladoraWallRose.getInstance();
        try {
            if (codigoProducto == null) {
                control.crearProducto(nombre, existencias, unidad, precio);
                JOptionPane.showMessageDialog(contentPanel, "Producto agregado correctamente");
            } else {
                control.actualizarProducto(codigoProducto, nombre, existencias, unidad, precio);
                JOptionPane.showMessageDialog(contentPanel, "Producto actualizado correctamente");
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}