package es.cursojava.ini.bucles;

import java.util.Scanner;

//Mostrar todas las tablas de multiplicar hasta el número que solicites al usuario

public class ejercicio4 {

	public static void main(String[] args) {
		
		System.out.println("Dime un numero");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int contador = 1;
		while (contador<=num) {
			//Aqui deberia haber hecho el print del inicio de tabla
			
			for (int numX = 0;numX<11;numX++) {
				if (numX == 0) {
					System.out.println("TABLA DEL "+contador);
				}
				System.out.println(contador+"x"+numX+"="+contador*numX);

			}
			contador++;			
			//Aqui añadir una linea vacia
			System.out.println();
		}
	}

}
