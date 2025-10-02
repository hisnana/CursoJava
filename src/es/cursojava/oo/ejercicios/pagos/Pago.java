package es.cursojava.oo.ejercicios.pagos;

import java.util.UUID;

public abstract class Pago implements ProcesarPago {
	
	private String numeroTarjeta;
	
    public Pago() {
        // Genera un UUID y lo asigna como número de tarjeta
        this.numeroTarjeta = UUID.randomUUID().toString();
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
	
	

}
