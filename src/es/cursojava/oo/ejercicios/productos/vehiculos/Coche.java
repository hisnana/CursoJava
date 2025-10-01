package es.cursojava.oo.ejercicios.productos.vehiculos;

import es.cursojava.oo.ejercicios.productos.interfaces.Apagable;
import es.cursojava.oo.ejercicios.productos.interfaces.Encendible;
import es.cursojava.oo.ejercicios.productos.productos.Alimentos;
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

	@Override
	public void select(Alimentos alimento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}



}
