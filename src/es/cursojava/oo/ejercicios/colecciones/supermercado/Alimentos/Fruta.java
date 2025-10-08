package es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos;


//Fruta hereda de Alimento y puede tener propiedades adicionales
public class Fruta extends Alimento {
 
	 public Fruta(String nombre, int cantidad, double precio, String tipo) {
	     super(nombre, cantidad, precio, tipo);
	     
	 }
	

	 @Override
	 public String toString() {
	     return super.toString() + (" [Tipo]" +this.getTipo());
	 }
}

