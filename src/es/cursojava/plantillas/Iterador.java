package es.cursojava.plantillas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface Iterador {
	
	public static void main(String[] args) {
		List<String> nombres = new ArrayList<>();
		// ... llenar lista

		Iterator<String> it = nombres.iterator();  // aquí “creas” el iterator

		while (it.hasNext()) {
		    String n = it.next();  // siguiente elemento
		    if (n.startsWith("A")) {
		        it.remove();       // borrar de forma segura
		    }
		}
	}
	


}
