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
			int numAleatorio = (int) Math.random();
			Scanner scan = new Scanner(System.in);
			System.out.println("Introduce tu nombre de jugador");
			String name = scan.nextLine();
			String fraseAleatoria = sujetos[numAleatorio]+" "+verbos[numAleatorio]+" "+complementos[numAleatorio];
			for (int i = 0 ; i < fraseAleatoria.length(); i++) {
				
				if (fraseAleatoria.substring(i, i+1).isBlank()) {
					System.out.print(" ");
				}else {
					System.out.print("_");
				}
				
			}
			int contador = 0;
			do {
				System.out.println();
				System.out.println(fraseAleatoria);
				System.out.println("Introduce una letra");
				scan = new Scanner(System.in);
				String letra = scan.nextLine();
				
				for (int i = 0 ; i < fraseAleatoria.length(); i++) {
					
					if (fraseAleatoria.substring(i, i+1).isBlank()) {
						System.out.print(" ");
					} else if (fraseAleatoria.substring(i, i+1).contentEquals(letra)) {
						System.out.print(letra);
					} else {
						System.out.print("_");
					}
					
				}
				contador++;
				System.out.println("Te quedan "+contador+" intentos");
				
			} while(fraseAleatoria.contains(letra)&&contador<6);
			

			
	}

}
