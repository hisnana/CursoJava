package es.cursojava.oo.ejercicios.colecciones.biblioteca;

public class Libro {
	private String titulo;
	private String isbn;
	private int anio;
	
	
	public Libro(String titulo, String isbn, int anio) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.anio = anio;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getAnio() {
		return anio;
	}


	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	
	
}
