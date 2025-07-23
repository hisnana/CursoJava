package es.cursojava.ini.metodos;

import java.util.Scanner;

//Ejercicio 3
//====================================================================================
//Escribe un único método en Java que sirva para realizar los siguientes cálculos 
//sobre un rectángulo a partir de la base y la altura proporcionadas por el usuario:
//
//+ Calcular el área del rectángulo. Area=base×altura
//+ Calcular el perímetro del rectángulo. Perímetro=2×(base+altura)
//+ Calcular la diagonal del rectángulo (utilizando el teorema de Pitágoras). 
//Diagonal= (raíz cuadrada)base2+altura2
// 
//​Devolver todos los datos en un único return
//
//En el main pintar la información devuelta por el método


public class CalculadoraRectangulo {

	public static int[] rectangulo() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Dime el tamaño de la base del rectangulo");
		int base = scan.nextInt(); 
		System.out.println("Dime el tamaño de la altura del rectangulo");
		int altura = scan.nextInt(); 
		
		int area = base*altura;
		int perimetro = 2*(base+altura);
		double diagonal = (Math.sqrt(base*base+altura*altura));
		int[] resultadoRectangulo = {area,perimetro,(int)diagonal};
		
		return resultadoRectangulo; 
	}
	
}
