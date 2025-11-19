package es.cursojava.excepciones.cafeteria.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TazaCafe {
	private String tipoCafe;
	private int temperatura;
	
	
	public TazaCafe(String tipoCafe) {
		super();
		this.tipoCafe = tipoCafe;
		this.temperatura = (int)(Math.random() * 101);
	}
	
	

}
