package es.cursojava.ficheros.bbddyficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EscribirArchivo {

    /**
     * Añade (append) al archivo indicado una línea con las 'partes' unidas por '|'.
     * - Crea las carpetas padre si no existen.
     * - Si el archivo no existe, se crea automáticamente.
     * - Usa UTF-8.
     */
    public static File EscribirArchivo(String ruta, String... partes) throws IOException {
        File f = new File(ruta);

        // Crear carpetas padre si hiciera falta
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("No se pudieron crear las carpetas: " + parent);
            }
        }

        // Si no hay partes que escribir, no hacemos nada
        if (partes == null || partes.length == 0) {
            return f;
        }

        // Construir "a|b|c"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            if (i > 0) sb.append('|');
            sb.append(partes[i] == null ? "" : partes[i]);
        }

        // Escribir en modo APPEND (true) -> crea el fichero si no existe y no sobreescribe
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(f, true), StandardCharsets.UTF_8))) {
            bw.write(sb.toString());
            bw.newLine(); // cada llamada añade su propia línea
            bw.flush();
        }

        return f;
    }
}
