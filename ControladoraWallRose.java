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
}
