package es.cursojava.ini.strings;

import java.util.Scanner;

//Ejercicio 4
//==============================
//Escribe un programa que reciba un String con varias palabras separadas por espacios y determine cuál es la palabra más larga.
public class Ejercicio4String {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escribe lo que quieras y te digo cual es la palabra más larga:");
		String texto = scan.nextLine();
		int contadorLetras= 0;
		int inicioLetras = 0;

		for (int i = 0; i < texto.length(); i++) {
			char letra = texto.charAt(i);
			String letraString = String.valueOf(letra);
			System.out.println(letra);
			if(letraString.isBlank() == true) {
				
				//Usa el index del espacio en blanco -1 para cortar la palabra
				System.out.println(contadorLetras);
				
				String palabra = texto.substring(inicioLetras, contadorLetras);
				System.out.println(palabra);
				inicioLetras = contadorLetras+1;
				contadorLetras++;
				
			} else {
				
				contadorLetras++;
				
			}
			
		}
	}

}
