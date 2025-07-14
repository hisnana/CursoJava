package es.cursojava.ini.array;

import java.util.Iterator;
import java.util.Scanner;

public class Ejercicio2Arrays {

	public static void main(String[] args) {
		
		int [][] array = { {1,2,3,4}, {5,6,7,8},{9,10,11,12},{13,14,15,16}};
		
		
		
//		for (int i = 0 ; i<fila.length; i++) {
//			for (int j = 0;j<array.length;j++) {
//				Scanner scan = new Scanner(System.in);
//				System.out.println("Dime el valor para la fila "+i+" y la columna "+j);
//				array[i][j] = scan.nextInt();	
//				
//			}
//			
//		}
		
		for (int i = 0 ; i<array.length; i++) {
			for (int j = 0;j<array[0].length;j++) {

				System.out.print(array[i][j]+"\t");

			}
			System.out.println();
		}
		
		for (int i = 0 ; i<array.length; i++) {
			for (int j = 0;j<array[0].length;j++) {
				
				if (i==j) {
					System.out.print(array[i][j]+"\t");
					
				}else {
					System.out.print(" \t");
				}
			}
			System.out.println();
		}
		
		int valor1 = 0;
		int valor2 = 0;
		int valor3 = 0;

		for (int i = 0 ; i>=array.length; i++) {
			for (int j = 0;j>=array[0].length;j++) {
				array[i][j]= array[array.length][array[0].length];
				
				
				
				if (i==j) {
					
					System.out.print(array[i][j]+"\t");
					
				}else {
					System.out.print(" \t");
				}
			}
			System.out.println();
		}		
		
		
		
	}

}
