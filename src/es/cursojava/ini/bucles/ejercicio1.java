package es.cursojava.ini.bucles;

//Pintar numeros pares 1 al 100
public class ejercicio1 {

	public static void main(String[] args) {
//		int num = 0;
//		while (num%2==0&&num<=100) {
//			System.out.println(num);
//			num= num+2;
//			continue;
//		}
		
		for (int num = 0;num%2==0&&num<=100;num = num+2) {
			System.out.println(num);
		}

	}

}
