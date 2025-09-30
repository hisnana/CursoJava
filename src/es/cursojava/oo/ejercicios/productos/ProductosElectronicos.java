package es.cursojava.oo.ejercicios.productos;

import java.util.Date;
import es.cursojava.utils.MiLogger;

public abstract class ProductosElectronicos extends Producto implements Apagable,Encendible {
	
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
