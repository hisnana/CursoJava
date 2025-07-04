package es.cursojava.ini.bucles;

import java.util.Scanner;

//Pedir 10 numeros --> Indicar cuantos son multiplos de 3 y cuantos multiplos de 5

public class ejercicio3 {

	public static void main(String[] args) {

		int contador = 1;
		int num3 = 0;
		int num5 = 0;
		
		while (contador<11) {
			System.out.println("Dime el numero "+contador+"º");
			Scanner scan = new Scanner(System.in);
			int numero = scan.nextInt();
			if (numero%3==0) {
				System.out.println(numero+"  Tu numero es multiplo de 3");
				contador++;
				num3++;
			}
			if (numero%5==0) { //Si se usa un else if, numeros que sean multips de 3 y 5 solo entrarian por el primer if
				System.out.println(numero+"  Tu numero es multiplo de 5");
				contador++;
				num5++;
			} 
			if (numero%3!=0&&numero%5!=0) {
				System.out.println(numero+"  No es multiplo de ninguno");
				contador++;
			}
		}
		System.out.println("El total de numeros multiplos de 3 son "+num3+" y el total de numeros multiplos de 5 son "+num5 );

	}

}
