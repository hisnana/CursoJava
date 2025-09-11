package es.cursojava.oo.ejercicios.tiendaManiqui;

public class Vestido {
	private String color;
	private double precio;
	private String talla;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public Vestido(String color, double precio, String talla) {
		
		this.color = color;
		this.precio = precio;
		this.talla = talla;
	}
	
	public void mostrarInfo() {
		System.out.println("Es un vestido de color: "+color);
		System.out.println("El vestido vale: "+precio);
		System.out.println("La talla talla del vestido es: "+talla);
	}
	
	

}
