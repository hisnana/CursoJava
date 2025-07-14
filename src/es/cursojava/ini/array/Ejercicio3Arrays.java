package es.cursojava.ini.array;

import java.util.Scanner;

public class Ejercicio3Arrays {

	public static void main(String[] args) {
		boolean funciona = true;
		String [][] array = new String[2][3];
		
		do {
			System.out.println("1. Insertar Almunos\n"
					+ "2. Mostrar Alumnos\n"
					+ "3. Buscar Alumno\n"
					+ "4. Borrar Alumno\n"
					+ "5. Salir");
			Scanner scan = new Scanner(System.in);
			System.out.println("Dime una opción");
			int num = scan.nextInt(); 
		
			if (num==1) {
				
				for (int i = 0 ; i<array.length; i++) {
					for (int j = 0;j<array[0].length;j++) {
						if (array[i][j]==null) {
							scan = new Scanner(System.in);
							System.out.println("Dime el nombre del Alumno de la clase "+(i+1)+" del puesto "+(j+1));
							String nombre = scan.nextLine().toLowerCase().strip();
							array[i][j] = nombre;
							if (nombre.isEmpty()) {
								System.out.println("No puedes guardar un Alumno vacio");
								break;
							}
						} else {
							System.out.println("El puesto "+(j+1)+" de la clase "+(i+1)+" no esta disponible");
						}

					}
					
				}
				
			} else if(num==2) {
				for (int i = 0 ; i<array.length; i++) {
					for (int j = 0;j<array[0].length;j++) {
						if (array[i][j]==null) {
							System.out.println("El puesto "+(j+1)+" de la clase "+(i+1)+" esta VACIO");
						} else {
							System.out.println("El alumno de la clase "+(i+1)+" y del puesto "+(j+1)+" se llama "+array[i][j]);
						}

					}
					
				}
			} else if (num==3) {
				scan = new Scanner(System.in);
				System.out.println("Dime que alumno quieres buscar");
				String nombre = scan.nextLine();
				String resultado = "";
				for (int i = 0 ; i<array.length; i++) {
					for (int j = 0;j<array[0].length;j++) {
						if (array[i][j]!=null && array[i][j].contains(nombre)) {
							resultado+=("Alumno encontrado en la clase "+(i+1)+" en el puesto "+(j+1)+" y se llama: "+array[i][j]+"\n");	
						} 

					}
					
				}
				if (resultado.isEmpty()) {
					System.out.println("No se ha encontrado resultados");
				} else {
					System.out.println(resultado);
				}
				
			} else if (num==4) {
				scan = new Scanner(System.in);
				System.out.println("Dime que alumno quieres borrar");
				String nombre = scan.nextLine();
				String resultado = "";
				for (int i = 0 ; i<array.length; i++) {
					for (int j = 0;j<array[0].length;j++) {
						
						if (array[i][j]!=null && array[i][j].contains(nombre)) {
							resultado+=("Se ha borrado el alumno "+array[i][j]+" de la clase "+(i+1)+" en el puesto "+(j+1)+"\n");
							array[i][j]=null;
						} else {
							continue;
						}

					}
					
				}
				if (resultado.isEmpty()) {
					System.out.println("No se ha encontrado resultados");
				} else {
					System.out.println(resultado);
				}
			} else if (num==5) {
				System.out.println("Bye bye");
				funciona = false;
			} else {
				System.out.println("Numero erroneo");
			}
		} while (funciona);

	}

}
