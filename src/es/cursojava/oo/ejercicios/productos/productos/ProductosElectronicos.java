package es.cursojava.oo.ejercicios.productos.productos;

import java.util.Date;

import es.cursojava.oo.ejercicios.productos.interfaces.Apagable;
import es.cursojava.oo.ejercicios.productos.interfaces.Consultable;
import es.cursojava.oo.ejercicios.productos.interfaces.Encendible;
import es.cursojava.oo.ejercicios.productos.interfaces.Insertable;
import es.cursojava.oo.ejercicios.productos.interfaces.Updatable;
import es.cursojava.utils.MiLogger;

public abstract class ProductosElectronicos extends Producto implements Apagable,Encendible,Consultable,Insertable,Updatable {
	
	private Date fechaFabricacion;
	
    // Constructor sin argumentos que asigna la fecha actual
    public ProductosElectronicos() {
        this.fechaFabricacion = new Date();  // fecha actual al crear el objeto
    }

    // Getter para fechaFabricacion
    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

}
