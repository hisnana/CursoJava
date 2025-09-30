package es.cursojava.oo.ejercicios.productos;

import es.cursojava.utils.MiLogger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiLogger.info("Da comienzo la creación de productos");
		Encendible[] encendibles = crearProductos();
		for (Encendible encendible : encendibles) {
			encender(encendible);
		}


	}
	
	private static Encendible[] crearProductos () {
		
		Alimentos chocolateBlanco = new Chocolate();
		Alimentos heladoMenta = new Helado();
		ProductosElectronicos ordenador1 = new Ordenador("Intel");
		VehiculoMotorizado coche1 = new Coche();
		Vehiculo bici1 = new Bicicleta();
		
		Encendible[] encendibles = {ordenador1,coche1};
		
		return encendibles;
		
		
	}
	
	private static void encender(Encendible encendible) {
		
		encendible.encender(encendible);
		
		
	}
	private static void apagar(Apagable apagable) {
		apagable.apagar(apagable);
		
		
	}
}
