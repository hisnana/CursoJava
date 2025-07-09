package es.cursojava.ini.strings;
//Ejercicio 2
//==============================
//Escribe un programa que determine si un String ingresado por el usuario es un palíndromo (se lee igual de izquierda a derecha que de derecha a izquierda, ignorando mayúsculas y espacios).

import java.util.Scanner;

public class Ejercicio2Strings {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escribe un palíndromo:");
		String texto = scan.nextLine();
		int inicio = 0;
		int tamaño = texto.length();
		char primero = texto.charAt(inicio);
		char segundo = texto.charAt(tamaño-1);
		

		for (int i=0;i<texto.length();i++) {

			primero = texto.toLowerCase().charAt(inicio);
			segundo = texto.toLowerCase().charAt(tamaño-1);

			if ( primero == segundo ) {
				inicio++;
				tamaño--;
				if (i == texto.length()-1) {
					System.out.println("Es un palíndromo");
				}
				

			} else {
				System.out.println("La palabra no es un palíndromo");
				break;
			}
			
			
		}
		
	}

}
