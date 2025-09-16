package es.cursojava.oo.ejercicios.bandamusica;

import org.slf4j.Logger;

public class Guitarra extends Instrumento {
	private int numCuerdas;
	
	public Guitarra(String nombre, String tipo, boolean afinado,int numCuerdas) {
		super(nombre,tipo,afinado);
		this.numCuerdas = numCuerdas;
	}
	
	public boolean afinar (Instrumento instrumento,Logger log) {
		boolean afinar=super.afinar(instrumento,log);
		log.info("Afinando guitarra");
		
		return afinar;
	}





}
