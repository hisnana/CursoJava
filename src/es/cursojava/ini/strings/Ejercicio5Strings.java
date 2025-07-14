package es.cursojava.ini.strings;

import java.util.Scanner;

//Ejercicio 5 - Validar un email
//==============================
//Preguntar al usuario por un email
//Le diremos si es valido o no y si no lo es le diremos el motivo
//
//Validaciones: 
//+ Que no tenga espacios en blanco
//+ Que tenga @, solo 1
//+ Que después de la @ tenga al menos un punto
//+ Que entre la @ y el punto a su derecha haya una separación de dos caracateres
//+ Que despues del último punto haya entre 2 y 6 caracteres

public class Ejercicio5Strings {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Dime tu email");
		String email = scan.nextLine().strip();
		int arroba = 0;
		int tamanoMax = email.length();
		
		boolean valido1 = false;
		boolean valido2= false;
		boolean valido3 = false;
		
		if (email.isEmpty() == true) {
			System.out.println("El email no puede estar vacio");
			valido1= false;
		}
		
		for (int i = 0; i<tamanoMax;i++) { //No es necesario hacer un for para mirar caracter por caracter
			char letra = email.charAt(i);
			String letraString = String.valueOf(letra);
			
			if (letraString.isBlank() == true) {
					System.out.println("Tu email contiene un espacio en blanco");
					valido1= false;
			}
				
			
			if (letraString.equals("@")) {
				arroba++;
				valido2= true;
				if (arroba>1) {
					System.out.println("Tu email no puede tener más de una @");
					valido2=false;
				}
				if (!email.substring(i,tamanoMax).contains(".")&&arroba>1) {
					System.out.println("Tu email no contiene el punto despues de la @");
					valido2=false;
				}
					
			} else {
				 
			}
			
			if (letraString.equals("@") && email.substring(i,tamanoMax).contains(".") && arroba<2 ) {
				int puntoIndex = email.substring(i,tamanoMax).lastIndexOf(".");
				int arrobaIndex = email.substring(i,tamanoMax).indexOf("@");
				String cadena = email.substring(i,tamanoMax);
				int tamanoCadena = cadena.length();
				String cadenaArroba = email.substring(i,tamanoMax).substring(arrobaIndex,puntoIndex);
				String cadenaPunto = cadena.substring(puntoIndex,tamanoCadena);

				if(cadenaArroba.length()<3) {
					System.out.println("Faltan caracteres entre la @ y el .");
					valido3=false;
				} else {
					valido3 = true;
				}
				
				if (cadenaPunto.length()<3 || cadenaPunto.length()>7) {
					System.out.println("Despues del punto tiene que haber en 2 y 6 caracteres");
					valido3=false;
				} 
				
			} else if (letraString.equals("@") && !email.substring(i,tamanoMax).contains(".") && arroba<2) {
				System.out.println("Falta el punto despues del @");
				valido3=false;
			}
			

		}
		if (valido1==true&&valido2==true&&valido3==true) {
			
			System.out.println("Tu email es valido");
			System.out.println(email);
			
		}
	}

}
