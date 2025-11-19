package es.cursojava.plantillas;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cuenta ocurrencias de la palabra "Java" en un fichero de texto.
 * - Usa regex con límites de palabra: \bJava\b (evita contar "JavaScript").
 * - Case-insensitive (java/JAVA/Java...)
 * Incluye variantes sin regex y con Streams por si el enunciado las pide.
 */
public class FuncionesScripts {
    // Palabra exacta "Java" (sensibilidad a may/min desactivada + Unicode)
    private static final Pattern P = Pattern.compile("\\bJava\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    /** Forma recomendada para examen: BufferedReader + regex. */
    public static long contar(Path ruta) throws IOException {
        long total = 0;
        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Matcher m = P.matcher(linea);
                while (m.find()) total++;
            }
        }
        return total;
    }

    /** Variante sin regex (tokenizando por no-palabra). */
    public static long contarSinRegex(Path ruta) throws IOException {
        long total = 0;
        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] tokens = linea.split("\\W+"); // separa por no-palabras
                for (String t : tokens) {
                    if ("java".equalsIgnoreCase(t)) total++;
                }
            }
        }
        return total;
    }

    /** Variante con Streams (solo si el examen lo permite). */
    public static long contarStream(Path ruta) throws IOException {
        try (var lines = Files.lines(ruta, StandardCharsets.UTF_8)) {
            return lines.mapToLong(l -> {
                Matcher m = P.matcher(l);
                long c = 0;
                while (m.find()) c++;
                return c;
            }).sum();
        }
    }

    /** Main de prueba rápida: java ContarJava ruta/al/fichero.txt */
    public static void main(String[] args) throws Exception {
        Path ruta = args.length > 0 ? Path.of(args[0]) : Path.of("entrada.txt");
        long n = contar(ruta);
        System.out.println("Total 'Java' (palabra): " + n);
    }
}

