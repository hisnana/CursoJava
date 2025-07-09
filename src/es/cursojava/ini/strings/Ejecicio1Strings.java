package es.cursojava.ini.strings;
//Ejercicio 1
//==============================
//Escribe un programa que reciba un String y devuelva el texto invertido.
import java.util.Scanner;

public class Ejecicio1Strings {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escribe lo que quieras:");
		String texto = scan.nextLine();
		
		
		for (int i = texto.length()-1;i >= 0 ;i--) {
			
			System.out.print(texto.charAt(i));
			
		}
		
	}

}
