package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;

public class Doctor extends EmpleadosHospital {
	
	private String especialidad;

	public Doctor(String nombre, int edad, String turno, String especialidad) {
		super(nombre, edad, turno);
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public boolean diagnosticarPaciente(Logger log,Paciente paciente) {
		boolean enfermedad = Math.random()>0.8;
		boolean enfermo = false;
		if(enfermedad) {
			log.info("El paciente "+paciente.getNombre()+" esta enfermo.");
			enfermo = true;
			
		} else {
			log.info("El paciente "+paciente.getNombre()+" NO esta enfermo.");
		}
		return enfermo;
	}
	
	public void comer() {
		
	}

}
