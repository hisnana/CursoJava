package es.cursojava.oo.ejercicios.colecciones.colegio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import es.cursojava.oo.ejercicios.colegio.Alumno;

public class InicioCole {

	 // === Lógica ===
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

        // 1) Recorrer la lista de colegios y mostrar:
        //    - datos del colegio
        //    - nombre de cada aula
        //    - por cada aula: nombre, apellido y nota media de sus alumnos
        System.out.println("===== LISTADO DE COLEGIOS, AULAS Y ALUMNOS =====");
        for (Colegio col : colegios) {
            System.out.println("Colegio: " + col.getNombre());
            System.out.println("Dirección: " + col.getDireccion());
            if (col.getAulas().isEmpty()) {
                System.out.println("  (Sin aulas)");
            } else {
                for (Map.Entry<String, List<Alumno>> e : col.getAulas().entrySet()) {
                    String aula = e.getKey();
                    List<Alumno> alumnos = e.getValue();
                    System.out.println("  [" + aula + "]");
                    if (alumnos.isEmpty()) {
                        System.out.println("    (Sin alumnos)");
                    } else {
                        for (Alumno a : alumnos) {
                            System.out.printf("    - %s %s | media: %.2f%n",
                                    a.getNombre(), a.getApellido(), a.getNotaMedia());
                        }
                    }
                }
            }
            System.out.println();
        }

        // 2) Indicar cuál es el colegio que tiene la nota media de alumnos mayor.
        //    (Se calcula la media de todas las notas de los alumnos del colegio)
        Colegio mejor = colegios.stream()
                .max(Comparator.comparingDouble(InicioCole::mediaColegioSegura))
                .orElse(null);

        if (mejor == null) {
            System.out.println("No hay colegios.");
        } else {
            double media = mediaColegioSegura(mejor);
            System.out.printf("Colegio con mayor media: %s (media=%.2f)%n", mejor.getNombre(), media);
        }
    }

    // Media de notas del colegio. Si no hay alumnos, devolvemos -1 para que no gane.
    private static double mediaColegioSegura(Colegio col) {
        OptionalDouble avg = col.getAulas().values().stream()
                .flatMap(List::stream)
                .mapToDouble(Alumno::getNotaMedia)
                .average();
        return avg.isPresent() ? avg.getAsDouble() : -1.0;
    }
}


