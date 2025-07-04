package es.cursojava.ini.bucles;

//aumentar pares, disminuir impares

public class ejercicio2 {

	public static void main(String[] args) {
		
//		for (int numPar = 2, numImpar = 99;numPar%2==0&&numImpar%2!=0&&numPar!=102&&numImpar!=0;numPar=numPar+2,numImpar=numImpar-2) {
//			System.out.println(numPar+","+numImpar);
//
//		}
		int numPar = 2;
		int numImpar = 99;
		while (numPar%2==0&&numImpar%2!=0&&numPar!=102&&numImpar!=0) {
			System.out.println(numPar+","+numImpar);
			numPar = numPar+2;
			numImpar =numImpar-2;
		}
	}

}
