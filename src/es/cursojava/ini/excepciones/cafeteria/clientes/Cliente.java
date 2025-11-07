package es.cursojava.ini.excepciones.cafeteria.clientes;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Cliente {
	private String nombre;

	public Cliente(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	

}
