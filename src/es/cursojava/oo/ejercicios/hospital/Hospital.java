package es.cursojava.oo.ejercicios.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Hospital {
	private String nombre;
	private Habitacion[] habitaciones ;
	private Paciente[] salaDeEspera = new Paciente[3];
	private static final Logger log = LoggerFactory.getLogger("Hospital");
	
	public static void main(String [] args) {
		Hospital hospital = new Hospital();
		Persona[] personas =hospital.abrirHospital();
		for (Persona persona : personas) {
			hospital.ficharEmpleados(persona);
			hospital.pasarConsulta(persona);
			hospital.horaDeComer(persona);
			
		}
		

		
	}
	
	public Persona[] abrirHospital() {
		Habitacion habitacion1 = new Habitacion(201);
		Habitacion habitacion2 = new Habitacion(202);
		habitaciones = new Habitacion[]{habitacion1,habitacion2};
		String[] sintomasPaciente1 = {"Dolor de cabeza"};
		String[] sintomasPaciente2 = {"Dolor de cabeza","Diarrea"};
		String[] sintomasPaciente3 = {"Tos"};
		Paciente paciente1 = new Paciente("Paciente1",66,sintomasPaciente1);
		Paciente paciente2 = new Paciente("Paciente2",45,sintomasPaciente2);
		Paciente paciente3 = new Paciente("Paciente3",51,sintomasPaciente3);
		salaDeEspera[0] = paciente1;
		salaDeEspera[1] = paciente2;
		salaDeEspera[2] = paciente3;
		Enfermero enfermero1 = new Enfermero("Enfermero1",32,"Tarde",2);
		Doctor doctor1 = new Doctor("Doctor1",45,"Mañana","Psiquiatra");
		Persona[] personas = {paciente1,paciente2,paciente3,enfermero1,doctor1};
		
		return personas;
	}
	
	public void ficharEmpleados(Persona persona) {
		
		if (persona instanceof EmpleadosHospital) {
			((EmpleadosHospital)persona).fichar(log,persona);
			
		}
		
	}
	
	public void horaDeComer (Persona persona) {
		if (persona instanceof EmpleadosHospital) {
			((EmpleadosHospital)persona).comer(log,persona);
		}
		if (persona instanceof Paciente) {
			((Paciente)persona).comer(log,persona);
		}
		
		
	}

	
	public void pasarConsulta (Persona persona) {
		
		if (persona instanceof Enfermero) {
			for (Paciente paciente : salaDeEspera) {
				((Enfermero)persona).atenderPaciente(log,paciente);
			}
			
		}
		
		if (persona instanceof Doctor) {
			for (int i = 0 ; i < salaDeEspera.length ; i++) {
				if (salaDeEspera[i]!=null) {
					
					boolean enfermo =((Doctor) persona).diagnosticarPaciente(log,salaDeEspera[i]);
					Enfermo[] enfermos = new Enfermo[salaDeEspera.length];
					if (enfermo) {
						enfermos[i] = new Enfermo(persona.getNombre(),persona.getEdad(),"Alergia al lunes");
						int contadorHabitacionesLlenas = 0;
						for (Habitacion habitacion : habitaciones) {
							if (contadorHabitacionesLlenas==habitaciones.length) {
								log.info("Todas las habitaciones están completas, busque otro hospital.");
								continue;
							}
							if (habitacion.getEnfermo()==null) {
								habitacion.setEnfermo(enfermos[i]);
							} else {
								contadorHabitacionesLlenas++;
							}

						}
					}
					
				}
			}
			
			
		}

	}

}
