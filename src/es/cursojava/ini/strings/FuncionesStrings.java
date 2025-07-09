package es.cursojava.ini.strings;

public class FuncionesStrings {

	public static void main(String[] args) {
		//Sacar la pocición de la "e" de "prueba":
		String texto = "Esto es una prueba de Strings";
		//System.out.println(texto.indexOf("e"));
		char letra = 'e';
		
		//Primera opción
		int primera = texto.indexOf(letra);
		int segunda = texto.indexOf(letra, primera + 1);
		System.out.println(segunda);		 
		//Segunda opción
		for (int i = 0; i < texto.length(); i++) {
		    if (texto.charAt(i) == letra && i!=texto.indexOf(letra)) {
		        System.out.println("Letra encontrada en la posición: " + i);
		        break;
		    }
		}
		
		//Tercera opción
		
		int posicionPrimeraE = texto.indexOf(letra)+1;
		String subcadena = texto.substring(posicionPrimeraE);
		int posicionSegundaE = subcadena.indexOf(letra);
		System.out.println("La posicion de la segunda e esta en "+(posicionPrimeraE+posicionSegundaE));
	}

}
