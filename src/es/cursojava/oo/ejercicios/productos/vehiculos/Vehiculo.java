package es.cursojava.oo.ejercicios.productos.vehiculos;

import java.util.UUID;

public abstract class Vehiculo {
	
	private String id;
	private int numRuedas;
	
	public Vehiculo() {
		this.id = UUID.randomUUID().toString();
	}

}
