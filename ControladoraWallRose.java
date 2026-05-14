package Control;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Logica.*;

public class ControladoraWallRose {
	
	private static ControladoraWallRose instance;
	
	private Map<String, Cliente> clientes;
    private Map<Integer, Orden> ordenes;
    private Map<Integer, Producto> productos;
    private Integer consecutivoOrden;
    private Integer consecutivoProducto;
    
    public static ControladoraWallRose getInstance() {
        if (instance == null) {
            instance = new ControladoraWallRose();
        }
        return instance;
    }
    
    public List<Cliente> obtenerListadoClientes() {
        return new LinkedList<>(clientes.values());
    }
    
    public Cliente obtenerCliente(String idCliente) {
        return clientes.get(idCliente);
    }
    
    public List<Orden> obtenerListadoOrdenesCliente(String idCliente) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente == null) return new LinkedList<>();
        return new LinkedList<>(cliente.getOrdenes().values());
    }
 
    public List<Orden> obtenerListadoOrdenesIniciadasCliente(String idCliente) {
        List<Orden> resultado = new LinkedList<>();
        Cliente cliente = clientes.get(idCliente);
        for (Orden orden : cliente.getOrdenes().values()) {
            if (orden.getEstado() == EstadoOrden.INICIADA) {
                resultado.add(orden);
            } else {
            	System.out.println("");
            }
        }
        return resultado;
    }

    public List<Orden> obtenerListadoOrdenesPendientesCliente(String idCliente) {
        List<Orden> resultado = new LinkedList<>();
        Cliente cliente = clientes.get(idCliente);
        for (Orden orden : cliente.getOrdenes().values()) {
            if (orden.getEstado() == EstadoOrden.PENDIENTE) {
                resultado.add(orden);
            } else {
            	System.out.println("");
            }
        }
        return resultado;
    }

    public List<Orden> obtenerListadoOrdenesTerminadasCliente(String idCliente) {
        List<Orden> resultado = new LinkedList<>();
        Cliente cliente = clientes.get(idCliente);
        for (Orden orden : cliente.getOrdenes().values()) {
            if (orden.getEstado() == EstadoOrden.TERMINADA) {
                resultado.add(orden);
            } else {
            	System.out.println("");
            }
        }
        return resultado;
    }
    
    public void crearCliente(String idCliente, String nombre, String email) {
    	Cliente cliente = new Cliente(idCliente, nombre, email);
    	clientes.put(idCliente, cliente);
    }
    
    public void actualizarCliente(String idCliente, String nombre, String email) throws Exception {
        if (clientes.containsKey(idCliente)) {
            Cliente cliente = clientes.get(idCliente);
            cliente.setNombre(nombre);
            cliente.setEmail(email);
        } else {
        	throw new Exception("No se encontró el usuario");
        }
    }
    
    public void borrarCliente(String idCliente) throws Exception {
    	if (clientes.containsKey(idCliente)) {
    		clientes.remove(idCliente);
    	} else {
    		throw new Exception("No se encontró el usuario");
    	}
    }
    
    public List<Producto> obtenerListadoProductos() {
        return new LinkedList<>(productos.values());
    }
    
    public void crearProducto(String nombre, double existencias, String unidad, double precio) {
    	Producto producto = new Producto(consecutivoProducto, nombre, existencias, unidad, precio);
    	productos.put(consecutivoProducto, producto);
    	consecutivoProducto++;
    }
    
    public Producto obtenerProducto(Integer codigoProducto) {
    	return productos.get(codigoProducto);
    }
    
    public void actualizarProducto(Integer codigoProducto, String nombre, double existencias, String unidad, double precio) throws Exception {
    	if (productos.containsKey(codigoProducto)) {
    		Producto producto = productos.get(codigoProducto);
    		producto.setNombre(nombre);
    		producto.setExistencias(existencias);
    		producto.setUnidad(unidad);
    		producto.setPrecio(precio);
    	} else {
    		throw new Exception("No se encontró el producto");
    	}
    }
    
    public void borrarProducto(Integer codigoProducto) {
    	if (productos.containsKey(codigoProducto)) {
    		productos.remove(codigoProducto);
    	}
    }
}
