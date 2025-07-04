package es.cursojava.ini.bucles;

import java.util.Scanner;
import java.util.Random;

public class Ruleta {
	
	public static void main(String [] args) {
		boolean encendido = true;
		int monedero = 0;
		while (encendido==true) {
			System.out.println("RULETA DE LA FORTUNAAAAA!!!!");
			System.out.println("¿Que quieres hacer?");
			System.out.println("1. Meter dineros en el monedero.\r\n"
					+ "2. Apostar\r\n"
					+ "3. Ver mis dineros\r\n"
					+ "4. Salir\r\n");
			Scanner scan = new Scanner(System.in);
			int opcion = scan.nextInt();
			
			switch (opcion) {
				case 1 -> {
					System.out.println("¿Cuantos dineros quieres ingresar?");
					int dineros = scan.nextInt();
					monedero = dineros+monedero;
				}
				case 2 -> {
					System.out.println("Dime tu apuesta:\r\n"
							+ "1. Número\r\n"
							+ "2. Par o Impar\r\n"
							+ "3. Decenas\r\n");
					int num = scan.nextInt();
					Random random = new Random();
					int numero = random.nextInt(36) + 1;
					if (num==1) {
						System.out.println("Dime el número al que apuestas");
						int apuesta= scan.nextInt();
						if (apuesta<0||apuesta>=36) {
							System.out.println("Tiene que ser un numero entero del 1 al 36");
							break;
						}
						System.out.println("¿Cuantos dineros apuestas?");
						int dinerosApuesta = scan.nextInt();	
						if (dinerosApuesta>monedero) {
							System.out.println("No tienes dineros suficientes,sorry");
							break;
						}
						System.out.println("El numero premiado es:"+numero);
						if (numero==apuesta) {
							int premio = dinerosApuesta*10;
							System.out.println("ENHORABUENA!!! HAS GANADO "+premio+" lereles");
							monedero=monedero+premio;
						} else if (numero!=apuesta) {
							System.out.println("Ohhhhh has perdido!!!");
							monedero=monedero-dinerosApuesta;
						}else {
							System.out.println("Algun error ha habido");
						}
					}else if (num==2) {
						System.out.println("¿Par o impar?");
						scan= new Scanner(System.in);
						String apuesta= scan.nextLine();
						if (!apuesta.equalsIgnoreCase("par")||!apuesta.equalsIgnoreCase("impar")) {
							System.out.println("Dato incorrecto");
							break;
						}
						System.out.println("¿Cuantos dineros apuestas?");
						scan= new Scanner(System.in);
						int dinerosApuesta = scan.nextInt();
						if (dinerosApuesta>monedero) {
							System.out.println("No tienes dineros suficientes,sorry");
							break;
						}
						System.out.println("El numero premiado es:"+numero);
						if (numero%2==0&&apuesta.equalsIgnoreCase("par")) {
							int premio = dinerosApuesta*2;
							System.out.println("ENHORABUENA!!! HAS GANADO "+premio+" lereles");
							monedero=monedero+premio;
						} else if (numero%2!=0&&apuesta.equalsIgnoreCase("impar")) {
							int premio = dinerosApuesta*2;
							System.out.println("ENHORABUENA!!! HAS GANADO "+premio+" lereles");
							monedero=monedero+premio;
						}else {
							System.out.println("Ohhhhh has perdido!!!");
							monedero=monedero-dinerosApuesta;
						}
					}else if(num==3) {
						System.out.println("¿1º docena(1),2º docena(2) o 3º docena(3)?");
						int apuesta= scan.nextInt();
						System.out.println("¿Cuantos dineros apuestas?");
						int dinerosApuesta = scan.nextInt();
						if (dinerosApuesta>monedero) {
							System.out.println("No tienes dineros suficientes,sorry");
							break;
						}
						if (apuesta==1&&numero<12) {
							int premio = dinerosApuesta*5;
							System.out.println("ENHORABUENA!!! HAS GANADO "+premio+" lereles");
							monedero=monedero+premio;
						} else if(apuesta==2&&numero>12&&numero<24) {
							int premio = dinerosApuesta*5;
							System.out.println("ENHORABUENA!!! HAS GANADO "+premio+" lereles");
							monedero=monedero+premio;
						} else if(apuesta==3&&numero>24&&numero<36) {
							int premio = dinerosApuesta*5;
							System.out.println("ENHORABUENA!!! HAS GANADO "+premio+" lereles");
							monedero=monedero+premio;
						} else {
							System.out.println("Número erroneo");
						}
					}else {
						System.out.println("Respuesta incorrecta");
						break;
					}
					
					
				}
				case 3 -> System.out.println("Tus dineros totales son: "+monedero+" lereles");
				case 4 -> {
					System.out.println("Bye bye");
					encendido= false;
					break;
				}
				default -> System.out.println("Opción erronea");
			
			}
			
			
			
		}
		

		
	}

}
