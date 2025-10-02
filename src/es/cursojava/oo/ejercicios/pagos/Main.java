package es.cursojava.oo.ejercicios.pagos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pago pagoTarjeta = new PagoTarjetaCredito();
		Pago pagoPayPal = new PagoPayPal();
		Pago pagoCrito = new PagoCrisptomoneda();
		double monto = 100;
		Pago[] pagos = {pagoTarjeta,pagoCrito,pagoPayPal};
		
//		ProcesadorDePagos.realizarPago(pagoTarjeta, monto);
//		ProcesadorDePagos.realizarPago(pagoCrito, monto);
//		ProcesadorDePagos.realizarPago(pagoPayPal, monto);
		for(Pago pago : pagos) {
			procesar(pago,monto);
		}
	}
	
	public static void procesar(Pago pago,double monto) {
		ProcesadorDePagos.realizarPago(pago, monto);
	}

}
