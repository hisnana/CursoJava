package es.cursojava.streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase plantilla para entender y practicar Java Streams.
 *
 * Cambia el package a lo que uses en tu repo.
 *
 * NOTA:
 * - He usado System.out.println para que lo veas claro.
 * - Si quieres, puedes cambiarlo por log.info(...) con tu logger.
 */
public class PlantillaStreams {

    public static void main(String[] args) {

        ejemploBasicoMap();
        ejemploFilter();
        ejemploFilterYMap();
        ejemploSortYLimit();
        ejemploMapAEmpleadoDto();
    }

    /**
     * Ejemplo 1:
     * - Duplicar números de una lista usando stream().map(...)
     */
    public static void ejemploBasicoMap() {
        System.out.println("=== Ejemplo básico: map (duplicar números) ===");

        // 1) Lista de entrada
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // 2) Stream:
        //    - numeros.stream() → convierte la List<Integer> en Stream<Integer>
        //    - .map(n -> n * 2) → transforma cada número 'n' en 'n*2'
        //    - .collect(...) → recoge el resultado en una nueva List<Integer>
        List<Integer> numerosDobles = numeros.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println("Original: " + numeros);
        System.out.println("Doblados: " + numerosDobles);
        System.out.println();
    }

    /**
     * Ejemplo 2:
     * - Filtrar solo números pares usando filter(...)
     */
    public static void ejemploFilter() {
        System.out.println("=== Ejemplo: filter (quedarnos con pares) ===");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        // .filter(...) recibe un "predicado": una función que devuelve boolean
        // e -> e % 2 == 0 → solo pasan los que cumplan esto (los pares)
        List<Integer> pares = numeros.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Original: " + numeros);
        System.out.println("Pares:    " + pares);
        System.out.println();
    }

    /**
     * Ejemplo 3:
     * - Combinar filter + map en una misma "tubería" (pipeline)
     */
    public static void ejemploFilterYMap() {
        System.out.println("=== Ejemplo: filter + map (pares * 10) ===");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        // PASO A PASO:
        // 1) stream() → Stream<Integer>
        // 2) filter(e -> e % 2 == 0) → nos quedamos solo con los pares
        // 3) map(e -> e * 10) → transformamos cada par en par*10
        // 4) collect(toList()) → volvemos a tener una List<Integer>
        List<Integer> resultado = numeros.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 10)
                .collect(Collectors.toList());

        System.out.println("Original:   " + numeros);
        System.out.println("Pares*10:   " + resultado);
        System.out.println();
    }

    /**
     * Ejemplo 4:
     * - ordenar, limitar resultados y recorrer con forEach
     */
    public static void ejemploSortYLimit() {
        System.out.println("=== Ejemplo: sorted + limit + forEach ===");

        List<String> nombres = Arrays.asList("Ana", "Luis", "Pedro", "Lucía", "Carlos");

        // Queremos:
        // 1) Ordenar alfabéticamente
        // 2) Quedarnos solo con los 3 primeros
        // 3) Imprimirlos por pantalla
        nombres.stream()
                .sorted()                // orden natural (alfabético)
                .limit(3)                // nos quedamos con los 3 primeros
                .forEach(n -> System.out.println("Nombre: " + n));
        // forEach(...) → operación terminal: recorre cada elemento y ejecuta el println

        System.out.println();
    }

    /**
     * Para este ejemplo creamos dos clases internas simples para simular entity y DTO.
     * Es una mini versión de tu Empleado / EmpleadoDto.
     */
    private static class Empleado {
        private Long id;
        private String nombre;
        private String departamento;
        private BigDecimal salario;

        public Empleado(Long id, String nombre, String departamento, BigDecimal salario) {
            this.id = id;
            this.nombre = nombre;
            this.departamento = departamento;
            this.salario = salario;
        }

        public Long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDepartamento() {
            return departamento;
        }

        public BigDecimal getSalario() {
            return salario;
        }
    }

    private static class EmpleadoDto {
        private Long id;
        private String nombre;
        private String departamento;

        public EmpleadoDto(Long id, String nombre, String departamento) {
            this.id = id;
            this.nombre = nombre;
            this.departamento = departamento;
        }

        @Override
        public String toString() {
            return "EmpleadoDto{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", departamento='" + departamento + '\'' +
                    '}';
        }
    }

    /**
     * Ejemplo 5:
     * - Simular lo que haces en el servicio:
     *   List<Empleado> → Stream → List<EmpleadoDto>
     * - Usamos map(...) para convertir entity → DTO
     */
    public static void ejemploMapAEmpleadoDto() {
        System.out.println("=== Ejemplo: map de Entity a DTO (como en el servicio) ===");

        // Lista de "empleados" simulada (entity)
        List<Empleado> empleados = Arrays.asList(
                new Empleado(1L, "Ana", "IT", new BigDecimal("1800")),
                new Empleado(2L, "Luis", "VENTAS", new BigDecimal("2000")),
                new Empleado(3L, "Lucía", "IT", new BigDecimal("2200"))
        );

        // 1) empleados.stream() → Stream<Empleado>
        // 2) filter(e -> "IT".equals(e.getDepartamento())) → nos quedamos solo con IT
        // 3) map(e -> new EmpleadoDto(...)) → convertimos Empleado → EmpleadoDto
        // 4) sorted(...) → ordenamos los DTO por nombre
        // 5) collect(toList()) → List<EmpleadoDto>
        List<EmpleadoDto> empleadosItDto = empleados.stream()
                .filter(e -> "IT".equals(e.getDepartamento()))
                .map(e -> new EmpleadoDto(e.getId(), e.getNombre(), e.getDepartamento()))
                .sorted(Comparator.comparing(dto -> dto.nombre))
                .collect(Collectors.toList());

        System.out.println("Empleados IT (DTO):");
        empleadosItDto.forEach(dto -> System.out.println(" - " + dto));
        System.out.println();
    }
}
