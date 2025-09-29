package es.cursojava.oo.ejercicios.limpiar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Verdura implements Desinfectable {
	private String procedencia;
	private final static Logger log = LoggerFactory.getLogger("Verdura");



	public Verdura(String procedencia) {
		super();
		this.procedencia = procedencia;
	}



	@Override
	public void desinfectar() {
		// TODO Auto-generated method stub
		
		log.info("La verdura de "+procedencia+" se esta desinfectando.");
		
	}
	
	

}
