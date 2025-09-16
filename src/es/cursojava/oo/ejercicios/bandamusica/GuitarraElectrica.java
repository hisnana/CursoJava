package es.cursojava.oo.ejercicios.bandamusica;

import org.slf4j.Logger;

public class GuitarraElectrica extends Guitarra {
	private int potencia;
	

	public GuitarraElectrica(String nombre, String tipo, boolean afinado, int numCuerdas, int potencia) {
		super(nombre, tipo, afinado, numCuerdas);
		this.potencia = potencia;
	}


	public void tocar(Logger log) {
		log.info("Tocando muy alto");
	}

}
