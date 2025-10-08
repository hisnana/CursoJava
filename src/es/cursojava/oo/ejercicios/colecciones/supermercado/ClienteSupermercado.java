package es.cursojava.oo.ejercicios.colecciones.supermercado;

import java.util.ArrayList;
import java.util.List;
import es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos.Alimento;





class ClienteSupermercado {
    private String nombre;
    private List<Alimento> carritoCompra = new ArrayList<>();

    public ClienteSupermercado(String nombre) {
        this.setNombre(nombre);
    }

    // Añadir alimento al carrito
    public void agregarAlCarrito(Alimento alimento) {
        getCarritoCompra().add(alimento);
    }

    // Calcular total a pagar en euros
    public double calcularTotal() {
        double total = 0;
        for (Alimento a : getCarritoCompra()) {
            total += a.getPrecio() * a.getCantidad();
        }
        return total;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alimento> getCarritoCompra() {
		return carritoCompra;
	}

	public void setCarritoCompra(List<Alimento> carritoCompra) {
		this.carritoCompra = carritoCompra;
	}


}