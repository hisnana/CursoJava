package es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos;

public class Verdura extends Alimento {
   

    public Verdura(String nombre, int cantidad, double precio, String tipo) {
		super(nombre, cantidad, precio, tipo);
	}


    @Override
    public String toString() {
        return super.toString() + (" [Orgánica] "+ getTipo());
    }
}