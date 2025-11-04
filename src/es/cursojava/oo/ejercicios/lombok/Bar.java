package es.cursojava.oo.ejercicios.lombok;

public class Bar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Botella botella = new Botella(2,"Cristal","Redonda","Agua");
		
		Botella botella2 = Botella.builder().capacidad(1).build();

	}

}
 