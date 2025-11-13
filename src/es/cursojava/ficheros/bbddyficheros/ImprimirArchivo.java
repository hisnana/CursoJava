package es.cursojava.ficheros.bbddyficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import es.cursojava.utils.MiLogger;

public class ImprimirArchivo {
	public static void imprimirArchivo(String ruta) throws IOException {
	    File f = new File(ruta);

	    if (!f.exists()) { MiLogger.warning("No existe: " + f.getAbsolutePath()); return; }
	    if (!f.isFile()) { MiLogger.warning("No es un fichero: " + f.getAbsolutePath()); return; }

	    try (BufferedReader br = new BufferedReader(
	            new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            MiLogger.info(linea);
	        }
	    }
	}

}
