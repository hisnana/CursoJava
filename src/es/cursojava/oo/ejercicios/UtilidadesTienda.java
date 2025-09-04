package es.cursojava.oo.ejercicios;

public class UtilidadesTienda {
	
	public static double calcularValorInventario(Producto[] productos) {
		double total = 0;
		for (Producto producto : productos) {
			total += producto.calcularValorTotal() ;
			
		}
		
		
		return total;
	}

}
