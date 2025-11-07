package es.cursojava.ini.excepciones.cafeteria.clientes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Huesped extends Cliente{
	private String dni;

	public Huesped(String nombre, String dni) {
		super(nombre);
		this.dni = dni;
	}


}
