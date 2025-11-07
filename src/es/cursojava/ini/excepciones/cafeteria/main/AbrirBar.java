package es.cursojava.ini.excepciones.cafeteria.main;

import java.util.ArrayList;

import es.cursojava.ini.excepciones.cafeteria.clientes.ClienteAsiduo;
import es.cursojava.ini.excepciones.cafeteria.clientes.Comensal;
import es.cursojava.ini.excepciones.cafeteria.clientes.Huesped;
import es.cursojava.ini.excepciones.cafeteria.pojos.TazaCafe;

public class AbrirBar {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		abrirCafeteria();
	}
	private static void abrirCafeteria() {
		
		List<Cliente> clientes = new ArrayList<>();
		
		Huesped huesped1 = new Huesped("Pepe","a5");
		Comensal comensal1 = new Comensal("Paco",false);
		ClienteAsiduo clienteAsiduo1 = new ClienteAsiduo("Marco", "El notas");
		
		
		
		
		//TazaCafe taza1 = new TazaCafe();
	}
}
