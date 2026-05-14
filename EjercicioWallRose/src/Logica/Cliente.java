package Logica;
 
import java.util.Map;
import java.util.TreeMap;
 
public class Cliente {
 
    private String id;
    private String nombre;
    private String email;
    private Map<Integer, Orden> ordenes;
 
    public Cliente(String id, String nombre, String email) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.ordenes = new TreeMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public Map<Integer, Orden> getOrdenes() {
		return ordenes;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void agregarOrden(Orden orden) {
		ordenes.put(orden.getNumero(), orden);
	}
	
	public void borrarOrden(Orden orden) {
		ordenes.remove(orden.getNumero());
	}
}