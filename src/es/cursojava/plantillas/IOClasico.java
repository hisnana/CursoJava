package es.cursojava.plantillas;

import java.io.*;

public class IOClasico {
    public static void main(String[] args) {
        File base = new File("C:\\Users\\Tardes\\Ficheros");
        if (!base.exists()) base.mkdirs();

        File fichero = new File(base, "fichero_io.txt");

        // Escribir (reescribe)
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero, false))) {
            pw.println("Hola IO clásico");
            pw.println("Otra línea");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Añadir
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero, true))) {
            pw.println("Línea añadida (append)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
