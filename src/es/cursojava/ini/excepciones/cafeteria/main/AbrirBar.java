package es.cursojava.ini.excepciones.cafeteria.main;

import java.util.ArrayList;

import java.util.List;

import es.cursojava.ini.excepciones.cafeteria.clientes.Cliente;

import es.cursojava.ini.excepciones.cafeteria.clientes.ClienteAsiduo;
import es.cursojava.ini.excepciones.cafeteria.clientes.Comensal;
import es.cursojava.ini.excepciones.cafeteria.clientes.Huesped;
import es.cursojava.ini.excepciones.cafeteria.excepciones.TooColdTemperatureException;
import es.cursojava.ini.excepciones.cafeteria.excepciones.TooHotTemperatureException;
import es.cursojava.ini.excepciones.cafeteria.interfaces.Cafeable;
import es.cursojava.ini.excepciones.cafeteria.pojos.TazaCafe;
import es.cursojava.utils.MiLogger;


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
		
		clientes.add(clienteAsiduo1);
		clientes.add(comensal1);
		clientes.add(huesped1);
		
		for (Cliente cliente : clientes) {
			if (cliente instanceof Cafeable) {
				TazaCafe tazaCafe = new TazaCafe("con leche");
				try {
					((Cafeable) cliente).tomarCafe(tazaCafe);
				} catch (TooColdTemperatureException | TooHotTemperatureException e) {
					// TODO Auto-generated catch block
					MiLogger.error("Problemas con el cafe de "+cliente.getNombre()+" : "+e.getMessage());
					
				}
			}
			
		}
		
		
		
	}
}
