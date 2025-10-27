package es.cursojava.oo.ejercicios.colecciones.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;

public class GestionBiblioteca {
	
	
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
	
    public static void imprimirBiblioteca(Map<Autor, List<Libro>> biblioteca) {
        MiLogger.info("\n=== Biblioteca (resumen) ===");
        for (Map.Entry<Autor, List<Libro>> e : biblioteca.entrySet()) {
            Autor a = e.getKey(); //Guarda las claves
            List<Libro> ls = e.getValue();//Guarda los valores
            MiLogger.info(a.getNombre() + " [" + a.getNacionalidad() + "] → " + ls.size() + " libros");
            for (Libro l : ls) {
                MiLogger.info("   - " + l.getTitulo() + " (" + l.getAnio() + ") #" + l.getIsbn());
            }
        }
    }
    
    public static void borrarPorIsbn(Map<Autor, List<Libro>> biblioteca) {
        MiLogger.info("\n=== Borrado por ISBN ===");
        String isbnABorrar = Utilidades.pedirDato("Indica ISBN a eliminar: ");

        int totalEliminados = 0;

        for (Map.Entry<Autor, List<Libro>> e : biblioteca.entrySet()) {
            List<Libro> lista = e.getValue();
            Iterator<Libro> it = lista.iterator();//Iterator es un cursor para recorrer colecciones elemento a elemento
            while (it.hasNext()) {//hasNext() comprueba si hay mas elementos en la lista para que no de un index out bound
                Libro l = it.next();
                if (l.getIsbn().equalsIgnoreCase(isbnABorrar)) {
                    it.remove();           // eliminación segura sin ConcurrentModificationException
                    totalEliminados++;
                }
            }
        }

        if (totalEliminados > 0) {
            MiLogger.info("Eliminado ISBN " + isbnABorrar + " (" + totalEliminados + " ocurrencia(s)).");
        } else {
            MiLogger.info("No se encontró ningún libro con ISBN " + isbnABorrar + ".");
        }
    }
    
    // Pide datos con Utilidades.pedirDato/Entero y agrega el libro (crea autor si no existe)
    public static void crearLibro(Map<Autor, List<Libro>> biblioteca) {
        MiLogger.info("\n=== Alta de libro ===");
        String nombreAutor = Utilidades.pedirDato("Autor (nombre): ");
        Autor autor = buscarAutorPorNombre(biblioteca, nombreAutor);

        if (autor == null) {
            String nac = Utilidades.pedirDato("Nacionalidad del autor nuevo: ");
            autor = new Autor(nombreAutor, nac);
            biblioteca.put(autor, new ArrayList<>());
        }

        String titulo = Utilidades.pedirDato("Título: ");
        String isbn   = Utilidades.pedirDato("ISBN: ");
        int anio      = Utilidades.pedirEntero("Año de publicación: ");

        List<Libro> lista = biblioteca.get(autor);

        boolean yaExiste = false;
        for (int i = 0; i < lista.size(); i++) {
            Libro li = lista.get(i);
            if (li.getIsbn().equalsIgnoreCase(isbn)) {
                yaExiste = true;
                break;
            }
        }

        if (yaExiste) {
            MiLogger.info("No se agregó. Ya existe un libro con ISBN " + isbn + " para " + autor.getNombre());
        } else {
            lista.add(new Libro(titulo, isbn, anio));
            MiLogger.info("Agregado: \"" + titulo + "\" a " + autor.getNombre());
        }
    }
    
    // Buscar autor por nombre (case-insensitive)
    public static Autor buscarAutorPorNombre(Map<Autor, List<Libro>> biblioteca, String nombre) {
        for (Autor a : biblioteca.keySet()) {
            if (a.getNombre().equalsIgnoreCase(nombre)) return a;
        }
        return null;
    }

}
