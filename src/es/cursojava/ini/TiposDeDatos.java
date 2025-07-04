package es.cursojava.ini;

import java.util.Scanner;

public class TiposDeDatos {

	public static void main(String[] args) {
		//Tipos primitivos
		
		//Enteros
		int numero = 2300;
		byte edad = 10;
		short numero2 = 123;
		long numero3 = 3_432_423;
		
		//Decimales
		double decimal1 = 9.7;
		float decimal2 = 100.9f;
		
		//Caracteres
		char caracter = ' ';
		 
		//Booleanos
		boolean esMayorEdad = 7>3 ;
		
		System.out.println(edad + numero2);
		
		//Tipos objeto
		
		String nombre = "Ana";
		nombre.toUpperCase();
		System.out.println(Math.random());
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce tus apellidos");
		String apellidos = scan.nextLine();
		System.out.println(nombre + " " + apellidos);

	}

}
