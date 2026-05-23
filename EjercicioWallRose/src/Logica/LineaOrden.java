package Logica;

import java.io.Serializable;

public class LineaOrden implements Serializable{
	private double cantidad;
	private Producto producto;
	public LineaOrden(Producto producto, double cantidad) {
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public double calcularCosto() {
		return cantidad * producto.getPrecio();
	}
}
