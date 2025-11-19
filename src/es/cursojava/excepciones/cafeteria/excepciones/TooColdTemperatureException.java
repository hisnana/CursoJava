package es.cursojava.excepciones.cafeteria.excepciones;

public class TooColdTemperatureException extends TemperatureException {
	public TooColdTemperatureException(String mssg) {
		super(mssg);
	}
}
