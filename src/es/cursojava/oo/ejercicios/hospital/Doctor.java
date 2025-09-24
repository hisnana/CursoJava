package es.cursojava.oo.ejercicios.hospital;

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
	
	public Enfermo diagnosticarPaciente(Persona paciente) {
		boolean enfermedad = Math.random()>0.8;
		Enfermo enfermo = new Enfermo();
		if(enfermedad) {
			//Se crea un enfermo con datos si hay habitaciones libres
			enfermo = new Enfermo(paciente.getNombre(),paciente.getEdad(),"Alergia al lunes");
			
		} else {
			enfermo = new Enfermo();
		}
		
		return enfermo;
	}

}
