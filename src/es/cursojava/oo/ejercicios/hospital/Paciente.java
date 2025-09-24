package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;

public class Paciente extends Persona{
	private String[] sintomas;
	
	private void comer(Logger log,Persona persona) {
		log.info("El paciente "+persona.getNombre()+" esta comiendo.");
	}
	


	public Paciente(String nombre, int edad, String[] sintomas) {
		super(nombre, edad);
		this.sintomas = sintomas;
	}

	public String[] getSintomas() {
		return sintomas;
	}

	public void setSintomas(String[] sintomas) {
		this.sintomas = sintomas;
	}

	
}
