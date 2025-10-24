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
		
		List<Libro> libros = new ArrayList<>();
		libros.add(new Libro("Effective Java", "978-0134685991", 2017));
		libros.add(new Libro("Clean Code", "978-0132350884", 2008));
		libros.add(new Libro("Head First Design Patterns", "978-0596007126", 2004));
		libros.add(new Libro("Java Concurrency in Practice", "978-0321349606", 2006));
		libros.add(new Libro("Refactoring", "978-0201485677", 1999));
		libros.add(new Libro("Test Driven Development", "978-0321146533", 2002));
		libros.add(new Libro("Domain-Driven Design", "978-0321125217", 2003));
		libros.add(new Libro("Effective Unit Testing", "978-1935182573", 2013));
		libros.add(new Libro("Clean Architecture", "978-0134494166", 2017));
		
		
		Autor autor1= new Autor("Pepe","Español");
		Autor autor2= new Autor("Haku","Japonesa");
		Autor autor3= new Autor("Louise","Francés");
		
		biblioteca1.put(autor1, libros.subList(0, 3));
		biblioteca1.put(autor2, libros.subList(3, 5));
		biblioteca1.put(autor3, libros.subList(5, 9));
		
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
        do {
            GestionBiblioteca.crearLibro(biblioteca1);
        } while (Utilidades.pedirConfirmacion("¿Crear otro libro?"));

        // --- Flujo de BORRADO: al menos una vez, y repetir si confirmas ---
        do {
            GestionBiblioteca.borrarPorIsbn(biblioteca1);
        } while (Utilidades.pedirConfirmacion("¿Borrar otro ISBN?"));

        // --- Resumen final ---
        GestionBiblioteca.imprimirBiblioteca(biblioteca1);
        MiLogger.info("Fin del programa.");
    }


   
	
}
