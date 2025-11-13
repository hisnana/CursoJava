package es.cursojava.ficheros.bbddyficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class CrearArchivo {
    // ====== 1) Re-crear (borrar si existe y crear de nuevo) ======
    public static File recrearArchivo(String ruta) throws IOException {
        return recrearArchivo(new File(ruta));
    }

    public static File recrearArchivo(File f) throws IOException {
        // Asegurar carpetas
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("No se pudieron crear las carpetas: " + parent);
            }
        }
        // Borrar si existe; si no se puede, truncar
        if (f.exists() && !f.delete()) {
            try (FileOutputStream fos = new FileOutputStream(f, /*append=*/false)) {
                // truncado a 0 bytes como plan B
            }
        }
        // Crear si no existe
        if (!f.exists() && !f.createNewFile()) {
            throw new IOException("No se pudo crear el archivo: " + f.getAbsolutePath());
        }
        return f;
    }

    // ====== 2) Append con '|' (crea si no existe) ======
    public static File escribirArchivo(String ruta, String... partes) throws IOException {
        return escribirArchivo(new File(ruta), partes);
    }

    public static File escribirArchivo(File f, String... partes) throws IOException {
        // Asegurar carpetas
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("No se pudieron crear las carpetas: " + parent);
            }
        }
        if (partes == null || partes.length == 0) return f;

        // Construir "a|b|c"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partes.length; i++) {
            if (i > 0) sb.append('|');
            sb.append(partes[i] == null ? "" : partes[i]);
        }

        // Escribir en modo append (crea si no existe)
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(f, true), StandardCharsets.UTF_8))) {

            // Si el archivo tiene contenido y NO acaba en salto, añade uno antes
            if (f.exists() && f.length() > 0 && !terminaEnNuevaLinea(f)) {
                bw.newLine();
            }
            bw.write(sb.toString());
            bw.newLine(); // cada llamada añade su propia línea
        }
        return f;
    }

    // Comprueba si el archivo termina en '\n' o '\r'
    private static boolean terminaEnNuevaLinea(File f) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            long len = raf.length();
            if (len == 0) return true;
            raf.seek(len - 1);
            int last = raf.read();
            return last == '\n' || last == '\r';
        }
    }
}
