package es.cursojava.ini.metodos;

import java.util.Scanner;

public class Ahorcado {

	public static void main(String[] args) {
		String[] sujetos = {
			    "El gato", "La niña", "Mi amigo", "Un hombre", "La policía", "El perro",
			    "La profesora", "Un payaso", "El médico", "La madre", "El robot", "Mi vecino"
			};

			String[] verbos = {
			    "come", "pinta", "busca", "rompe", "salta", "canta",
			    "lava", "lee", "mueve", "atrapa", "conduce", "enciende"
			};

			String[] complementos = {
			    "una manzana", "la casa", "el coche", "una canción", "el jardín", "la calle",
			    "el balón", "un sombrero", "la ventana", "la lámpara", "el periódico", "el fuego"
			};
			int numAleatorio = (int) (Math.random()*12);
			Scanner scan = new Scanner(System.in);
			System.out.println("Introduce tu nombre de jugador");
			String name = scan.nextLine();
			String fraseAleatoria = sujetos[numAleatorio]+" "+verbos[numAleatorio]+" "+complementos[numAleatorio];
			String fraseOculta = "";
			for (int i = 0 ; i < fraseAleatoria.length(); i++) {
				
				if (fraseAleatoria.substring(i, i+1).isBlank()) {
					
					fraseOculta += " ";
				}else {
					
					fraseOculta += "_";
				}
			
				
			}
			System.out.println(fraseOculta);
			int contador = 0;
			
			do {
				String letra=pideLetra(fraseAleatoria);
				comprobarLetra(fraseAleatoria,letra,fraseOculta);
				contador++;
				System.out.println("Llevas "+contador+" intentos de 6");
				
			} while(contador<6);
			

			
	}
	
	public static String pideLetra (String fraseAleatoria) {
		System.out.println();
		System.out.println(fraseAleatoria);
		System.out.println("Introduce una letra");
		Scanner scan = new Scanner(System.in);
		scan = new Scanner(System.in);
		String letra = scan.nextLine();
		return letra;
		
	}
	
	private static void comprobarLetra (String fraseAleatoria,String letra,String fraseOculta) {
		
//		if (fraseAleatoria.contains(letra)) {
//			fraseOculta.replace("_", letra);
//		}
//		System.out.println(fraseOculta);
		
		for (int i = 0 ; i < fraseAleatoria.length(); i++) {
			fraseOculta = "";
			if (fraseAleatoria.substring(i, i+1).isBlank()) {
				
				fraseOculta += " ";
			} else if (fraseAleatoria.substring(i, i+1).toLowerCase().equalsIgnoreCase(letra)) {
				
				fraseOculta += letra;
			} else {
				
				fraseOculta += "_";
			}
			
		}
		System.out.println(fraseOculta);
		System.out.println();
		
	}

}
