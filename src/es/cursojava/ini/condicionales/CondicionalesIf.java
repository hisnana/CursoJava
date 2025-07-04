package es.cursojava.ini.condicionales;

public class CondicionalesIf {

	public static void main(String[] args) {
		
		//int numero2 = (int) 	Math.random()*10;
		int numero = 6;//(int) (Math.random()*10);
		System.out.println(numero);

//		if (numero>=5) {
//			System.out.println("El número es mayor de 5 " );
//		}else if (numero>=5 && numero<7) {
//			System.out.println("El número es mayor de 5 y menor de 7" );
//		}else if (numero>=2 || numero<5) {
//			System.out.println("El número es " );
//		}else {
//			System.out.println("El número es menor de 5 " );
//		}
		
		if (numero>=5) {
			System.out.println("El número es mayor de 5 " );
		}
		
		
		
		if (numero>=5 && numero<7) {
			System.out.println("El número es mayor de 5 y menor de 7" );
		}else if (numero>=2 || numero<5) {
			System.out.println("El número es " );
		}else {
			System.out.println("El número es menor de 5 " );
		}
		
	}

}
