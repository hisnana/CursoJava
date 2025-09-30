package es.cursojava.oo.ejercicios.productos;

import java.util.UUID;

public abstract class Producto {
	
	private String id;
	
	public Producto() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
