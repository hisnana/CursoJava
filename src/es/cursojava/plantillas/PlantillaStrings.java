package es.cursojava.plantillas;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

// Opcional: si no usas tu logger, elimina estas dos líneas.
import es.cursojava.utils.MiLogger;

public final class PlantillaStrings {

    private PlantillaStrings() {}

    /** Devuelve "" si s es null. */
    public static String nvl(String s) {
        return s == null ? "" : s;
    }

    /** Trim y colapsa espacios internos múltiples a uno solo. */
    public static String limpiarEspacios(String s) {
        s = nvl(s).trim();
        return s.replaceAll("\\s+", " ");
    }

    /** a minúsculas con locale español. */
    public static String minusculasEs(String s) {
        return nvl(s).toLowerCase(new Locale("es", "ES"));
    }

    /** a mayúsculas con locale español. */
    public static String mayusculasEs(String s) {
        return nvl(s).toUpperCase(new Locale("es", "ES"));
    }

    /** Elimina tildes/diacríticos (áéíóúñ -> aeioun; ñ se mantiene como n si lo deseas: aquí la mantenemos). */
    public static String quitarTildes(String s) {
        s = nvl(s);
        String norm = Normalizer.normalize(s, Normalizer.Form.NFD);
        return norm.replaceAll("\\p{M}+", ""); // quita marcas diacríticas
    }

    /** Solo caracteres alfanuméricos (opcionalmente conserva espacios si keepSpaces=true). */
    public static String soloAlfanumerico(String s, boolean keepSpaces) {
        s = nvl(s);
        StringBuilder out = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c) || (keepSpaces && Character.isSpaceChar(c))) {
                out.append(c);
            }
        }
        return out.toString();
    }

    /** Reemplazo simple (no regex). */
    public static String reemplazar(String s, String buscar, String por) {
        return nvl(s).replace(nvl(buscar), nvl(por));
    }

    /** Reemplazo con regex (usa Pattern.quote si quieres tratar 'regex' como literal). */
    public static String reemplazarRegex(String s, String regex, String por) {
        return nvl(s).replaceAll(regex, nvl(por));
    }

    /** Capitaliza cada palabra (tipo título sencillo). */
    public static String capitalizarPalabras(String s) {
        s = limpiarEspacios(s.toLowerCase(new Locale("es", "ES")));
        if (s.isEmpty()) return s;
        String[] partes = s.split(" ");
        for (int i = 0; i < partes.length; i++) {
            String p = partes[i];
            if (!p.isEmpty()) {
                partes[i] = Character.toUpperCase(p.charAt(0)) + (p.length() > 1 ? p.substring(1) : "");
            }
        }
        return String.join(" ", partes);
    }

    /** Invierte el string. */
    public static String invertir(String s) {
        return new StringBuilder(nvl(s)).reverse().toString();
    }

    /** Substring seguro: si índices se salen, recorta; si fin < inicio, devuelve "". */
    public static String cortarEntre(String s, int inicioIncl, int finExcl) {
        s = nvl(s);
        int ini = Math.max(0, inicioIncl);
        int fin = Math.min(s.length(), finExcl);
        if (fin <= ini) return "";
        return s.substring(ini, fin);
    }

    /** Padding a la izquierda hasta longitud 'len' con char 'c'. */
    public static String padLeft(String s, int len, char c) {
        s = nvl(s);
        if (s.length() >= len) return s;
        StringBuilder sb = new StringBuilder(len);
        for (int i = s.length(); i < len; i++) sb.append(c);
        sb.append(s);
        return sb.toString();
    }

    /** Padding a la derecha hasta longitud 'len' con char 'c'. */
    public static String padRight(String s, int len, char c) {
        s = nvl(s);
        if (s.length() >= len) return s;
        StringBuilder sb = new StringBuilder(len);
        sb.append(s);
        for (int i = s.length(); i < len; i++) sb.append(c);
        return sb.toString();
    }

    /** Split seguro por separador literal (no regex). */
    public static String[] partirPor(String s, String separadorLiteral) {
        s = nvl(s);
        String sep = Pattern.quote(nvl(separadorLiteral));
        return s.split(sep, -1); // -1 conserva vacíos
    }

    /** Join sin Streams. */
    public static String unirCon(List<String> partes, String separador) {
        if (partes == null || partes.isEmpty()) return "";
        String sep = nvl(separador);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partes.size(); i++) {
            if (i > 0) sb.append(sep);
            sb.append(nvl(partes.get(i)));
        }
        return sb.toString();
    }

    /** Ejemplo de uso básico de StringBuilder (mutaciones eficientes). */
    public static String construirFrase(String... trozos) {
        StringBuilder sb = new StringBuilder();
        for (String t : trozos) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(nvl(t));
        }
        return sb.toString();
    }

    /** Log opcional para ver quién llama (usa tu MiLogger). */
    public static void logDemo(String mensaje) {
        // Si no usas MiLogger, elimina esta línea.
        MiLogger.info("PlantillaStrings: " + nvl(mensaje));
    }
}
