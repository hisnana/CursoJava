package es.cursojava.ini.excepciones;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import es.cursojava.oo.ejercicios.colegio.Alumno;
import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int alumnosNuevos = 5;
		int contador= 0;
		List<Alumno> alumnos = new ArrayList<>();
		while(contador<=alumnosNuevos) {
			 MiLogger.info("Inserta los datos del alumno"+contador+"º");
			 boolean datoIncorrecto = true;
			 while(datoIncorrecto) {
				 String nombre = Utilidades.pideDatoString("Dime el nombre del alumno "+contador+"º");
				 int edad = 0;
				 double notaMedia = 0;
				 edad = Utilidades.pedirEntero("Introduce la edad");
				 notaMedia = Utilidades.pedirDecimal("Introduce la nota media");
				 Alumno alumno;
				 try {
					alumno = new Alumno(nombre,edad,notaMedia);
					alumnos.add(alumno);
					datoIncorrecto = false;
				 } catch (IllegalArgumentException iae) {
					// TODO Auto-generated catch block
					 MiLogger.info("Error  "+iae.getMessage());
					 datoIncorrecto = true;
				 } catch ( NotaInvalidaException nie) {
					 
					 MiLogger.info("Error  "+nie.getMessage());
						datoIncorrecto = true;
				 }
					 
				 
			 }
			 contador++;			 
		 }
		 
		 for(Alumno alumno : alumnos) {
			 MiLogger.info(alumno.toString());
		 }
	}

}
