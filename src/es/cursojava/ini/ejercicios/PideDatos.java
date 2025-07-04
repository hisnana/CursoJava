package es.cursojava.ini.ejercicios;

import java.util.Scanner;

public class PideDatos {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nombre de calle");
		String calle = scan.nextLine();
		System.out.println("Número");
		int num = scan.nextInt();
		System.out.println("Población");
		scan = new Scanner(System.in);
		String pob = scan.nextLine();
		System.out.println("Tus datos son: "+ calle +" "+ num + " " + pob);
		boolean boo = num>100;
		System.out.println("La calle es grande:"+ boo);
		double math = Math.random();
		boolean boo2 = math>0.5;
		System.out.println("El número generado aleatoriamente es mayor de 0,5: "+boo2);

	}

}
