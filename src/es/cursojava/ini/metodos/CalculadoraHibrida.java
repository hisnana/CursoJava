package es.cursojava.ini.metodos;

//Ejercicio 1
//====================================================================================
//En una clase 
//+ Escribe un método que pinte la tabla de multiplicar de un numero que le pases
//+ Escribe un método que pinte las tablas de multiplicar de los números que estén entre dos números que le pases
//+ Desde el main llamar a los dos métodos

public class CalculadoraHibrida {

	public static void main(String[] args) {
		int num = 8;
		int num2 = 5;
		multiplicadora(num);
		multiplicadora2(num,num2);
		String[] nombres= {"Ana","Laura","Antonio","Aitana","Pepa"};
		int totalNombres = NombresAT.arraySt(nombres);
		System.out.println("El totas del nombre que empiezan por A y contienen t son "+totalNombres);
		
		int[] resultadoRectangulo =CalculadoraRectangulo.rectangulo();
		System.out.println("El area del rectangulo es :"+resultadoRectangulo[0]);
		System.out.println("El perimetro del rectangulo es :"+resultadoRectangulo[1]);
		System.out.println("La diagonal del rectangulo es :"+resultadoRectangulo[2]);

	}
	
	public static void multiplicadora (int num) {
		System.out.println("Se va a imprimir la tabla del "+num);
		for (int i=0 ; i<=10 ;i++ ) {
			
			System.out.println(i+" X "+num+" ="+i*num);
			
		}
		
	}
	
	public static void multiplicadora2 (int num,int num2) {
		if(num<=num2) { //Se podria simprificar usando el metodo multiplicadora
			for(int j= num ; j<=num2 ; j++) {
				System.out.println("TABLA DEL "+j);
				for (int i=0 ; i<=10 ;i++ ) {
					
						System.out.println(i+" X "+j+" ="+i*j);
					
					
				}
			}	
			
			
		} else if (num>num2) {
			for(int j= num2 ; j<=num ; j++) {
				System.out.println("TABLA DEL "+j);
				for (int i=0 ; i<=10 ;i++ ) {
					
						System.out.println(i+" X "+j+" ="+i*j);
					
					
				}
			}	
			
		}


		
	}

}
