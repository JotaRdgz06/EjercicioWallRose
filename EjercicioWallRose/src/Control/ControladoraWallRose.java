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
    
    private ControladoraWallRose() {
        clientes = new TreeMap<>();
        ordenes = new TreeMap<>();
        productos = new TreeMap<>();
        consecutivoOrden = 1;
        consecutivoProducto = 1;
    }
    
    public static ControladoraWallRose getInstance() {
        if (instance == null) {
            instance = new ControladoraWallRose();
        }
        return instance;
    }
    
    public List<Cliente> obtenerListadoClientes() {
        return new LinkedList<>(clientes.values());
    }
    
    public Cliente obtenerCliente(String idCliente) throws Exception {
        if (!clientes.containsKey(idCliente))
            throw new Exception("No se encontró el cliente");
        return clientes.get(idCliente);
    }
    
    public List<Orden> obtenerListadoOrdenesCliente(String idCliente) throws Exception {
        if (!clientes.containsKey(idCliente))
            throw new Exception("No se encontró el cliente");
        Cliente cliente = clientes.get(idCliente);
        return new LinkedList<>(cliente.getOrdenes().values());
    }
 
    public List<Orden> obtenerListadoOrdenesIniciadasCliente(String idCliente) throws Exception {
        if (!clientes.containsKey(idCliente))
            throw new Exception("No se encontró el cliente");
        List<Orden> resultado = new LinkedList<>();
        Cliente cliente = clientes.get(idCliente);
        Map<Integer, Orden> ordenesCliente = cliente.getOrdenes();
        List<Orden> listaOrdenes = new LinkedList<>(ordenesCliente.values());
        for (Orden orden : listaOrdenes) {
            if (orden.getEstado() == EstadoOrden.INICIADA) {
                resultado.add(orden);
            }
        }
        return resultado;
    }

    public List<Orden> obtenerListadoOrdenesPendientesCliente(String idCliente) throws Exception {
    	if (!clientes.containsKey(idCliente))
            throw new Exception("No se encontró el cliente");
        List<Orden> resultado = new LinkedList<>();
        Cliente cliente = clientes.get(idCliente);
        Map<Integer, Orden> ordenesCliente = cliente.getOrdenes();
        List<Orden> listaOrdenes = new LinkedList<>(ordenesCliente.values());
        for (Orden orden : listaOrdenes) {
            if (orden.getEstado() == EstadoOrden.PENDIENTE) {
                resultado.add(orden);
            }
        }
        return resultado;
    }

    public List<Orden> obtenerListadoOrdenesTerminadasCliente(String idCliente) throws Exception {
    	if (!clientes.containsKey(idCliente))
            throw new Exception("No se encontró el cliente");
        List<Orden> resultado = new LinkedList<>();
        Cliente cliente = clientes.get(idCliente);
        Map<Integer, Orden> ordenesCliente = cliente.getOrdenes();
        List<Orden> listaOrdenes = new LinkedList<>(ordenesCliente.values());
        for (Orden orden : listaOrdenes) {
            if (orden.getEstado() == EstadoOrden.TERMINADA) {
                resultado.add(orden);
            }
        }
        return resultado;
    }
    
    public void crearCliente(String idCliente, String nombre, String email) throws Exception {
        if (clientes.containsKey(idCliente))
            throw new Exception("Ya existe un cliente con ese id");
        for (Cliente c : clientes.values()) {
            if (c.getEmail().equalsIgnoreCase(email))
                throw new Exception("Ya existe un cliente con ese email");
        }
        clientes.put(idCliente, new Cliente(idCliente, nombre, email));
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
    
    public Producto obtenerProducto(Integer codigoProducto) throws Exception {
        if (!productos.containsKey(codigoProducto))
            throw new Exception("No se encontró el producto");
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
    
    public void borrarProducto(Integer codigoProducto) throws Exception {
        if (!productos.containsKey(codigoProducto))
            throw new Exception("No se encontró el producto");
        productos.remove(codigoProducto);
    }
    
    public List<Orden> obtenerListadoOrdenes() {
        return new LinkedList<>(ordenes.values());
    }
    
    public Double obtenerMontoTotalPendiente() {
        double total = 0;
        for (Orden orden : ordenes.values()) {
            if (orden.getEstado() == EstadoOrden.PENDIENTE) {
                total += orden.calcularMontoTotal();
            }
        }
        return total;
    }
    
    public void crearOrdenVacia(String idCliente) throws Exception {
        if (!clientes.containsKey(idCliente))
            throw new Exception("Cliente no encontrado");
        Cliente cliente = clientes.get(idCliente);
        Orden orden = new Orden(consecutivoOrden, cliente);
        ordenes.put(consecutivoOrden, orden);
        cliente.agregarOrden(orden);
        consecutivoOrden++;
    }
    
    public Orden obtenerOrden(Integer numeroOrden) throws Exception {
    	if (ordenes.containsKey(numeroOrden))
    		return ordenes.get(numeroOrden);
    	else
    		throw new Exception("Numero de orden no encontrado");
    }
    
    public List<LineaOrden> obtenerLineasOrden(Integer numeroOrden) throws Exception {
        if (ordenes.containsKey(numeroOrden))
            return ordenes.get(numeroOrden).getLineas();
        else
        	throw new Exception("No existe una orden con ese número");
    }
    
    public void establecerOrdenPendiente(Integer numeroOrden) throws Exception {
        if (ordenes.containsKey(numeroOrden)) {
            Orden orden = ordenes.get(numeroOrden);
        	orden.setEstado(EstadoOrden.PENDIENTE);
        } else 
        	throw new Exception("No existe una orden con ese número");
    }
 
    public void establecerOrdenTerminada(Integer numeroOrden) throws Exception {
        if (ordenes.containsKey(numeroOrden)) {
        	Orden orden = ordenes.get(numeroOrden);
        	orden.setEstado(EstadoOrden.TERMINADA);
        } else
        	throw new Exception("No existe una orden con ese número");
    }
    
    public void agregarLineaOrden(Integer numeroOrden, Integer codigoProducto, Double cantidad) throws Exception {
        if (!ordenes.containsKey(numeroOrden))
            throw new Exception("No se encontró el número de orden");
        if (!productos.containsKey(codigoProducto))
            throw new Exception("No se encontró el código del producto");
        
        Orden orden = ordenes.get(numeroOrden);
        Producto producto = productos.get(codigoProducto);
        orden.agregarLinea(new LineaOrden(producto, cantidad));
    }
    
    public void actualizarLineaOrden(Integer numeroOrden, Integer numeroLinea, Integer codigoProducto, Double cantidad) throws Exception {
        if (!ordenes.containsKey(numeroOrden))
            throw new Exception("No se encontró la orden");
        if (!productos.containsKey(codigoProducto))
            throw new Exception("No se encontró el producto");
        
        Orden orden = ordenes.get(numeroOrden);
        Producto producto = productos.get(codigoProducto);
        List<LineaOrden> lineas = orden.getLineas();
        LineaOrden linea = lineas.get(numeroLinea);
        linea.setProducto(producto);
        linea.setCantidad(cantidad);
    }
    
    public void borrarLineaOrden(Integer numeroOrden, Integer numeroLinea) throws Exception {
    	if (!ordenes.containsKey(numeroLinea))
    		throw new Exception("No se encontró la línea");
    	if (!ordenes.containsKey(numeroOrden))
    		throw new Exception("No se encontró la orden");
    	
    	Orden orden = ordenes.get(numeroOrden);
        orden.borrarLinea(numeroLinea);
    }
    
    public void borrarOrden(Integer numeroOrden) throws Exception {
    	if (!ordenes.containsKey(numeroOrden))
    		throw new Exception("No se encontró la orden");
    	
    	Orden orden = ordenes.get(numeroOrden);
    	Cliente cliente = orden.getCliente();
    	cliente.borrarOrden(orden);
    	ordenes.remove(numeroOrden);
    }
    
    public Integer obtenerSiguienteCodigoProducto() {
        return consecutivoProducto;
    }
}
