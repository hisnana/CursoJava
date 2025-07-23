package es.cursojava.ini.metodos;

import es.cursojava.utils.Utilidades;

public class main {

	public static void main(String[] args) {
		String nombre = "Ana";
		 Metodos.saludar(nombre);
		 
		 int edad = Utilidades.calcularEdad(1953);
		 System.out.println("Tu edad es: "+edad);

	}

}
