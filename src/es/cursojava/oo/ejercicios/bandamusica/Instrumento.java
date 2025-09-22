package es.cursojava.oo.ejercicios.bandamusica;

import org.slf4j.Logger;

public class Instrumento {
	
	private String nombre;
	private String tipo;
	private boolean afinado;
	
	public boolean afinar(Instrumento intrumento, Logger log) {
		log.info("Afinando instrumento "+this.nombre);
		this.afinado=Math.random()>0.4;
		
		if (afinado) {
			afinado = true;
			log.info("El instrumento  "+this.nombre+" esta afinado");
			
		} else  {
			afinado = false;
			log.info("El instrumento  "+this.nombre+" no esta afinado");
		}
		return afinado;
	}
	
	public void tocar(Instrumento instrumento,Logger log) {
		log.info("Tocando instrumento "+this.nombre);
		
	}

	public Instrumento(String nombre, String tipo, boolean afinado) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.afinado = afinado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAfinado() {
		return afinado;
	}

	public void setAfinado(boolean afinado) {
		this.afinado = afinado;
	}

}
