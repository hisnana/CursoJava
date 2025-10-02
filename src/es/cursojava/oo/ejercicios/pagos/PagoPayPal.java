package es.cursojava.oo.ejercicios.pagos;

import es.cursojava.utils.MiLogger;

public class PagoPayPal extends Pago {

	@Override
	public void procesarPago(double monto) {
		// TODO Auto-generated method stub
		
		MiLogger.info("El pago con PayPal se ha realizado correctamente con el pago "+getNumeroTarjeta()+" y la cantidad "+monto);
		
	}

}
