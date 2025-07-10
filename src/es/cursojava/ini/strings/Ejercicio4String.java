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
		int mayorValor = 0;
		StringBuilder palabra = new StringBuilder();
		String palabraMayor = null;


		for (int i = 0; i < texto.length(); i++) {
			char letra = texto.charAt(i);
			String letraString = String.valueOf(letra);
			
			if(letraString.isBlank() != true) {
				
				//Si no es blanco suma el caracter y guardalo
				
				contadorLetras++;			
				palabra.append(letraString);

				if(i == texto.length()-1) {
					System.out.println(palabra);
					System.out.println("Tiene "+contadorLetras+" letras");
					if (contadorLetras>mayorValor) {
						palabraMayor = palabra.toString();
						mayorValor=contadorLetras;
					}
				}
					
				
			} else if (letraString.isBlank() == true) {

					
				System.out.println(palabra);
				System.out.println("Tiene "+contadorLetras+" letras");
				
				if (contadorLetras>mayorValor) {
					
					palabraMayor = palabra.toString();
					mayorValor=contadorLetras;
				}
				
				contadorLetras=0;
				palabra = new StringBuilder();
					
			}
			
		}
		
		System.out.println("La palabra más grande es: "+palabraMayor);
	}

}
