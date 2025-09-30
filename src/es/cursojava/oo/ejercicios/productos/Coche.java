package es.cursojava.oo.ejercicios.productos;

import es.cursojava.utils.MiLogger;

public class Coche extends VehiculoMotorizado {
	
	private String marca;

	@Override
	public void encender(Encendible encendible) {
		// TODO Auto-generated method stub
		MiLogger.info("El producto "+encendible.getClass().getSimpleName()+" se va ha encender.");
	}

	@Override
	public void apagar(Apagable apagable) {
		// TODO Auto-generated method stub
		MiLogger.info("El producto "+apagable.getClass().getSimpleName()+" se va ha apagar.");
	}



}
