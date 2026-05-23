package Logica;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Orden implements Serializable{
	private Integer numero;
	private LocalDateTime fecha;
	private final static Double IV = 0.13;
	private List<LineaOrden> lineas;
	private Cliente cliente;
	private EstadoOrden estado;
	
	public Orden(Integer numero, Cliente cliente) {
		super();
		this.numero = numero;
		this.fecha = LocalDateTime.now();
		this.lineas = new LinkedList<>();
		this.cliente = cliente;
		this.estado = EstadoOrden.INICIADA;
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public Integer getNumero() {
		return numero;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public List<LineaOrden> getLineas() {
		return lineas;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public double calcularMonto() {
		double total = 0;
		for (LineaOrden linea : lineas) {
			total += linea.calcularCosto();
		}
		return total;
	}
	
	public double calcularMontoImpuesto() {
		return calcularMonto() * IV;
	}
	
	public double calcularMontoTotal() {
		return calcularMonto() + calcularMontoImpuesto();
	}
	
	public void agregarLinea(LineaOrden linea) {
		lineas.add(linea);
	}
	
	public void borrarLinea(Integer numeroLinea) {
		lineas.remove((int)numeroLinea);
	}
}
