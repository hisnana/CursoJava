package es.cursojava.oo.ejercicios.biblioteca;

public class Direccion {
	private String calle;
	private String ciudad;
	private int codigoPostal;
	public Direccion(String calle, String ciudad, int codigoPostal) {
		
		this.calle = calle;
		this.ciudad = ciudad;
		this.codigoPostal = codigoPostal;
	}
	
	public void mostrarDireccion() {
		System.out.println(calle );
		System.out.println(ciudad);
		System.out.println(codigoPostal);
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	

}
