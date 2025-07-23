package es.cursojava.ini.metodos;


//Ejercicio 2
//====================================================================================
//En una clase 
//+ Escribe un método que reciba un array de Strings con nombres y devuelva el número de nombres que empiecen por A y contengan una t
//+ Desde el main generar el array con los nombres y llamar al método anterior, indicar cuantos nombres cumplen esa condición-


public class NombresAT {

	public static int arraySt (String[] nombres) {
		
		int resultado = 0;
		
		
		for(String nombre:nombres) {
			if (nombre.startsWith("A")&&nombre.contains("t")) {
				resultado++;
			}
		}
		return resultado;
		
	}
	
	
}
