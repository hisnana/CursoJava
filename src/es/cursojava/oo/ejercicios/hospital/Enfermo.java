package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;

public class Enfermo extends Persona {
	private String enfermedad;
	
	private void comer(Logger log,Enfermo enfermo) {
		log.info("El enfermo "+enfermo.getNombre()+" esta comiendo.");
	}
	
	public Enfermo() {
		
	}

	public Enfermo(String nombre, int edad, String enfermedad) {
		super(nombre, edad);
		this.enfermedad = enfermedad;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}


}
