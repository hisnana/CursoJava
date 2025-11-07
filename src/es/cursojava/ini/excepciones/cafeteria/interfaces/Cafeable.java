package es.cursojava.ini.excepciones.cafeteria.interfaces;

import es.cursojava.ini.excepciones.cafeteria.excepciones.TooColdTemperatureException;
import es.cursojava.ini.excepciones.cafeteria.excepciones.TooHotTemperatureException;
import es.cursojava.ini.excepciones.cafeteria.pojos.TazaCafe;

public interface Cafeable {
	boolean tomarCafe(TazaCafe cafe) throws TooColdTemperatureException, TooHotTemperatureException;
}
