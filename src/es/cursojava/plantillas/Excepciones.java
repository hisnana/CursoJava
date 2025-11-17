package es.cursojava.plantillas;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

// Opcional: usa tu logger si lo tienes
import es.cursojava.utils.MiLogger;

/**
 * 🧠 RESUMEN RÁPIDO
 * Throwable
 * ├─ Error                ← NO lo captures (errores de JVM)
 * └─ Exception
 *    ├─ RuntimeException  ← "unchecked" (no obliga try/throws). Ej: NPE, IAE, AIOOBE, NumberFormatException, InputMismatchException...
 *    └─ (resto)           ← "checked" (OBLIGA try/catch o throws). Ej: IOException, SQLException, ClassNotFoundException...
 *
 * Reglas:
 * - Si sobreescribes equals → sobreescribe hashCode (no aplica aquí, pero recuerda).
 * - Si capturas, haz algo ÚTIL (log, transformar, recuperar) o propaga.
 * - Prefiere validar con if + IllegalArgumentException a usar try/catch para control de flujo.
 * - try-with-resources para cerrar automáticamente (JDBC, ficheros…)
 * 
 * 
 * Checked (obliga try/throws): IOException, SQLException, ParseException…
Unchecked: RuntimeException y subclases → NullPointerException, IllegalArgumentException, IndexOutOfBoundsException, InputMismatchException, NumberFormatException…
throw: cuando detectas un estado inválido.
try/catch: si puedes recuperar, registrar o traducir la excepción; si no, propaga.
Propagación: public void x() throws IOException { ... }
Multi-catch: catch (IOException | SQLException e) { ... }
try-with-resources: cierra solo (AutoCloseable).
Logging: mejor logger que printStackTrace(). Si lo necesitas como texto: stackTraceComoString(e).
Buenas prácticas: mensajes claros con datos clave, no tragues excepciones vacías, evita lógica en finally, no captures Error.
 */
public class Excepciones {

    /* ============================
     * 1) VALIDACIONES DE ENTRADA
     * ============================
     * Cuándo: antes de ejecutar lógica; evita estados inválidos.
     */
    public static int requierePositivoNoCero(int n) {
        if (n <= 0) {
            // Mensaje claro + dato problemático
            throw new IllegalArgumentException("n debe ser > 0; valor recibido=" + n);
        }
        return n;
    }

    public static <T> T requiereNoNulo(T obj, String nombre) {
        // Equivalente a Objects.requireNonNull(obj, "mensaje");
        if (obj == null) {
            throw new NullPointerException(nombre + " no puede ser null");
        }
        return obj;
    }

    /* ============================
     * 2) THROW vs TRY/CATCH
     * ============================
     * - throw: cuando detectas un estado inválido y QUIERES cortar el flujo aquí.
     * - try/catch: cuando SABES recuperarte o quieres transformar/registrar y volver a lanzar.
     */

    // Ejemplo: delego a quien llame (propagación con "throws")
    public static String leerPrimeraLinea(String ruta) throws IOException { // <- checked → obliga a quien llama
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            return br.readLine();
        }
    }

    // Capturo checked y lo "envuelvo" en unchecked del dominio (ApplicationException)
    public static String leerPrimeraLineaSegura(String ruta) {
        try {
            return leerPrimeraLinea(ruta);
        } catch (IOException e) {
            // Log y encadenado (cause) para no perder el stack original
            // MiLogger.error("Error leyendo " + ruta, e);
            throw new AppRuntimeException("No se pudo leer el archivo: " + ruta, e);
        }
    }

    /* ====================================
     * 3) MULTI-CATCH, ORDEN Y RE-THROW
     * ====================================
     * - Captura primero las específicas y al final las genéricas.
     * - Multi-catch con | cuando la acción es la misma.
     */
    public static int parsearEntero(String s) {
        try {
            requiereNoNulo(s, "s");
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException | NullPointerException e) {
            // MiLogger.warn("Entrada inválida: " + s, e);
            throw new IllegalArgumentException("El texto no es un entero válido: '" + s + "'", e);
        }
    }

    /* ============================
     * 4) TRY-WITH-RESOURCES (JDBC)
     * ============================
     * Cierra solo conexiones/recursos que implementan AutoCloseable.
     * Si hay fallo, intenta rollback y vuelve a lanzar.
     */
    public static void ejemploJdbc(String url, String user, String pass) {
        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            con.setAutoCommit(false);
            // ... tu lógica SQL (PreparedStatement, etc.)
            con.commit();
        } catch (SQLException e) {
            // No tenemos el Connection aquí (ya salió del scope si falló al abrir),
            // en código real captura dentro para poder con.rollback() si procede.
            // MiLogger.error("Fallo JDBC", e);
            throw new AppRuntimeException("Fallo JDBC", e);
        }
    }

    /* ============================
     * 5) FINALLY
     * ============================
     * - Se ejecuta SIEMPRE (haya o no excepción) salvo System.exit/kill.
     * - Úsalo para liberar recursos si no puedes usar try-with-resources.
     * - ⚠️ Evita lanzar excepciones nuevas en finally (ocultan la original).
     */
    public static void ejemploFinally(File f) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            // ... usar
        } catch (IOException e) {
            // MiLogger.error("No se pudo abrir: " + f, e);
            throw new AppRuntimeException("No se pudo abrir: " + f, e);
        } finally {
            if (fis != null) {
                try { fis.close(); } catch (IOException ignore) { /* log si quieres */ }
            }
        }
    }

    /* =========================================
     * 6) LEER CONSOLA SIN InputMismatchException
     * =========================================
     */
    public static int leerEnteroSeguro(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {                 // evita InputMismatchException
            String basura = sc.next();
            System.out.println("Introduce un entero (descartado: " + basura + ")");
        }
        return sc.nextInt();
    }

    /* =========================================
     * 7) STACK TRACE → a String (para loggers)
     * =========================================
     */
    public static String stackTraceComoString(Throwable t) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);                 // imprime en nuestro buffer
            return sw.toString();
        }
    }

    /* =========================================
     * 8) DEMO de uso
     * =========================================
     */
    public static void main(String[] args) {
        // Validación típica (no uses try/catch para validar argumentos)
        int n = requierePositivoNoCero(5);

        // Multi-catch + transformación a mensaje de negocio
        try {
            System.out.println(parsearEntero(" 123 "));
            System.out.println(parsearEntero("abc"));      // lanzará IAE
        } catch (IllegalArgumentException e) {
            System.out.println("Error de entrada: " + e.getMessage());
            // MiLogger.warn(stackTraceComoString(e));
        }

        // Scanner sin InputMismatch
        try (Scanner sc = new Scanner(System.in)) {
            int edad = leerEnteroSeguro(sc, "Edad: ");
            System.out.println("Edad ok=" + edad);
        }

        // Checked → propagado con throws
        try {
            System.out.println(leerPrimeraLinea("inexistente.txt"));
        } catch (IOException e) {
            System.out.println("No se pudo leer: " + e.getMessage());
        }

        // Checked → envuelto en unchecked de aplicación
        try {
            System.out.println(leerPrimeraLineaSegura("inexistente.txt"));
        } catch (AppRuntimeException e) {
            System.out.println("Fallo app: " + e.getMessage());
        }

        // Ejemplo “café frío” que me enseñaste
        comprobarTemperaturaCafe(15); // lanzará TooColdTemperatureException
    }

    /* =========================================
     * 9) EJEMPLO custom (tu ejemplo del café)
     * =========================================
     */
    public static void comprobarTemperaturaCafe(int grados) {
        if (grados < 20) {
            throw new TooColdTemperatureException("La temperatura del café es menor a 20º: " + grados);
        }
    }
}

/* ================================
 * EXCEPCIONES PROPIAS (plantillas)
 * ================================
 */

/**
