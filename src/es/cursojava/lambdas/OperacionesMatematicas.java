package es.cursojava.lambdas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OperacionesMatematicas {
	private static final Logger log = LoggerFactory.getLogger(OperacionesMatematicas.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Suma suma = new Suma();
		int resultadoSuma = suma.operar(5, 3);
		log.info("Resultado de la suma: " + resultadoSuma);
		Operacion sumaLambda = (a, b) -> a + b;

	}

}
