package es.cursojava.oo.ejercicios.colecciones.biblioteca;

public class Libro {
	private String titulo;
	private String isbn;
	private int anio;
	private Autor autor;
	
	
	public Libro(String titulo, String isbn, int anio) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.anio = anio;
	}

	

	public Libro(String titulo, String isbn, int anio, Autor autor) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.anio = anio;
		this.autor = autor;
	}



	public Autor getAutor() {
		return autor;
	}



	public void setAutor(Autor autor) {
		this.autor = autor;
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
