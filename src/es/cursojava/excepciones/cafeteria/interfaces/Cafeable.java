package es.cursojava.excepciones.cafeteria.interfaces;

import es.cursojava.excepciones.cafeteria.excepciones.TooColdTemperatureException;
import es.cursojava.excepciones.cafeteria.excepciones.TooHotTemperatureException;
import es.cursojava.excepciones.cafeteria.pojos.TazaCafe;

public interface Cafeable {
	boolean tomarCafe(TazaCafe cafe) throws TooColdTemperatureException, TooHotTemperatureException;
}
