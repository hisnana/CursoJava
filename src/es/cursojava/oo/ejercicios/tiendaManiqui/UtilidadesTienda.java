package es.cursojava.oo.ejercicios.tiendaManiqui;

import es.cursojava.oo.ejercicios.tienda.Producto;

public class UtilidadesTienda {
	
	public static double calcularValorInventario(Producto[] productos) {
		double total = 0;
		for (Producto producto : productos) {
			total += producto.calcularValorTotal() ;
			
		}
		
		
		return total;
	}

}
