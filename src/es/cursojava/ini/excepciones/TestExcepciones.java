package es.cursojava.ini.excepciones;

public class TestExcepciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		metodoTest();
		System.err.println("Termina");
	}
	
	public static void metodoTest() {
		try {
			if(Math.random()>0.2) {
				NumeroNoPositivoException npe = new NumeroNoPositivoException("Mi excepcion");
				throw npe;
			}else {
				System.out.println("Todo bien");
			}
		}catch(NumeroNoPositivoException e) {
			
		}

	}

}
