package es.cursojava.utils;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MiLogger {

    private static final String RUTA_LOG = "app.log";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

 // Método privado y estático que obtiene el nombre simple de la clase que llamó al método 'log' (u otro método)
 // Es útil para fines de registro o debug, para saber quién hizo la llamada.
 private static String obtenerClaseLlamante() {
     // Obtiene la pila de llamadas del hilo actual como un arreglo de StackTraceElement
     // Cada elemento representa una llamada en la pila, desde el método más reciente hacia atrás.
     // La pila típicamente se ve así (índices):
     // [0] Thread.currentThread().getStackTrace()
     // [1] obtenerClaseLlamante() - este método actual
     // [2] log() - método que llamó a obtenerClaseLlamante()
     // [3] método que llamó a log()
     // [4] método que llamó al método en [3], y así sucesivamente
     StackTraceElement[] stack = Thread.currentThread().getStackTrace();

     // Verificamos que la pila tenga al menos 5 elementos para poder acceder al índice 4 sin error.
     if (stack.length >= 5) {
         // En la posición 4 tenemos el método que llamó a 'log' (o quien nos interesa).
         // Obtenemos el nombre completo de la clase que está ahí (incluye paquete)
         String fullClassName = stack[4].getClassName();

         // Para simplificar, extraemos solo el nombre simple de la clase (sin el paquete).
         // Buscamos la última aparición del punto '.' para separar el paquete del nombre de la clase.
         int ultimoPunto = fullClassName.lastIndexOf('.');

         // Si encontramos un punto, devolvemos solo la parte después de él (el nombre simple)
         // Si no hay punto, devolvemos el nombre completo (ya es simple)
         return (ultimoPunto >= 0) ? fullClassName.substring(ultimoPunto + 1) : fullClassName;
     }

     // Si la pila no tiene suficientes elementos (caso raro), devolvemos "Desconocida"
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


