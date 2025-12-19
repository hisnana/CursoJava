package es.cursojava.lambdas;

public class MainComparaCadenas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComparaCadenas comparaLongitud = s1 -> s1.length() > 5;
		ComparaCadenas contieneTexto = s1 -> {s1 = s1.toLowerCase();
										return s1.contains("Hola");};
		
		String cadena1 = "Hola Mundo";
		
		System.out.println("¿La cadena tiene más de 5 caracteres? " + comparaLongitud.comparar(cadena1));
		System.out.println("¿La cadena contiene 'Hola'? " + contieneTexto.comparar(cadena1));
	}

}
