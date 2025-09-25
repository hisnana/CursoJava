package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;

public abstract class EmpleadosHospital extends Persona {
	private String turno;

	public EmpleadosHospital(String nombre, int edad, String turno) {
		super(nombre, edad);
		this.turno = turno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public void fichar (Logger log,Persona persona) {
		
		log.info("El empleado "+persona.getNombre()+" esta fichando.");
		
	}
	
	@Override
	public void comer (Logger log,Persona persona) {
		log.info("El empleado "+persona.getNombre()+" esta comiendo.");
	}
	
	public abstract void comer();
	

}
