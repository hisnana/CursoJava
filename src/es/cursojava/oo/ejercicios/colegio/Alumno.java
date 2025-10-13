package es.cursojava.oo.ejercicios.colegio;

import java.util.Arrays;

public class Alumno {
	
	private String nombre;
	private String apellido;
	private String dni;
	private double notaMedia;
	private String[] asignaturas;
	private int edad;
	
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
	
    public Alumno(String nombre, int edad, double notaMedia) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
    }


    @Override
    public String toString() {
        return String.format("Alumno{nombre='%s', edad=%d, notaMedia=%.2f}", nombre, edad, notaMedia);
    }
	
	public Alumno(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}
	
	public Alumno(String nombre, String dni, double notaMedia) {
		
		this.nombre = nombre;
		this.dni = dni;
		this.notaMedia = notaMedia;
		
	}

	

	public Alumno(String nombre, String dni, double notaMedia, String[] asignaturas) {
		
		this.nombre = nombre;
		this.dni = dni;
		this.notaMedia = notaMedia;
		this.asignaturas = asignaturas;
	}



	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Alumno(String string) {
		// TODO Auto-generated constructor stub
	}




	public String getApellido() {
		return apellido;
	}

	public void setApellidos(String apellidos) {
		this.apellido = apellidos;
	}

	public Alumno(String nombre, String apellido, String dni, double notaMedia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.notaMedia = notaMedia;
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
