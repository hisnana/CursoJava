package es.cursojava.oo.ejercicios.pagos;

import es.cursojava.utils.MiLogger;

public class PagoTarjetaCredito extends Pago {

	@Override
	public void procesarPago(double monto) {
		// TODO Auto-generated method stub
		MiLogger.info("El pago con Tarjeta de crédito se ha realizado correctamente con el pago "+getNumeroTarjeta()+" y la cantidad "+monto);

	}

}
