package es.cursojava.ini.excepciones;

import java.util.Arrays;

import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;

public class Excepciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arrayNum=crearArray();
		rellenarArray(arrayNum);
		muestraPosicion(arrayNum);
		MiLogger.info("bye bye");

	}
//	En una clase
//	main ()-->
//	En tres métodos distintos
//	+Pedir tamaño de un array de números, con ese dato crear el array
//	+solicitar los números a ingresar en el array
//	+solicitar la posición del array para mostrar su valor
//
//	Si hay alguna excepción se indica al usuario un mensaje y se vuelve a pedir la información
	private static int[] crearArray ()  {
	    boolean creado = false;
	    int[] resultado = null;

	    while (!creado) {
	        try {
	            int num = requirePositivo(
	                Utilidades.pedirEntero("Inserta un número (> 0) para crear un array")
	            );
	            resultado = new int[num];
	            creado = true; // salimos del bucle en la siguiente iteración
	        } catch (NumeroNoPositivoException e) {
	            MiLogger.info("Entrada inválida: " + e.getMessage() + ". Intenta de nuevo.");
	        }
	    }
	    return resultado;
		
	}
	
	// Valida y lanza checked
	public static int requirePositivo(int valor) throws NumeroNoPositivoException {
	    if (valor <= 0) {
	        throw new NumeroNoPositivoException("El valor debe ser > 0");
	    }
	    return valor;
	}
	
	private static void rellenarArray (int[] array) {
		int i=0;
		for (int n : array) {
			array[i]= Utilidades.pedirEntero("Inserta un numero para la posicion "+i+"º del array");
			i++;
		}
		MiLogger.info(Arrays.toString(array));
		
	}
	
	private static void muestraPosicion (int[] array) {
		boolean seguir = true;
		while(seguir) {
			try {
				int num= Utilidades.pedirEntero("Inserta un numero de la posicion que quieres mostrar ");
				MiLogger.info("En la posicion "+num+" esta el numero "+array[num]);
			} catch (ArrayIndexOutOfBoundsException aioob) {
				MiLogger.info("La posicion solicitada no existe tiene que ser del 0 al "+(array.length-1));
				
			}
			seguir=Utilidades.pedirConfirmacion("¿Quieres saber otra posicion?");
		}
		
	}

}
