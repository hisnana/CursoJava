package es.cursojava.ini.excepciones.cafeteria.clientes;

import es.cursojava.ini.excepciones.cafeteria.interfaces.Cafeable;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Comensal extends Cliente implements Cafeable {
	private boolean tieneReserva;

	@Override
	public void tomarCafe() {
		// TODO Auto-generated method stub
		
	}

	public Comensal(String nombre, boolean tieneReserva) {
		super(nombre);
		this.tieneReserva = tieneReserva;
	}
	
	

}
