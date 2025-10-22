package es.cursojava.oo.ejercicios.colecciones.colegio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import es.cursojava.oo.ejercicios.colegio.Alumno;
import es.cursojava.utils.MiLogger;

public class InicioCole {

    // Para mostrar números con 2 decimales sin usar printf
    private static final DecimalFormat DF = new DecimalFormat("#0.00");

    public static void main(String[] args) {
        // Colegio 1 (7 alumnos: 2/1/4)
        Colegio c1 = new Colegio("Colegio Sakura", "C/ Jardines 1", new LinkedHashMap<>());
        c1.getAulas().put("aula1", new ArrayList<>(List.of(
                new Alumno("Ana", "López", "1111A", 8.4),
                new Alumno("Bruno", "Martín", "2222B", 6.7)
        )));
        c1.getAulas().put("aula2", new ArrayList<>(List.of(
                new Alumno("Carla", "Nieves", "3333C", 9.1)
        )));
        c1.getAulas().put("aula3", new ArrayList<>(List.of(
                new Alumno("Diego", "Ortega", "4444D", 5.9),
                new Alumno("Elena", "Pérez", "5555E", 7.3),
                new Alumno("Félix", "Quintana", "6666F", 9.4),
                new Alumno("Gabriela", "Ruiz", "7777G", 7.9)
        )));

        // Colegio 2
        Colegio c2 = new Colegio("Colegio Fuji", "Av. Montaña 8", new LinkedHashMap<>());
        c2.getAulas().put("aulaA", new ArrayList<>(List.of(
                new Alumno("Hugo", "Santos", "8888H", 7.2),
                new Alumno("Irene", "Téllez", "9999I", 8.0)
        )));
        c2.getAulas().put("aulaB", new ArrayList<>(List.of(
                new Alumno("Javier", "Ureña", "1010J", 6.1),
                new Alumno("Kevin", "Villalba", "1112K", 8.6)
        )));

        // Colegio 3
        Colegio c3 = new Colegio("Colegio Kumo", "Plaza del Sol 3", new LinkedHashMap<>());
        c3.getAulas().put("aulaX", new ArrayList<>(List.of(
                new Alumno("Laura", "Wang", "1212L", 7.5),
                new Alumno("Marta", "Xu", "1313M", 6.8),
                new Alumno("Nora", "Yamada", "1414N", 8.9)
        )));
        c3.getAulas().put("aulaY", new ArrayList<>(List.of(
                new Alumno("Óscar", "Zafra", "1515O", 7.1)
        )));

        List<Colegio> colegios = List.of(c1, c2, c3);

        // 1) Listado (sin printf)
        MiLogger.info("===== LISTADO DE COLEGIOS, AULAS Y ALUMNOS =====");
        for (Colegio col : colegios) {
            MiLogger.info("Colegio: " + col.getNombre());
            MiLogger.info("Dirección: " + col.getDireccion());
            if (col.getAulas().isEmpty()) {
                MiLogger.info("  (Sin aulas)");
            } else {
                for (Map.Entry<String, List<Alumno>> e : col.getAulas().entrySet()) {
                    String aula = e.getKey();
                    List<Alumno> alumnos = e.getValue();
                    MiLogger.info("  [" + aula + "]");
                    if (alumnos.isEmpty()) {
                        MiLogger.info("    (Sin alumnos)");
                    } else {
                        for (Alumno a : alumnos) {
                            MiLogger.info("    - " + a.getNombre() + " " + a.getApellido()
                                    + " | media: " + DF.format(a.getNotaMedia()));
                        }
                    }
                }
            }
        }

        // 2) Colegio con mayor media
        Colegio mejor = null;
        double mejorMedia = -1.0;
        for (Colegio col : colegios) {
            double media = mediaColegioSegura(col); // bucles clásicos
            if (media > mejorMedia) {
                mejorMedia = media;
                mejor = col;
            }
        }

        if (mejor == null) {
            MiLogger.info("No hay colegios.");
        } else {
            MiLogger.info("Colegio con mayor media: " + mejor.getNombre()
                    + " (media=" + DF.format(mejorMedia) + ")");
        }
    }

    // Media de notas del colegio. Si no hay alumnos, devolvemos -1.
    private static double mediaColegioSegura(Colegio col) {
        double suma = 0.0;
        int contador = 0;

        for (List<Alumno> lista : col.getAulas().values()) {
            for (Alumno a : lista) {
                suma += a.getNotaMedia();
                contador++;
            }
        }

        return (contador == 0) ? -1.0 : (suma / contador);
    }
}

