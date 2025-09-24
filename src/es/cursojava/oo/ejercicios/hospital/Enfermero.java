package es.cursojava.oo.ejercicios.hospital;

public class Enfermero  extends EmpleadosHospital {
	
	private int planta;

	private Paciente atenderPaciente(Paciente paciente) {
		
		
		return paciente;
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
	
	

}
