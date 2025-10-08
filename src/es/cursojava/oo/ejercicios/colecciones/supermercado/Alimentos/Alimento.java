package es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos;

import java.util.*;

public abstract class Alimento {
    private String nombre;
    private int cantidad;
    private double precio;
    private String tipo;

    public Alimento(String nombre, int cantidad, double precio,String tipo) {
        this.nombre = nombre;
        this.cantidad= cantidad;
        this.precio = precio;
        this.tipo = tipo;
    }


	@Override
    public String toString() {
        return nombre + " (" + getCantidad() + " uds, €" + precio + ")";
    }

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
}