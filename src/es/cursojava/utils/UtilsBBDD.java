package es.cursojava.utils;

import es.cursojava.oo.ejercicios.productos.interfaces.Consultable;
import es.cursojava.oo.ejercicios.productos.interfaces.Deleteable;
import es.cursojava.oo.ejercicios.productos.interfaces.Insertable;
import es.cursojava.oo.ejercicios.productos.interfaces.Updatable;

public class UtilsBBDD {
	
	public static void insertarObjeto(Insertable obj) {
		obj.insert();
	}
	
	public static void consultarObjeto(Consultable obj) {
		obj.select(null);
	}
	
	public static void updateObjeto(Updatable obj) {
		obj.update();
	}
	
	public static void consultarObjeto(Deleteable obj) {
		obj.delete();
	}

}
