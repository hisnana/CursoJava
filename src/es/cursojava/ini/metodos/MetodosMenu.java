
package es.cursojava.ini.metodos;

import java.util.Scanner;

public class MetodosMenu {

	public static void pintarCuadrado() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Dime que tamaño quieres que tengan los lados del cuadrado:");
		int lado = scan.nextInt();
		for (int contador = 0;contador<lado;contador++ ) {
			int i = 0;
			for (int puntos = 0;puntos<lado;puntos++) {
				
				if (contador==0 || contador==lado-1) {
					System.out.print(" * ");
				} else {
					
					if (i==0 || i==lado-1) {
						System.out.print(" * ");
						i++;
					} else {
						System.out.print("   ");
						i++;
					}
						
					
				}
				
			}
			System.out.println();

		}

	}
	
	public static void validarEmail () {
		System.out.println("Introduce un email");
		Scanner scan = new Scanner(System.in);
		String email = scan.nextLine();
		String mensajeError = "";
		
		email = email.trim();
		
		if (email.isEmpty()) {
			mensajeError = "El email no puede estar en blanco";
		}else {
			if (email.contains(" ")) {
				mensajeError = "El email no puede tener espacios en blanco\n";
			}
			
			if (!email.contains("@")) {
				mensajeError += "El email no tiene arroba\n";
			}
			else if (email.indexOf("@")!=email.lastIndexOf("@")) {
				mensajeError += "El email no puede tener más de 1 arroba\n";
			}else {
				String dominio = email.substring(email.indexOf("@")+1);
				System.out.println("dominio: "+dominio);
				
				if (!dominio.contains(".")) {
					mensajeError += "El dominio del email debe contener al menos un punto \n";
				}else {//Si contiene punto
					int posicionPunto = dominio.indexOf(".");
					if (posicionPunto <2 ) {
						mensajeError += "La separación entre la @ y el primer punto debe ser superior a 2 caracteres \n";
					}
					
					int posicionUltimoPunto = dominio.lastIndexOf(".");
					int longitudDominio = dominio.length()-1;
					if (longitudDominio - posicionUltimoPunto<2 ||
							longitudDominio - posicionUltimoPunto>6) {
						mensajeError += "Después del último punto solo puede haber entre 2 y 6 caracteres \n";
					}
				}
				
				
			}
		
		}
		
	
		
		if (mensajeError.isEmpty()) {
			System.out.println("El email "+ email +" es valido");
		}else {
			System.out.println(mensajeError);
		}

	}
	
	
	public static void anadirAlumno () {
		
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
				boolean completo = false;
				for (int i = 0 ; i<array.length; i++) {
					for (int j = 0;j<array[0].length;j++) {
						if (array[i][j]==null) {
							scan = new Scanner(System.in);
							System.out.println("Dime el nombre del Alumno de la clase "+(i+1)+" del puesto "+(j+1));
							String nombre = scan.nextLine().toLowerCase().strip();
							array[i][j] = nombre;
							completo = true;
							if (nombre.isEmpty()) {
								System.out.println("No puedes guardar un Alumno vacio");
								break;
							}
					} 
//						else {
//							System.out.println("El puesto "+(j+1)+" de la clase "+(i+1)+" no esta disponible");
//						}

					}
					
				}
				if (!completo) {
					System.out.println("Los aulas estan completos");
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
						
						if (array[i][j]!=null && array[i][j].contains(nombre)) {//Deberia haberlo hecho con un equals
							resultado+=("Se ha borrado el alumno "+array[i][j]+" de la clase "+(i+1)+" en el puesto "+(j+1)+"\n");
							array[i][j]=null;
						} else {
							 ;
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
