package es.cursojava.oo.ejercicios;

public class Producto {
	private String nombre;
	private double precio;
	private int cantidad;
	
	public Producto(String nombre, double precio, int cantidad) {
	
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	

	public void mostrarInfo() {
		System.out.println(nombre);
		System.out.println(precio);
		System.out.println(cantidad);
		
	}
	
	public double calcularValorTotal() {
		double total = precio * cantidad;
		return total;
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
