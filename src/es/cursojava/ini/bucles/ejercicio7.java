package es.cursojava.ini.bucles;

import java.util.Scanner;

/*
 * 7. Solicitar por consola el numero de números de la serie 
de Fibonacci que se quieren mostrar
Serie de Fibonacci: 0,1,1,2,3,5,8,13,21,34,55,....
 */

public class ejercicio7 {

	public static void main(String[] args) {
		System.out.println("Dime el numero de numero que quieres que muestre de la serie Fibonacci:");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
//		int serie = 0 ;
//		int serie2 = 0;
//		int serie3 = 1;
//		int contador = 0;
//		
//		while (contador<num) {
//			System.out.println(serie);
//			 if (contador==0) {
//				 System.out.println(serie3);
//			 }
//			
//			serie = serie2+serie3;
//			serie2 = serie3;
//			serie3 = serie;
//			
//			contador++;
//		}
		/*
		 * No esta bien hacer ñapaaaas
		 */
		
		//Asi si te ha quedado correcto:
		
		int serie = 0;
		int serie2 = 1;
		int suma = 0;
		int contador = 0;
		
		while (contador<num ) {
			System.out.println(serie);
			suma = serie+serie2;
			serie=serie2;
			serie2=suma;
			contador++;
		}
	}

}
