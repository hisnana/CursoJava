package es.cursojava.ficheros.bbddyficheros;

import java.io.File;
import java.io.IOException;
import java.util.List;

import es.cursojava.bbdd.ConsultaProductos;
import es.cursojava.bbdd.Producto;

public class Start {

  public static void main(String[] args) {
  List<Producto> productos = ConsultaProductos.consultaProductos();
  System.out.println("Cantidad productos: " + productos.size());
  String nombreArchivo = "./recursos/productos.txt";
  File archivo;
	try {
		archivo = CrearArchivo.recrearArchivo(nombreArchivo);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  for (Producto p : productos) {
      try {
		archivo = CrearArchivo.escribirArchivo(nombreArchivo.toString(),p.getId().toString(), p.getNombre().toString(),p.getCategoria().toString(),p.getPrecio().toString(),p.getStock().toString(),p.getFechaAlta().toString(),p.getEstado().toString(),p.getCodigoSku().toString(),p.getCreadoPor().toString(),p.getIva().toString());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
  }
  
	try {
		ImprimirArchivo.imprimirArchivo(nombreArchivo);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

}
