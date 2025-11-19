package es.cursojava.excepciones.cafeteria.clientes;


import es.cursojava.excepciones.cafeteria.excepciones.TooColdTemperatureException;
import es.cursojava.excepciones.cafeteria.excepciones.TooHotTemperatureException;
import es.cursojava.excepciones.cafeteria.interfaces.Cafeable;
import es.cursojava.excepciones.cafeteria.pojos.TazaCafe;
import es.cursojava.utils.MiLogger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteAsiduo extends Cliente implements Cafeable {
	
	private String mote;


	public ClienteAsiduo(String nombre, String mote) {
		super(nombre);
		this.mote = mote;
	}

	@Override
	public boolean tomarCafe(TazaCafe cafe) throws TooColdTemperatureException, TooHotTemperatureException {
		// TODO Auto-generated method stub
		boolean servible = true;
		if (cafe.getTemperatura() < 20) {
		    servible = false; // o cafe.setServible(false);
		    throw new TooColdTemperatureException("La temperatura del café es menor a 20º");
		}else if (cafe.getTemperatura()>80) {
			servible = false;
			throw new TooHotTemperatureException("La temperatura del cafe es mayor a 80º");
			
		} else {
			servible = true;
			MiLogger.info("El cliente "+getNombre()+" se toma un cafe "+cafe.getTipoCafe()+" a temperatura "+cafe.getTemperatura());
		}
			
		return servible;	
		
	}
			
	
	

}
