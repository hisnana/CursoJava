package es.cursojava.plantillas;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;

public class EjercicioFicheros2 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 
			String directorio = Utilidades.pedirDato("Inserta un directorio:");
            File dir = new File(directorio);
            if (!dir.exists()) {
                MiLogger.info("No existe: " + dir.getAbsolutePath());
                return;
            }
            if (!dir.isDirectory()) {
                MiLogger.info("No es un directorio: " + dir.getAbsolutePath());
                return;
            }

            File[] elems = dir.listFiles();
            if (elems == null) {
                MiLogger.info("No se pudo listar el directorio (permisos u otro error).");
                return;
            }

            // Ordenar por nombre (A→Z, sin mayúsculas/minúsculas)
            Arrays.sort(elems, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));

            long totalBytes = 0L;
            int count = 0;

            for (File f : elems) {
                if (!f.isFile()) continue; // Solo ficheros (ignora subdirectorios)
                long size = f.length();
                String hidden = f.isHidden() ? "SI esta oculto" : "NO esta oculto";
                MiLogger.infof("%-50s %12s %10s%n", f.getName(), humanSize(size), hidden);
                totalBytes += size;
                count++;
            }

            if (count == 0) {
                MiLogger.info("(No hay ficheros en este directorio)");
            } else {
                MiLogger.infof("=".repeat(76));
                MiLogger.infof("Ficheros: %d   Tamaño total: %s%n", count , humanSize(totalBytes));
            }
	        
	    }

	    // Convierte bytes a B/KB/MB/GB/TB con 2 decimales
	    private static String humanSize(long bytes) {
	        String[] units = {"B","KB","MB","GB","TB"};
	        double v = bytes;
	        int i = 0;
	        while (v >= 1024 && i < units.length - 1) {
	            v /= 1024.0;
	            i++;
	        }
	        return String.format("%.2f %s", v, units[i]);
	    }
	

}
