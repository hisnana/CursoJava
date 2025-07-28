package es.cursojava.utils;

import java.util.Scanner;

public class Utilidades {

	//metodo calcularEdad al cual le pasamos un año y le devolvemos la edad apatir de ese año.
	
	public static int calcularEdad (int ano) {
		
		int añoActual = 2025;
		int resultado = añoActual - ano;
		return resultado;
		
	}
	
	public static void pintaMenu (String[] arrayStrings) {
		
		for (String palabra : arrayStrings) {
			System.out.println(palabra);
		}
		System.out.println("Introduce una opción");
		
	}
	public static void pintaMenu (String[] arrayStrings,String texto) {
		
		for (String palabra : arrayStrings) {
			System.out.println(palabra);
		}
		System.out.println(texto);
		
	}
	
	public static int pideDatoNumerico(String texto) {
		System.out.println(texto);
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		return num;
	}
	
	public static String pideDatoCadena(String texto){
		System.out.println(texto);
		Scanner scan = new Scanner(System.in);
		String dato= scan.nextLine();
		return dato;
		
	}
	
}


