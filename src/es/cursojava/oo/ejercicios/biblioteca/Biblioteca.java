package es.cursojava.oo.ejercicios.biblioteca;

public class Biblioteca {
	
	private String nombre;
	private Direccion direccion;
	private Libro[] libros;
	private int contadorLibros;
	
	public void agregarLibro(Libro libro) {
		if (contadorLibros<libros.length) {
			libros[contadorLibros]= libro ;
			contadorLibros++;
			
		} else {
			System.out.println("La Biblioteca "+nombre+" esta llena");
		}
		
	}
	
	public void mostrarLibros() {
		if(libros!=null) {
			for(Libro libro:libros) {
				libro.mostrarInfo();
			}
			
		}
		
	}
	
	public void mostrarInfoBiblioteca() {
		System.out.println(nombre);
		direccion.mostrarDireccion();;
		for(Libro libro:libros) {
			libro.mostrarInfo();
		}
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Libro[] getLibros() {
		return libros;
	}

	public void setLibros(Libro[] libros) {
		this.libros = libros;
	}

	public Biblioteca(String nombre, Direccion direccion, int numLibros) {
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.libros = new Libro[numLibros] ;
	}

	public Biblioteca() {
		
	}
	
}
