package es.cursojava.plantillas;
//Ejercicio 1:
//============================================================
//Ficheros FILE: 
//Crear en la carpeta Ficheros:
//Crear el directorio llamado ‘Imágenes’. 
//Crear el directorio llamado ‘Documentos’. 
//Crear el directorio llamado ‘Otros’. 
//
//Preguntar por 5 nombres de ficheros.
//    Si el fichero tiene la extensión de una imagen (.gif - .jpg - .png) lo vas a crear en la carpeta de imágenes. 
//    Si el fichero es un .txt en documentos. 
//    Si no es una imagen y no es un documento lo añadirán en otros. 
//
//Coger todos los ficheros que están en el directorio de otros, renombrarlos con nuestro nombre y con un contador para clasificar los archivos.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import es.cursojava.utils.MiLogger;
import es.cursojava.utils.Utilidades;

public class EjercicioFicheros1 {
	private static final String[] IMG_EXTS = {".gif", ".jpg", ".jpeg", ".png"};
	private static final String DIRECTORIO = "C:\\Users\\Tardes\\Ficheros";
    private static final String DIR_IMG  = "Imagenes";
    private static final String DIR_DOC  = "Documentos";
    private static final String DIR_OTROS= "Otros";
	public static void main(String[] args) {
		
		
		File base = new File(DIRECTORIO);
        File dirImg = new File(base, DIR_IMG);
        File dirDoc = new File(base, DIR_DOC);
        File dirOtros = new File(base, DIR_OTROS);
        if (!base.exists()) base.mkdirs();
        dirImg.mkdirs();
        dirDoc.mkdirs();
        dirOtros.mkdirs();
		
		
		int numFicheros = Utilidades.pedirEntero("Cuanto ficheros quieres crear?");
		
		if(numFicheros>0) {
			int contador = 0;
			while(contador<numFicheros) {
				String nombreFichero = Utilidades.pideDatoString("Dime como llamar el fichero "+(contador+1)).toLowerCase();
				String nombreSolo = new File(nombreFichero).getName();
				String ext = getExtension(nombreSolo);
				
                File destino;
                if (esImagen(ext)) {
                    destino = unicoTest(dirImg, sinExtension(nombreSolo), ext);
                } else if (".txt".equals(ext)) {
                    destino = unicoTest(dirDoc, sinExtension(nombreSolo), ext);
                    escribeLinea(destino, "Archivo creado para pruebas.");
                } else {
                    destino = unicoTest(dirOtros, sinExtension(nombreSolo), ext);
                }
                
				
                try {
                    // createNewFile crea el archivo vacío; si quieres "rellenarlo", escribe algo opcional:
                    if (destino.createNewFile()) {
                    	MiLogger.info("Creado: " + destino.getAbsolutePath());
                        // Opcional: rellenar con una línea
                        
                    } else {
                    	MiLogger.info("Ya existía: " + destino.getAbsolutePath());
                    }
                } catch (IOException e) {
                	MiLogger.info("Error creando " + destino.getAbsolutePath() + ": " + e.getMessage());
                }
                
				contador++;
			}
		}else {
			MiLogger.info("El numero de ficheros tiene que ser mayor que 0");
		}
		
		 // 3) Renombrar los ficheros en "Otros" a: <TU_NOMBRE>_<contador>.<ext>
        String miNombre = "Ana"; // <-- pon aquí tu nombre preferido
        int contador = 1;
        File[] otros = dirOtros.listFiles();
        if (otros != null) {
            for (File f : otros) {
                if (!f.isFile()) continue;

                String ext = getExtension(f.getName()); // conserva la extensión original
                // Creamos nombre destino con contador, evitando colisiones si ya existe
                File nuevo = unicoTest(dirOtros, miNombre + "_" + contador, ext);

                boolean ok = f.renameTo(nuevo);
                if (ok) {
                    System.out.println("Renombrado: " + f.getName() + " -> " + nuevo.getName());
                    contador++;
                } else {
                    System.err.println("No se pudo renombrar: " + f.getName());
                }
            }
        }
		
	}
	
    // ===== Helpers =====

    private static boolean esImagen(String ext) {
        for (String e : IMG_EXTS) {
            if (e.equalsIgnoreCase(ext)) return true;
        }
        return false;
    }
    
    // Quita la extensión, p.ej. "foto.png" -> "foto"
    private static String sinExtension(String name) {
        int dot = name.lastIndexOf('.');
        return (dot >= 0) ? name.substring(0, dot) : name;
    }
	
    // Devuelve p.ej. ".png" o "" si no hay extensión
    private static String getExtension(String name) {
        int dot = name.lastIndexOf('.');
        return (dot >= 0 && dot < name.length() - 1) ? name.substring(dot).toLowerCase() : "";
    }
    
    // Asegura nombre único dentro del directorio (si existe, añade _1, _2, ...)
    private static File unicoTest(File dir, String baseName, String ext) {
        File f = new File(dir, baseName + ext);
        int i = 1;
        while (f.exists()) {
            f = new File(dir, baseName + "_" + i + ext);
            i++;
        }
        return f;
    }

    // Opcional: escribir una línea en el archivo recién creado
    @SuppressWarnings("unused")
    private static void escribeLinea(File f, String linea) {
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error escribiendo en " + f.getAbsolutePath() + ": " + e.getMessage());
        }
    }

	

}
