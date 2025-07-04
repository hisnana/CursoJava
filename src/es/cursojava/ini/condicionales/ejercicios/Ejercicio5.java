package es.cursojava.ini.condicionales.ejercicios;

/*
 * Herramienta para calcular el precio de una llamada
Cuando se realiza una llamada, el cobro es por el tiempo que esta dura, 
de tal forma que:

+ los primeros cinco minutos cuestan 1€, 
+ los siguientes 3 minutos, 80 céntimos, 
+ los siguientes dos minutos a 70 céntimos 
+ y a partir del décimo minuto, 50 céntimos cada minuto.

Se carga un impuesto del 3% cuando es domingo, 
y si es otro día, 
	en turno de mañana 15% (6-12)
	y en turno de tarde 10%. (12-23)
	y en turno de noche -10%. (23-6)

Solicitar tiempo de llamada, dia de la semana y hora y calcular el coste de la llamada

Acciones:
Crear una condición que indique que mientras la cantidad de minutos sea mayor a 0 y menor a 5 el coste sea 1 euro. 
Crear una condición que indique que si la llamada dura más de 5 minutos y menos de 8, el costo de los minutos 6, 7 y 8 sea de 80 céntimos.
Crear una condición que indique que si la llamada dura más de 8 minutos y menos y hasta 10, el costo de los minutos 9 y 10 sea de 70 céntimos.
Crear una condición que indique que si la llamada dura más de 10 minutos cada minuto valga 50 céntimos.
Crear una condición que indique que si la llamada se hace el día domingo la llamada tenga un impuesto derl 3%
Crear una condición que indique que si la llamada se hace en un día diferente al domingo se aplique:
15% turno de 06:00am a 12:00m; 10% turno de 12:00m a 23:00pm; -10% turno de 23:00am a 05:59am; 
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ejercicio5 {

	public static void main(String[] args) {
	
		System.out.println("Dime cuando ha durado en minutos tu llamada");
		Scanner scan = new Scanner(System.in);
		int minutos = scan.nextInt();
		System.out.println("Dime que dia de la semana se ha realizado tu llamada");
		scan = new Scanner(System.in);
		String dia = scan.nextLine();
		System.out.println("Dime a que hora ha sido tu llamada,Ej: 13:20");
		scan = new Scanner(System.in);
		String hora = scan.nextLine();
		LocalTime time = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
		double dineros = 1;
		double percent = 0;
		double dinerosTotal = 0;
		if (minutos>5) {
			
			dineros += 0.80;
			System.out.println("Tu llamada cuesta 80 céntimos más, el resultado es:"+dineros);
		} 
		if (minutos>8) {
			
			dineros += 0.70;
			System.out.println("Tu llamada cuesta 70 céntimos más, el resultado es:"+dineros);
		} 
		if (minutos>10) {
			
			dineros += (minutos-10)*0.5;
			System.out.println("Tu llamada cuesta 50 céntimos por minuto, el resultado es:"+dineros);
		} else {
			System.out.println("Error, introduce bien el dato");
		}
		
		if (dia.equalsIgnoreCase("domingo")) {
			System.out.println("La llamada tiene un impuesto del 3%");
			percent = 3;
		} else if (!dia.equalsIgnoreCase("domingo")) {
			if (time.isAfter(LocalTime.of(6, 0)) && time.isBefore(LocalTime.of(12, 0))) {
				System.out.println("Tu llamada tendrá un impuesto del 15%");
				percent = 15;
			} else if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(23, 0)) ) {
				System.out.println("Tu llamada tendrá un impuesto del 10%");
				percent = 10;
			} else if (time.isAfter(LocalTime.of(23, 0)) && time.isBefore(LocalTime.of(5, 59))) {
				System.out.println("Tu llamada tendrá un impuesto del -10%");
				percent = -15;
			}else {
				System.out.println("Error en la hora introducida");
			}
		} else {
			System.out.println("Dia equivocado");
		}
		double result = (dineros/100) * percent;
		result = result+dineros;
		System.out.println("El coste total de tu llamada es: "+result);
	}

}
