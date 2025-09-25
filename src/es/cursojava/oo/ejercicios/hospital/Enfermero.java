package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;

public class Enfermero  extends EmpleadosHospital {
	
	private int planta;

	public void atenderPaciente(Logger log,Paciente paciente) {
		
		log.info("El enfermero esta atendiendo al paciente "+paciente.getNombre());
		
		
	}

	public Enfermero(String nombre, int edad, String especialidad, int planta) {
		super(nombre, edad, especialidad);
		this.planta = planta;
	}

	public int getPlanta() {
		return planta;
	}

	public void setPlanta(int planta) {
		this.planta = planta;
	}
	
	public void comer() {
		
	}

}
