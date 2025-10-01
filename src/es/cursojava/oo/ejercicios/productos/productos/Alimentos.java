package es.cursojava.oo.ejercicios.productos.productos;

import java.util.Date;

import es.cursojava.oo.ejercicios.productos.interfaces.Consultable;

public abstract class Alimentos extends Producto implements Consultable  {
	
	private Date fechaFabricacion;
	
    // Constructor sin argumentos que asigna la fecha actual
    public Alimentos() {
        this.fechaFabricacion = new Date();  // fecha actual al crear el objeto
    }
    
    // Getter para fechaFabricacion
    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }


}
