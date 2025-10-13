package es.cursojava.oo.ejercicios.colecciones.colegio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import es.cursojava.oo.ejercicios.colegio.Alumno;
import es.cursojava.utils.MiLogger;

public class Inicio {

	public static void main(String[] args) {
        // ---------- EJERCICIO 1 ----------
        Colegio colegioPrincipal = construirColegioPrincipal(); // 7 alumnos: 2/1/4
        MiLogger.info("=========== EJERCICIO 1 ===========");
        ejecutarEjercicio1(colegioPrincipal);

        // ---------- EJERCICIO 2 ----------
        Colegio c2 = construirColegio2();
        Colegio c3 = construirColegio3();
        List<Colegio> colegios = Arrays.asList(colegioPrincipal, c2, c3);

        MiLogger.info("=========== EJERCICIO 2 ===========");
        ejecutarEjercicio2(colegios);
    }

    // ===== Datos (sin streams) =====
    private static Colegio construirColegioPrincipal() {
        Map<String, List<Alumno>> aulas = new LinkedHashMap<>();

        Alumno alumno1 = new Alumno("alumno1", "Apellido1", "1111A", 8);
        Alumno alumno2 = new Alumno("alumno2", "Apellido2", "2222B", 4);
        Alumno alumno3 = new Alumno("alumno3", "Apellido3", "3333C", 7);
        Alumno alumno4 = new Alumno("alumno4", "Apellido4", "4444D", 5);
        Alumno alumno5 = new Alumno("alumno5", "Apellido5", "5555E", 9);
        Alumno alumno6 = new Alumno("alumno6", "Apellido6", "6666F", 6);
        Alumno alumno7 = new Alumno("alumno7", "Apellido7", "7777G", 3);

        aulas.put("aula1", new ArrayList<Alumno>(Arrays.asList(alumno1, alumno2)));
        aulas.put("aula2", new ArrayList<Alumno>(Arrays.asList(alumno3)));
        aulas.put("aula3", new ArrayList<Alumno>(Arrays.asList(alumno4, alumno5, alumno6, alumno7)));

        return new Colegio("Colegio Principal", "C/ Jardines 1", aulas);
    }

    private static Colegio construirColegio2() {
        Map<String, List<Alumno>> aulas = new LinkedHashMap<>();
        aulas.put("aulaA", new ArrayList<Alumno>(Arrays.asList(
                new Alumno("Hugo", "Santos", "8888H", 7.2),
                new Alumno("Irene", "Téllez", "9999I", 8.0)
        )));
        aulas.put("aulaB", new ArrayList<Alumno>(Arrays.asList(
                new Alumno("Javier", "Ureña", "1010J", 6.1),
                new Alumno("Kevin", "Villalba", "1112K", 8.6)
        )));
        return new Colegio("Colegio Fuji", "Av. Montaña 8", aulas);
    }

    private static Colegio construirColegio3() {
        Map<String, List<Alumno>> aulas = new LinkedHashMap<>();
        aulas.put("aulaX", new ArrayList<Alumno>(Arrays.asList(
                new Alumno("Laura", "Wang", "1212L", 7.5),
                new Alumno("Marta", "Xu", "1313M", 6.8),
                new Alumno("Nora", "Yamada", "1414N", 8.9)
        )));
        aulas.put("aulaY", new ArrayList<Alumno>(Arrays.asList(
                new Alumno("Óscar", "Zafra", "1515O", 7.1)
        )));
        return new Colegio("Colegio Kumo", "Plaza del Sol 3", aulas);
    }

    // ====== Lógica EJERCICIO 1 (solo bucles) ======
    private static void ejecutarEjercicio1(Colegio colegio) {
        Map<String, List<Alumno>> aulas = colegio.getAulas();

        // 1) Número de aulas
        MiLogger.info("1) Nº de aulas de " + colegio.getNombre() + ": " + aulas.size());


        // 2) Datos de los alumnos por aula
        MiLogger.info("2) Datos de los alumnos por aula");
        imprimirAlumnosPorAula_Datos(aulas);


        // 3) Conteo por aula
        MiLogger.info("3) Conteo por aula");
        imprimirConteosPorAula(aulas);


        // 4) Pedir aula por teclado y mostrar alumnos
        Scanner sc = new Scanner(System.in);
        System.out.print("4) Introduce el nombre de un aula (p.ej., aula1): ");
        String aulaBuscada = sc.nextLine().trim();
        mostrarAlumnosDeAula(aulas, aulaBuscada);

        // 5) Aula que contiene al alumno con la mejor nota
        MiLogger.info("5) Aula(s) con el alumno de mayor nota");
        mostrarAulaConMejorAlumno(aulas);
        // sc.close();  // opcional (si cierras aquí, no podrás reutilizar Scanner más tarde)
    }

    private static void imprimirAlumnosPorAula_Datos(Map<String, List<Alumno>> aulas) {
        for (Map.Entry<String, List<Alumno>> e : aulas.entrySet()) {
            String aula = e.getKey();
            List<Alumno> alumnos = e.getValue();
            MiLogger.info("[" + aula + "]");
            if (alumnos == null || alumnos.isEmpty()) {
                MiLogger.info("  (sin alumnos)");
            } else {
                for (int i = 0; i < alumnos.size(); i++) {
                    Alumno a = alumnos.get(i);
                    MiLogger.info("  - " + a.toString());
                }
            }
        }
    }

    private static void imprimirConteosPorAula(Map<String, List<Alumno>> aulas) {
        for (Map.Entry<String, List<Alumno>> e : aulas.entrySet()) {
            String aula = e.getKey();
            List<Alumno> alumnos = e.getValue();
            int n = (alumnos == null) ? 0 : alumnos.size();
            if (n == 1) {
                MiLogger.info(aula + " -> 1 alumno");
            } else {
                MiLogger.info(aula + " -> " + n + " alumnos");
            }
        }
    }

    private static void mostrarAlumnosDeAula(Map<String, List<Alumno>> aulas, String aulaBuscada) {
        String claveReal = null;
        for (String k : aulas.keySet()) {
            if (k.equalsIgnoreCase(aulaBuscada)) {
                claveReal = k;
                break;
            }
        }
        if (claveReal == null) {
            MiLogger.info("El aula '" + aulaBuscada + "' no existe.");
            return;
        }

        List<Alumno> alumnos = aulas.get(claveReal);
        MiLogger.info("Alumnos en " + claveReal + ":");
        if (alumnos == null || alumnos.isEmpty()) {
            MiLogger.info("  (sin alumnos)");
        } else {
            for (int i = 0; i < alumnos.size(); i++) {
                Alumno a = alumnos.get(i);
                MiLogger.info("  - " + a.getNombre() + " " + a.getApellido() +
                                   " (DNI " + a.getDni() + ") | media: " + a.getNotaMedia());
            }
        }
    }

    private static void mostrarAulaConMejorAlumno(Map<String, List<Alumno>> aulas) {
        double mejorNota = -1.0;
        List<String> aulasConMejor = new ArrayList<String>();
        List<Alumno> alumnosTop = new ArrayList<Alumno>();

        for (Map.Entry<String, List<Alumno>> e : aulas.entrySet()) {
            String aula = e.getKey();
            List<Alumno> alumnos = e.getValue();
            if (alumnos == null) continue;

            for (int i = 0; i < alumnos.size(); i++) {
                Alumno a = alumnos.get(i);
                double nota = a.getNotaMedia();

                if (nota > mejorNota) {
                    mejorNota = nota;
                    aulasConMejor.clear();
                    alumnosTop.clear();
                    aulasConMejor.add(aula);
                    alumnosTop.add(a);
                } else if (nota == mejorNota) {
                    aulasConMejor.add(aula);
                    alumnosTop.add(a);
                }
            }
        }

        if (alumnosTop.isEmpty()) {
            MiLogger.info("No hay alumnos.");
            return;
        }

        MiLogger.info("Mejor nota: " + mejorNota);
        for (int i = 0; i < alumnosTop.size(); i++) {
            Alumno a = alumnosTop.get(i);
            String aula = aulasConMejor.get(i);
            MiLogger.info(" - Aula: " + aula + " | " + a.getNombre() + " " + a.getApellido());
        }
    }

    // ====== Lógica EJERCICIO 2 (solo bucles) ======
    private static void ejecutarEjercicio2(List<Colegio> colegios) {
        // 1) Mostrar datos por colegio -> aulas -> alumnos (nombre, apellido, media)
        MiLogger.info("Listado de colegios, aulas y alumnos:");
        for (int i = 0; i < colegios.size(); i++) {
            Colegio col = colegios.get(i);
            MiLogger.info("Colegio: " + col.getNombre());
            MiLogger.info("Dirección: " + col.getDireccion());

            Map<String, List<Alumno>> aulas = col.getAulas();
            if (aulas.isEmpty()) {
                MiLogger.info("  (Sin aulas)");
            } else {
                for (Map.Entry<String, List<Alumno>> e : aulas.entrySet()) {
                    String aula = e.getKey();
                    List<Alumno> alumnos = e.getValue();
                    MiLogger.info("  [" + aula + "]");
                    if (alumnos == null || alumnos.isEmpty()) {
                        MiLogger.info("    (Sin alumnos)");
                    } else {
                        for (int j = 0; j < alumnos.size(); j++) {
                            Alumno a = alumnos.get(j);
                            MiLogger.info("    - " + a.getNombre() + " " + a.getApellido() +
                                               " | media: " + a.getNotaMedia());
                        }
                    }
                }
            }

        }

        // 2) Colegio con mayor media de alumnos
        Colegio mejorColegio = null;
        double mejorMedia = -1.0;

        for (int i = 0; i < colegios.size(); i++) {
            Colegio col = colegios.get(i);

            double suma = 0.0;
            int cuenta = 0;

            Map<String, List<Alumno>> aulas = col.getAulas();
            for (Map.Entry<String, List<Alumno>> e : aulas.entrySet()) {
                List<Alumno> alumnos = e.getValue();
                if (alumnos == null) continue;

                for (int j = 0; j < alumnos.size(); j++) {
                    Alumno a = alumnos.get(j);
                    suma += a.getNotaMedia();
                    cuenta++;
                }
            }

            double mediaCol;
            if (cuenta == 0) {
                mediaCol = -1.0; // sin alumnos, no compite
            } else {
                mediaCol = suma / cuenta;
            }

            if (mediaCol > mejorMedia) {
                mejorMedia = mediaCol;
                mejorColegio = col;
            }
        }

        if (mejorColegio == null) {
            MiLogger.info("No hay colegios.");
        } else {
            MiLogger.info("Colegio con mayor media: " + mejorColegio.getNombre() +
                               " (media=" + mejorMedia + ")");
        }
    }
}



