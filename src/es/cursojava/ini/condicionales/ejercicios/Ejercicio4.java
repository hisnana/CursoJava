package es.cursojava.ini.condicionales.ejercicios;
/*
 * Pintar menu
1. Pintar Cuadrado
2. Validar email
3. Añadir Alumno
4. Salir

Elige una opción

Se pide la opción por teclado, dependiendo de la opción introducida le indicamos la que ha seleccionado, si selecciona la 4 le decimos "Adios!" y si no es ninguna de las que existe que se ha equivocado
 */

import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) {
		
		System.out.println("1. Pintar Cuadrado\r\n"
				+ "2. Validar email\r\n"
				+ "3. Añadir Alumno\r\n"
				+ "4. Salir");
		System.out.println("Elige el número de opción");
		Scanner scan = new Scanner(System.in);
		int eleccion = scan.nextInt();
		
//		if (eleccion == 1) {
//			System.out.println("Pintar cuadrado");
//		} else if (eleccion == 2) {
//			System.out.println("Validar email");
//		} else if (eleccion == 3) {
//			System.out.println("Añadir Alumno");
//		} else if (eleccion == 4) {
//			System.out.println("Adios!");
//		} else {
//			System.out.println("Te has equivocado");
//		}
		
		switch (eleccion) {
		case 1 : System.out.println("Pintar cuadrado");break;
		case 2 : System.out.println("Validar email");break;
		case 3 : System.out.println("Añadir alumno");break;
		case 4 : System.out.println("Adios!");break;
		default : System.out.println("Te has equivocado");
		}

	}

}
