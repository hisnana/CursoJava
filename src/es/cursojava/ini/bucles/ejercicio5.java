package es.cursojava.ini.bucles;

import java.util.Scanner;

//Solicitar por consola el numero de alumnos de un aula. 
//Pedir para cada alumno su nota
//Indicar la nota media de todos los alumnos del aula.

public class ejercicio5 {

	public static void main(String[] args) {
	
		System.out.println("Dime un numero de alumnos");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int contadorNota = 0;
		
		for (int contador = 1;contador<=num;contador++ ) {
			System.out.println("Dime la nota del numero "+contador);
			int nota = scan.nextInt();
			contadorNota = contadorNota+nota;
			
		}
		System.out.println("Esta es la nota media de todos los alumnos "+contadorNota/2);
	}

}
