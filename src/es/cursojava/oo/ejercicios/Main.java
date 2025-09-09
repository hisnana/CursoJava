package es.cursojava.oo.ejercicios;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Direccion direccion1 = new Direccion("Calle de la Biblio 1","Madrid",28915);
		Biblioteca biblioteca1 = new Biblioteca("Biblioteca1",direccion1,3);
		
		Libro libro1 = new Libro("Libro1","Autor1","JHG5676FI7F");
		Libro libro2 = new Libro("Libro2","Autor2","KJGH765F7IVGGB");
		Libro libro3 = new Libro("Libro3","Autor3","HB8GT78GGB");
		
		biblioteca1.agregarLibro(libro1);
		biblioteca1.agregarLibro(libro2);
		biblioteca1.agregarLibro(libro3);
		biblioteca1.mostrarInfoBiblioteca();
		biblioteca1.mostrarLibros();
		
	}

}
