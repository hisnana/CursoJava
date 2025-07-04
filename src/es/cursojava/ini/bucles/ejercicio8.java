package es.cursojava.ini.bucles;

import java.util.Scanner;

/*
 * 8. Numero tenistas en el top 10 --> 
+ Pedir por teclado la cantidad de tenistas que se apuntan a un torneo, 
+ por cada uno de ellos preguntar en que puesto de la ATP se encuentran. 
+ Indicar cuantos tenistas estarían entre los diez primeros.
 */
public class ejercicio8 {

	public static void main(String[] args) {
		System.out.println("Cantidad de tenistas que se apuntan al torneo");
		Scanner scan = new Scanner(System.in);
		int numTenistas = scan.nextInt();
		int totalTop = 0;
		
		for (int contador = 1;contador<=numTenistas;contador++) {
			System.out.println("Dime el puesto en el que se encuentra el "+contador+"º tenista");
			int puestoTenista = scan.nextInt();
			
			
			if (puestoTenista<=10) {
				System.out.println("El tenista esta entre los 10 primeros");
				totalTop++;
			}
			
		}
		System.out.println("El TOTAL de tenistas entre los 10 primeros es "+totalTop);
	}

}
