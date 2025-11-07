package es.cursojava.oo.ejercicios.colegio;

import java.util.Arrays;

import es.cursojava.ini.excepciones.NotaInvalidaException;


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
	
	

	public Alumno(String nombre, String apellido, String dni, double notaMedia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.notaMedia = notaMedia;
	}

	public Alumno(String nombre, String apellido, String dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public Alumno(String nombre, String apellido, String dni, double notaMedia, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.notaMedia = notaMedia;
		this.edad = edad;
	}

	public Alumno(String nombre, int edad, double notaMedia) throws IllegalArgumentException,NotaInvalidaException  {
        this.nombre = nombre;
        if (edad <= 0 || edad >110) {
	        throw new IllegalArgumentException("El valor debe ser > 0 y < 110");
	    } 
	    this.edad = edad;
	    
        if (notaMedia < 0.0 || notaMedia > 10.0) {
	        throw new NotaInvalidaException("El valor debe ser entre 0 y 10");
	    } 
	    this.notaMedia= notaMedia;
	    
        
    }


    @Override
    public String toString() {
        return String.format("Alumno{nombre='%s', edad=%d, notaMedia=%.2f}", nombre, edad, notaMedia);
    }


	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}



	public String getApellido() {
		return apellido;
	}

	public void setApellidos(String apellidos) {
		this.apellido = apellidos;
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
