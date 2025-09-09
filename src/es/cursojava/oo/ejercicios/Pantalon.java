package es.cursojava.oo.ejercicios;

public class Pantalon {
	private String color;
	private double precio;
	private String talla;
	private Boton boton;
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
	public Boton getBoton() {
		return boton;
	}
	public void setBoton(Boton boton) {
		this.boton = boton;
	}
	public Pantalon(String color, double precio, String talla, Boton boton) {
		
		this.color = color;
		this.precio = precio;
		this.talla = talla;
		this.boton = boton;
	}
	 public void mostrarInfo() {
		 System.out.println("El pantalón tiene el color: "+color);
		 System.out.println("El pantalón vale: "+precio);
		 System.out.println("El pantalón tiene la talla "+talla);
		 System.out.println("El pantalón tiene boton: ");
		 boton.mostrarInfo();
	 }
	

}
