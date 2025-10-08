package es.cursojava.oo.ejercicios.colecciones.supermercado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos.Alimento;

class Supermercado {
    private String nombre;

    // Stock: lista de listas de alimentos (por ejemplo, por categorías)
    private List<List<Alimento>> stockAlimentos = new ArrayList<>();

    private Set<ClienteSupermercado> clientes = new HashSet<>();

    public Supermercado(String nombre) {
        this.nombre = nombre;
    }

    // Mostrar todos los productos disponibles con cantidades (recorremos todas las listas)
    public void mostrarStock() {
        System.out.println("Productos disponibles en " + nombre + ":");
        for (List<Alimento> categoria : getStockAlimentos()) {
            for (Alimento a : categoria) {
                if (a.getCantidad() > 0) {
                    System.out.println("- " + a.getNombre()+": " + a.getCantidad() + " unidades");
                }
            }
        }
    }

    // Buscar alimento en todo el stock por nombre
    public Alimento buscarEnStock(String nombreProducto) {
        for (List<Alimento> categoria : getStockAlimentos()) {
            for (Alimento a : categoria) {
                if (a.getNombre().equalsIgnoreCase(nombreProducto)) {
                    return a;
                }
            }
        }
        return null; // no encontrado
    }

	public List<List<Alimento>> getStockAlimentos() {
		return stockAlimentos;
	}

	public void setStockAlimentos(List<List<Alimento>> stockAlimentos) {
		this.stockAlimentos = stockAlimentos;
	}

	public Set<ClienteSupermercado> getClientes() {
		return clientes;
	}

	public void setClientes(Set<ClienteSupermercado> clientes) {
		this.clientes = clientes;
	}
}