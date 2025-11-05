package es.cursojava.ini.excepciones;

import java.util.Arrays;

import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;

public class Excepciones {

	public static void main(String[] args) throws NumeroNoPositivoException {
		// TODO Auto-generated method stub
		int[] arrayNum=crearArray();
		arrayNum= rellenarArray(arrayNum);
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
		try {
			int num= Utilidades.pedirEntero("Inserta un numero para crear un array");
			int[] arrayNum = new int[num];
		} catch(NumeroNoPositivoException nnpe) {
			
		}
		
		if (num <= 0) throw new NumeroNoPositivoException("El numero no puede ser 0");
		
		return arrayNum;
	}
	
	private static int[] rellenarArray (int[] array) {
		int i=0;
		for (int n : array) {
			int num= Utilidades.pedirEntero("Inserta un numero para la posicion "+i+"º del array");
			array[i]=num;
			i++;
		}
		MiLogger.info(Arrays.toString(array));
		return array;
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
