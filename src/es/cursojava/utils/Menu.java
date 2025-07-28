package es.cursojava.utils;

import es.cursojava.ini.metodos.MetodosMenu;

public class Menu {

	public static void main(String[] args) {
		
		String[] opcionesMenu = {"1. Pintar Cuadrado\r","2. Validar email\r","3. Añadir Alumno\r","4. Salir\r"};
		boolean encendido = true;
		
		while (encendido) {
			
			Utilidades.pintaMenu(opcionesMenu);
			int opcion = Utilidades.pideDatoNumerico("");
			
			switch(opcion) {
				case 1 -> MetodosMenu.pintarCuadrado();
				case 2 -> MetodosMenu.validarEmail();
				case 3 -> MetodosMenu.anadirAlumno();
				case 4 -> encendido = false;
				default -> System.out.println("Opción incorrecta");
			
			};
			
			
		}
		
		

	}

}
