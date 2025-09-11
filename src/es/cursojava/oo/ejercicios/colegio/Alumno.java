package es.cursojava.oo.ejercicios.colegio;

import java.util.Arrays;

public class Alumno {
	
	private String nombre;
	private String dni;
	private double notaMedia;
	private String[] asignaturas;
	
	public void estudiar(){
		System.out.println("El estudiante "+nombre+" tiene las asignaturas: "+Arrays.toString(asignaturas));
		if(notaMedia < 5) {
			System.out.println("Ha estudiado poco");
		} else if (notaMedia > 5 && notaMedia < 9) {
			System.out.println("Ha estudiado mucho");
		}else if (notaMedia > 9 ) {
			System.out.println("Es un genio");
		}
	}
	
	
	
	public Alumno(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}
	

	public Alumno(String nombre, String dni, double notaMedia, String[] asignaturas) {
		
		this.nombre = nombre;
		this.dni = dni;
		this.notaMedia = notaMedia;
		this.asignaturas = asignaturas;
	}



	public String getNombre() {
		return nombre;
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}

	public String[] getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(String[] asignaturas) {
		this.asignaturas = asignaturas;
	}

}
