package es.cursojava.oo.ejercicios;

public class Boton {
	private String color;
	private String tamanio;
	private String forma;
	
	
	public Boton(String color, String tamanio, String forma) {
		
		this.color = color;
		this.tamanio = tamanio;
		this.forma = forma;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public void mostrarInfo() {
		System.out.println("El boton es de color: "+color);
		System.out.println("El boton es de tamaño: "+tamanio);
		System.out.println("El boton tiene la forma: "+forma);
	}
	

}
