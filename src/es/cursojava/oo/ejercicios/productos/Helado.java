package es.cursojava.oo.ejercicios.productos;

import es.cursojava.utils.MiLogger;

public class Helado extends Alimentos {
	
	private String sabor;

	@Override
	public void select(Alimentos alimento) {
		// TODO Auto-generated method stub
		MiLogger.info("El id del producto "+alimento.getClass().getSimpleName()+"es : "+alimento.getId());
	}

}
