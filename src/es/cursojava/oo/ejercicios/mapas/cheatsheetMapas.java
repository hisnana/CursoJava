package es.cursojava.oo.ejercicios.mapas;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import es.cursojava.oo.ejercicios.colecciones.Persona;
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
			
			//Como añadir por sublñistas
			Map<String,List<Alumno>> aulas = new HashMap<>();
			aulas.put("aula1",alumnos.subList(0, 2));
			aulas.put("aula2",alumnos.subList(2, 3));
			aulas.put("aula3",alumnos.subList(3, 7));
		}

	}

}
