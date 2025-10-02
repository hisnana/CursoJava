package es.cursojava.oo.ejercicios.pagos;

public class ProcesadorDePagos {
	
	public static void realizarPago(Pago metodoPago, double monto) {
		metodoPago.procesarPago(monto);
	}

}
