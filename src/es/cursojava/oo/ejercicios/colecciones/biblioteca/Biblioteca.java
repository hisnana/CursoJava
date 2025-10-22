package es.cursojava.oo.ejercicios.colecciones.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.cursojava.utils.MiLogger;

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

		mostrarNacionalidad(biblioteca1);
		titulos2010(biblioteca1);
		masDeDos(biblioteca1);
    
	}
	
	public static void mostrarNacionalidad(Map<Autor,List<Libro>> biblioteca) {
        // A) Mostrar las nacionalidades de los autores
        MiLogger.info("=== Nacionalidades de autores ===");
        for (Autor autor : biblioteca.keySet()) {
            MiLogger.info("- Autor "+autor.getNombre()+" su nacionalidad es " + autor.getNacionalidad());
        }
	}
	
	public static void titulos2010 (Map<Autor,List<Libro>> biblioteca) {
	     // 2) Mostrar el título de todos los libros publicados después de 2010
		int UMBRAL = 2010;
		MiLogger.info("=====LISTA DE LIBROS PUBLICADOS DESPUÉS DEL 2010======");
		for (Map.Entry<Autor, List<Libro>> e : biblioteca.entrySet()) {
		    Autor autor = e.getKey();
		    List<Libro> lista = e.getValue();

		    System.out.println(autor.getNombre() + " (" + autor.getNacionalidad() + "):");

		    int encontrados = 0;
		    for (Libro l : lista) {
		        if (l.getAnio() > UMBRAL) {
		            System.out.println("   * " + l.getTitulo() + " — " + l.getAnio());
		            encontrados++;
		        }
		    }
		    if (encontrados == 0) {
		        System.out.println("   (ningún libro anterior a " + UMBRAL + ")");
		    }
		}
	}
	
public static void masDeDos (Map<Autor,List<Libro>> biblioteca ) {
	
    // 3) Por cada autor con más de dos libros: su nombre y los títulos
    MiLogger.info("\n=== AUTORTES CON MAS DE DOS LIBROS PUBLICADOS Y SUS LIBROS PUBLICADOS ===");
    for (Map.Entry<Autor, List<Libro>> e : biblioteca.entrySet()) {
        Autor a = e.getKey();
        List<Libro> lista = e.getValue();
        if (lista.size() > 2) {
            MiLogger.info(a.getNombre() + " (" + lista.size() + " libros):");
            for (Libro l : lista) {
                MiLogger.info("   * " + l.getTitulo());
            }
        }
    }
	
}
	
}
