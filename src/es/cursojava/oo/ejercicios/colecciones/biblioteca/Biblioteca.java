package es.cursojava.oo.ejercicios.colecciones.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;

public class Biblioteca {
	public static void main(String[] args) {
		
		Map<Autor,List<Libro>> biblioteca1 = new HashMap();
		

		// --- Autores ---
		Autor autor1 = new Autor("Pepe", "Español");
		Autor autor2 = new Autor("Haku", "Japonesa");
		Autor autor3 = new Autor("Louise", "Francés");

		// --- Libros (creados primero) ---
		Libro l1 = new Libro("Effective Java",               "A1", 2017);
		Libro l2 = new Libro("Clean Code",                   "A2", 2008);
		Libro l3 = new Libro("Head First Design Patterns",   "A3", 2004);
		Libro l4 = new Libro("Java Concurrency in Practice", "A4", 2006);
		Libro l5 = new Libro("Refactoring",                  "A5", 1999);
		Libro l6 = new Libro("Test Driven Development",      "B1", 2002);
		Libro l7 = new Libro("Domain-Driven Design",         "B2", 2003);
		Libro l8 = new Libro("Effective Unit Testing",       "B3", 2013);
		Libro l9 = new Libro("Clean Architecture",           "B4", 2017);

		// --- Listas por autor (3 libros cada uno) ---
		List<Libro> librosPepe   = new ArrayList<>();
		librosPepe.add(l1);
		librosPepe.add(l2);
		librosPepe.add(l3);

		List<Libro> librosHaku   = new ArrayList<>();
		librosHaku.add(l4);
		librosHaku.add(l5);
		librosHaku.add(l6);

		List<Libro> librosLouise = new ArrayList<>();
		librosLouise.add(l7);
		librosLouise.add(l8);
		librosLouise.add(l9);

		// --- Cargar en el mapa ---
		biblioteca1.put(autor1, librosPepe);
		biblioteca1.put(autor2, librosHaku);
		biblioteca1.put(autor3, librosLouise);
		
        // ===== ejercicios =====
		//mostrar las nacionalidades de los autores
		GestionBiblioteca.mostrarNacionalidad(biblioteca1);
		//mostrar el título de todos los libros publicados después del 2010
		GestionBiblioteca.titulos2010(biblioteca1);
		//mostrar por cada autor que tenga más de dos libros su nombre y el nombre de todos los libros que tiene publicados
		GestionBiblioteca.masDeDos(biblioteca1);
		//+ Solicitar los datos de un libro, autor, titulo, isbn y año de publicación, 
//		- si el autor no está en la biblioteca, solicitar la nacionalidad del autor y meterlo en la biblioteca, 
//		- si el autor sí está ya, agregar el libro a su listado
//		
//	+ Solicitar un isbn, si algún libro tiene ese isbn hay que eliminarlo
		

        // --- Flujo de ALTA: al menos una vez, y repetir si confirmas ---
		if(Utilidades.pedirConfirmacion("¿Quieres crear un libro?")){
	        do {
	            GestionBiblioteca.crearLibro(biblioteca1);
	        } while (Utilidades.pedirConfirmacion("¿Crear otro libro?"));
		}


        // --- Flujo de BORRADO: al menos una vez, y repetir si confirmas ---
		
		if (Utilidades.pedirConfirmacion("¿Quieres borrar un libro?")) {
	        do {
	            GestionBiblioteca.borrarPorIsbn(biblioteca1);
	        } while (Utilidades.pedirConfirmacion("¿Borrar otro ISBN?"));
		}


        // --- Resumen final ---
        GestionBiblioteca.imprimirBiblioteca(biblioteca1);
        MiLogger.info("Fin del programa.");
    }


   
	
}
