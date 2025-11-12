package es.cursojava.plantillas;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class EmpezandoConFicherosNIO {

    public static void main(String[] args) {
        // Rutas (usa / o Paths.get para evitar problemas con \)
        Path base = Paths.get("C:/Users/Tardes/Ficheros");
        Path fichero1 = base.resolve("fichero1.txt");

        try {
            // 1) Asegurar que la carpeta existe
            Files.createDirectories(base);

            // 2) CREAR/REESCRIBIR y ESCRIBIR texto
            try (BufferedWriter bw = Files.newBufferedWriter(
                    fichero1,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING)) {

                bw.write("Primera línea");
                bw.newLine();
                for (int i = 1; i <= 3; i++) {
                    bw.write("Línea " + i);
                    bw.newLine();
                }
            }

            // 3) AÑADIR (append) al final del archivo
            try (BufferedWriter bw = Files.newBufferedWriter(
                    fichero1,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND)) {
                bw.write("Línea añadida al final");
                bw.newLine();
            }

            // 4) LEER línea a línea
            try (BufferedReader br = Files.newBufferedReader(fichero1, StandardCharsets.UTF_8)) {
                String linea;
                System.out.println("Contenido de " + fichero1 + ":");
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // 5) LEER todo a memoria (sin Streams)
            List<String> lineas = Files.readAllLines(fichero1, StandardCharsets.UTF_8);
            System.out.println("Total de líneas: " + lineas.size());

            // 6) LISTAR contenido del directorio
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(base)) {
                System.out.println("=== Contenido de " + base + " ===");
                for (Path p : ds) {
                    String tipo = Files.isDirectory(p) ? "[DIR] " : "[FILE]";
                    System.out.println(tipo + " " + p.getFileName());
                }
            }

            // 7) COPIAR / MOVER / BORRAR (ejemplos)
            Path copia = base.resolve("fichero1_copia.txt");
            Files.copy(fichero1, copia, StandardCopyOption.REPLACE_EXISTING);

            Path movido = base.resolve("fichero1_movido.txt");
            Files.move(copia, movido, StandardCopyOption.REPLACE_EXISTING);

            Files.deleteIfExists(movido);

            // 8) Crear subdirectorios numerados correctamente
            for (int i = 0; i < 10; i++) {
                Path subdir = base.resolve("subdir" + i);
                Files.createDirectories(subdir); // mkdirs()
            }

        } catch (IOException e) {
            // Si quieres SLF4J aquí, puedes loguear en vez de imprimir
            e.printStackTrace();
        }
    }
}
