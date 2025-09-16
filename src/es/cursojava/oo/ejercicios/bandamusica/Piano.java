package es.cursojava.oo.ejercicios.bandamusica;

import org.slf4j.Logger;

public class Piano extends Instrumento {
	
	private int numeroOctavas;
	private String tipoPiano;
	public Piano(String nombre, String tipo, boolean afinado, int numeroOctavas, String tipoPiano) {
		super(nombre, tipo, afinado);
		this.numeroOctavas = numeroOctavas;
		this.tipoPiano = tipoPiano;
	}
	
	public boolean afinar(Instrumento instrumento,Logger log) {
		boolean afinar = super.afinar(instrumento,log);
		log.info("Afinando Piano");
		return afinar;
	}

}
