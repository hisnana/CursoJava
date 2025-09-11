package es.cursojava.oo.ejercicios.tienda;

import es.cursojava.oo.ejercicios.tiendaManiqui.UtilidadesTienda;

public class Tienda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Producto producto1 = new Producto("Chocolate",3.5,5);
		Producto producto2 = new Producto("Pasta",1.95,2);
		Producto producto3 = new Producto("Calabacin",0.60,2);
		
		Producto[] productos = {producto1,producto2,producto3};
		
		for ( Producto producto : productos) {
			
			producto.mostrarInfo();
			
		}
		System.out.println("El valor total del inventario es: "+UtilidadesTienda.calcularValorInventario(productos));
	}

}
