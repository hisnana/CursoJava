package es.cursojava.ini.condicionales.ejercicios;

/*
 * + Declarar la variables asignatura de tipo String, nombreAlumno de tipo String y nota de tipo int 
+ Inicializar las variables con valores inventados
+ El resultado a mostrar sería: El alumno Juan en la asignatura Matemáticas tiene un Notable

0 <5 suspenso
5 <7 aprobado
7 <9 notable
9-10 sobresaliente
 */

public class Ejercicio2 {

	public static void main(String[] args) {
		String asignatura = "Lengua";
		String nombreAlumno = "Juan";
		int nota = 9;
		String notaFinal = null;
		if (nota <=5) {
			notaFinal = "Suspenso";
		} else if (nota>5 && nota<7) { //No es necesario poner el mayor que 5 porque ya ha entrado anteriormente
			notaFinal = "Aprobado";
		} else if (nota>=7 && nota<9) { //No es necesario poner el mayor que 7
			notaFinal = "Notable";
		}else if (nota == 9 || nota == 10 ) { //No es necesario poner el igual a 9
			notaFinal = "Sobresaliente";
		}else {
			System.out.println("Nota incorrecta");
		}
		System.out.println("El alumno "+nombreAlumno+" en la asignatura "+asignatura+" tiene un "+notaFinal);
	}

}
