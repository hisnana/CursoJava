package es.cursojava.oo.ejercicios.mapas;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import es.cursojava.oo.ejercicios.colecciones.Persona;
import es.cursojava.oo.ejercicios.colecciones.biblioteca.Autor;
import es.cursojava.oo.ejercicios.colecciones.biblioteca.Libro;
import es.cursojava.oo.ejercicios.colegio.Alumno;
import es.cursojava.utils.Utilidades;


public class cheatsheetMapas {


	public class PruebaMaps {

		public static void main(String[] args) {
			
			Map<String,Persona> personas = new HashMap();
			
			Persona p = new Persona("N1","A1", "111A",18);
			personas.put(p.getDni() , p );
			Persona p2 = new Persona("N2","A2", "111A",38);
			Persona p3 = personas.put(p2.getDni() , p2 );
			System.out.println(p3.getNombre());
			
			Persona p4 = new Persona("N2","A2", "222B",38);
			personas.put(p4.getDni() , p2 );
			
			
			//Recupero los datos de una persona concreta
			String dni = Utilidades.pideDatoCadena("Introduce un dni");
			Persona persona = personas.get(dni);
			System.out.println(persona.getNombre());
			
			System.out.println("========== Todas las claves ==============");
			//Obtengo todas las claves, para ello las convierte en un Set que guarda un conjunto de objetos sin duplicidad
			Set<String> dnis = personas.keySet();
			for (String dniPersona : dnis) {
				System.out.println(dniPersona);
			}
			
			
			System.out.println("========== Todos los valores a partir de las claves ==============");
			//Obtengo todos los valores a partir de las claves
			for (String dniPersona : dnis) {
				Persona personaValor = personas.get(dni);
				System.out.println(personaValor.getNombre());
			}
			
			System.out.println("========== Todos los valores de manera directa ==============");
			//Obtengo todos los valores sin usar la clave creando una coleccion con el mapa.values()
			Collection<Persona> colPersonas = personas.values();
			for (Persona persona2 : colPersonas) {
				System.out.println(persona2.getNombre());
			}
			
			//Los Entry son como un registro de una base da datos, guarda clave y valor. La mejor opcion para tener clave y valor.
			Set<Entry<String, Persona>> entries =personas.entrySet();
			for (Entry<String, Persona> entry : entries) {
				System.out.println(entry.getKey() + " " + entry.getValue().getNombre());
			}
			
			personas.remove("222B");
			
			//TRATAR MAPAS CON OBJETOS Y LISTAS
			
			
			//Ejercicio biblioteca, generar los objetos y agregarlos al mapa
			Map<Autor,List<Libro>> biblioteca1 = new HashMap();
			List<Libro> libros = new ArrayList<>();
			libros.add(new Libro("Effective Java", "978-0134685991", 2017));
			libros.add(new Libro("Clean Code", "978-0132350884", 2008));
			
			Autor autor1= new Autor("Pepe","Español");
			Autor autor2= new Autor("Haku","Japonesa");
			Autor autor3= new Autor("Louise","Francés");
			
			biblioteca1.put(autor1, libros.subList(0, 1));
			biblioteca1.put(autor2, libros.subList(1, 2));
			
			
		}

	}

}
