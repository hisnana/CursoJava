package es.cursojava.ini.condicionales.ejercicios;
/*
 * + Crear paquete ejercicios dentro de condicionales
+ Clase donde se solicite un número y se diga si el número es par o impar
 */
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce un número");
		int numeroRandom = scan.nextInt();
		if (numeroRandom%2 == 0) {
			System.out.println("El número es par");
		} else {
			System.out.println("El numero es impar");
		}
	
	}

}
