package es.cursojava.oo.ejercicios.bandamusica;

import org.slf4j.Logger;

public class Tambor extends Instrumento {
	private String material;

	public Tambor(String nombre, String tipo, boolean afinado, String material) {
		super(nombre, tipo, afinado);
		this.material = material;
	}
	
	public boolean afinar(Instrumento instrumento,Logger log) {
		boolean afinar = super.afinar(instrumento,log);
		afinar = true;
		return afinar;
	}
	
	public void aporrear(Logger log ) {
		log.info("Aporreando Tambor");
	}

}
