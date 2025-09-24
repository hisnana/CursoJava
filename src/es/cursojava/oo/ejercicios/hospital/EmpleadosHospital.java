package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmpleadosHospital extends Persona {
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
	
	private void comer(Logger log,EmpleadosHospital empleado) {
		log.info("El empleado "+empleado.getNombre()+" esta comiendo.");
	}
	
	
	

}
