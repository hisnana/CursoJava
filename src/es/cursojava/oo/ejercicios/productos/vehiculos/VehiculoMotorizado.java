package es.cursojava.oo.ejercicios.productos.vehiculos;

import es.cursojava.oo.ejercicios.productos.interfaces.Apagable;
import es.cursojava.oo.ejercicios.productos.interfaces.Consultable;
import es.cursojava.oo.ejercicios.productos.interfaces.Encendible;
import es.cursojava.oo.ejercicios.productos.interfaces.Insertable;
import es.cursojava.oo.ejercicios.productos.interfaces.Updatable;

public abstract class VehiculoMotorizado extends Vehiculo implements Encendible, Apagable, Consultable,Insertable,Updatable {
	
	private String tipoMotor;

}
