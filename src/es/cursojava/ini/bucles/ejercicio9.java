package es.cursojava.ini.bucles;

import java.util.Scanner;

/*
 * 9. Pintar el menu hasta que se puse la opción de salir. 

	1. Pintar Cuadrado
	2. Validar email
	3. Añadir Alumno
	4. Salir

	Elige una opción

Si se pulsa la opción 1, solicitar el tamaño de los lados de un cuadrado y pintar el cuadrado de la siguiente manera:
Por ejemplo si el tamaño es 4:

		****
		*  *
		*  *
		****
 */


public class ejercicio9 {

	public static void main(String[] args) {
		boolean encendido = true;
		while(encendido=true) {
			System.out.println("\t"
					+ "1. Pintar Cuadrado\r\n"
					+ "	2. Validar email\r\n"
					+ "	3. Añadir Alumno\r\n"
					+ "	4. Salir");
			System.out.println("Dime el número de opción:");
			Scanner scan = new Scanner(System.in);
			int num = scan.nextInt();
			
			
			if (num == 1) {
				System.out.println("Dime que tamaño quieres que tengan los lados del cuadrado:");
				int lado = scan.nextInt();
				for (int contador = 0;contador<lado;contador++ ) {
					int i = 0;
					for (int puntos = 0;puntos<lado;puntos++) {
						
						if (contador==0 || contador==lado-1) {
							System.out.print(" * ");
						} else {
							
							if (i==0 || i==lado-1) {
								System.out.print(" * ");
								i++;
							} else {
								System.out.print("   ");
								i++;
							}
								
							
						}
						
					}
					System.out.println();

				}

				
			} else if (num == 2) {
				
				System.out.println("Validar email");
				
			}else if (num == 3) {
				
				System.out.println("Añadir alumno");
				
			} else if (num == 4) {
				System.out.println("Bye bye");
				break;
			} else {
				System.out.println("La opción es incorrecta");
				
			
			}
		}


	}

}
