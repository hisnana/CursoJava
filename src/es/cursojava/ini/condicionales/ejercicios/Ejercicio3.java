package es.cursojava.ini.condicionales.ejercicios;

/*
 * Pedir dos numeros y pedir la operación que se quiere realizar ("suma o +", resta o - ,*,/)  nextLine

Mostrar el resultado de la operación con los dos números

 */
import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) {
		System.out.println("Dime un número cualquiera");
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		System.out.println("Dime otro número cualquiera");
		scan = new Scanner(System.in);
		int num2 = scan.nextInt();
		System.out.println("Dime que operación quieres realizar con ellos: suma o +, resta o -, * o /");
		scan = new Scanner(System.in);
		String operacion = scan.nextLine();
		int resultado;
		
		if (operacion.equalsIgnoreCase("suma") || operacion.equalsIgnoreCase("+")) {
			resultado = num1+num2;
			System.out.println("Se han sumado los numeros y el resultado es: "+resultado);
		} else if (operacion.equalsIgnoreCase("resta") || operacion.equalsIgnoreCase("-") ) {
			resultado = num1-num2;
		} else if (operacion.equalsIgnoreCase("*")) {
			resultado = num1*num2;
			System.out.println("Se han multiplicado los numeros y el resultado es: "+resultado);
		} else if (operacion.equalsIgnoreCase("/")) {
			resultado = num1/num2;
			System.out.println("Se han dividido los numeros y el resultado es el siguiente: "+resultado);
		}
	}

}
