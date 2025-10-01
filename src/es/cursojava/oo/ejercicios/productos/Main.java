package es.cursojava.oo.ejercicios.productos;

import es.cursojava.oo.ejercicios.productos.interfaces.Apagable;
import es.cursojava.oo.ejercicios.productos.interfaces.Encendible;
import es.cursojava.oo.ejercicios.productos.productos.Alimentos;
import es.cursojava.oo.ejercicios.productos.productos.Chocolate;
import es.cursojava.oo.ejercicios.productos.productos.Helado;
import es.cursojava.oo.ejercicios.productos.productos.Ordenador;
import es.cursojava.oo.ejercicios.productos.productos.ProductosElectronicos;
import es.cursojava.oo.ejercicios.productos.vehiculos.Bicicleta;
import es.cursojava.oo.ejercicios.productos.vehiculos.Coche;
import es.cursojava.oo.ejercicios.productos.vehiculos.Vehiculo;
import es.cursojava.oo.ejercicios.productos.vehiculos.VehiculoMotorizado;
import es.cursojava.utils.MiLogger;
import es.cursojava.utils.UtilsBBDD;

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
		UtilsBBDD.consultarObjeto(heladoMenta);
		
		return encendibles;
		
		
	}
	
	private static void encender(Encendible encendible) {
		
		encendible.encender(encendible);
		
		
	}
	private static void apagar(Apagable apagable) {
		apagable.apagar(apagable);
		
		
	}
}
