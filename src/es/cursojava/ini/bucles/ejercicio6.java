package es.cursojava.ini.bucles;

import java.util.Scanner;

/*
 * 6. Conjetura Collatz
Solicitar un numero:
+Si el numero es par, se divide entre dos
+Si es impar se multiplicar por 3 y se le suma 1
se repite hasta que el valor del numero es 1
 */

public class ejercicio6 {

	public static void main(String[] args) {
		System.out.println("Dime un numero:");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		while (num!=1) {
			if (num%2==0) {
				num=num/2;
				System.out.println("Es par: "+num);
			} else if (num%2!=0) {
				num=num*3+1;
				System.out.println("Es impar: "+num);
			} else {
				System.out.println("Dato erroneo, debe ser un numero entero");
			}
			
		}

	}

}
