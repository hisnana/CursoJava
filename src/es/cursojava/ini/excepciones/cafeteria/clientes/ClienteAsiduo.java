package es.cursojava.ini.excepciones.cafeteria.clientes;


import es.cursojava.ini.excepciones.cafeteria.interfaces.Cafeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteAsiduo extends Cliente implements Cafeable {
	
	private String mote;

	@Override
	public void tomarCafe() {
		// TODO Auto-generated method stub
		
	}

	public ClienteAsiduo(String nombre, String mote) {
		super(nombre);
		this.mote = mote;
	}
	
	

}
