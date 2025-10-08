package es.cursojava.oo.ejercicios.colecciones;


public class Persona {
	//Variables de instancia
	private String nombre;
	private String apellidos;
	private String dni;
	private int edad;
	
	//Constructores
	public Persona (){
		
	}
	
	public Persona(String nombre, String apellidos, 
			String dni, int edad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
	}


//	
	public Persona (String dni){
		this.dni = dni;
	}
	
	//Método de instancia NO ESTÁTICO
	public void correr() {
		System.out.print (nombre + " está corriendo");
		if (edad<70) {
			System.out.println(" rápido");
		}else {
			System.out.println(" despacio");
		}
	}
	
	//GETTER Y SETTER
	public String getNombre () {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

//	public void setDni(String dni) {
//		this.dni = dni;
//	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
}










