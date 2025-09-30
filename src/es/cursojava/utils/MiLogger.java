package es.cursojava.utils;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MiLogger {

    private static final String RUTA_LOG = "app.log";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Método privado para obtener la clase llamante
    private static String obtenerClaseLlamante() {
        // La pila de llamadas: [0] getStackTrace, [1] obtenerClaseLlamante, [2] log, [3] método que llamó a log
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        if (stack.length >= 5) {
            // El elemento 4 es el que llamó a log()
            String fullClassName = stack[4].getClassName();
            // Puedes devolver solo el nombre simple de la clase (sin paquete)
            int ultimoPunto = fullClassName.lastIndexOf('.');
            return (ultimoPunto >= 0) ? fullClassName.substring(ultimoPunto + 1) : fullClassName;
        }
        return "Desconocida";
    }

    public static void log(String nivel, String mensaje) {
        String timestamp = LocalDateTime.now().format(FORMATO_FECHA);
        String clase = obtenerClaseLlamante();
        String logEntry = String.format("%s [%s] (%s): %s", timestamp, nivel, clase, mensaje);

        System.out.println(logEntry);

        try (FileWriter fw = new FileWriter(RUTA_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logEntry);
        } catch (IOException e) {
            System.err.println("Error al escribir el log: " + e.getMessage());
        }
    }

    public static void info(String mensaje) {
        log("INFO", mensaje);
    }

    public static void warning(String mensaje) {
        log("WARNING", mensaje);
    }

    public static void error(String mensaje) {
        log("ERROR", mensaje);
    }
}


