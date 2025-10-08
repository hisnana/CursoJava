package es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos;


//Lácteo hereda de Alimento y tiene tipo (entera, semi, desnatada)
public class Lacteo extends Alimento {

	
	 public Lacteo(String nombre, int cantidad, double precio, String tipo) {
			super(nombre, cantidad, precio, tipo);
		}

	 @Override
	 public String toString() {
	     return super.toString() + " [Tipo: " + getTipo() + "]";
	 }
}
