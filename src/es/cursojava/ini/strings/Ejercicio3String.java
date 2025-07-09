package es.cursojava.ini.strings;
//Ejercicio 3
//==============================
//Crea un programa que cuente cuántas vocales (a, e, i, o, u) tiene un String. Ignora las mayúsculas y minúsculas.

import java.util.Scanner;

public class Ejercicio3String {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escribe lo que quieras:");
		String texto = scan.nextLine();
		String vocales = "aeiou";
		int totalVocales = 0;
		
		for (int i=0; i < texto.length(); i++) {
			char letraString = texto.charAt(i);
			for (int j = 0 ; j < vocales.length(); j++) {
				char letra = vocales.charAt(j);
				if (letraString == letra ) {
					totalVocales++;
					
				}
				
			}
		}
		System.out.println("El total de las vocales es: "+totalVocales);
	}

}
