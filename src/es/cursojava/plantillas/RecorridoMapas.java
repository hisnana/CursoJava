package es.cursojava.plantillas;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Plantilla de recorridos sobre Map<K,V> con "cuándo usar".
 *
 * Nota de orden:
 * - HashMap: sin orden garantizado.
 * - LinkedHashMap: conserva orden de inserción.
 * - TreeMap: orden natural o Comparator.
 * - ConcurrentHashMap: acceso concurrente (no bloqueante por entrada).
 */
public final class RecorridoMapas {

    private RecorridoMapas() {}

    public static void main(String[] args) {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("Ana", 3);
        mapa.put("Cris", 5);
        mapa.put("Isma", 2);

        recorrerEntrySetForEach(mapa);
        recorrerKeySetMasGet(mapa);
        recorrerSoloValues(mapa);
        recorrerMapForEach(mapa);
        recorrerStreamFiltrarOrdenar(mapa);
        ordenarPorValorDesc(mapa);

        eliminarMientrasIteras(mapa, e -> e.getValue() < 3); // elimina Ana(3? no) e Isma(2 sí)
        eliminarConRemoveIf(new LinkedHashMap<>(mapa), e -> e.getKey().startsWith("C"));

        ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>(mapa);
        recorrerConcurrentForEach(chm);
    }

    // 1) entrySet + for-each
    // CUÁNDO USAR: necesitas "clave y valor" y quieres rendimiento simple O(n). Es la opción por defecto.
    public static <K, V> void recorrerEntrySetForEach(Map<K, V> map) {
        for (Map.Entry<K, V> e : map.entrySet()) {
            K k = e.getKey();
            V v = e.getValue();
            // ... tu lógica
            // System.out.println(k + " -> " + v);
        }
    }

    // 2) keySet + get(key)
    // CUÁNDO USAR: ya tienes funciones que operan por clave y ocasionalmente miras el valor.
    // NOTA: implica un get extra; en maps muy grandes, entrySet suele ser más eficiente.
    public static <K, V> void recorrerKeySetMasGet(Map<K, V> map) {
        for (K k : map.keySet()) {
            V v = map.get(k);
            // ... tu lógica
        }
    }

    // 3) values()
    // CUÁNDO USAR: solo te importan los valores; no necesitas la clave.
    public static <V> void recorrerSoloValues(Map<?, V> map) {
        for (V v : map.values()) {
            // ... tu lógica solo con el valor
        }
    }

    // 4) Map.forEach(BiConsumer)
    // CUÁNDO USAR: estilo funcional conciso; ideal para acciones simples (log, acumular).
    public static <K, V> void recorrerMapForEach(Map<K, V> map) {
        map.forEach((k, v) -> {
            // ... tu lógica
        });
    }

    // 5) Streams sobre entrySet (filtrar, transformar, ordenar)
    // CUÁNDO USAR: necesitas pipeline (filter/map/sort) y/o producir otra colección.
    public static <K, V extends Comparable<? super V>> void recorrerStreamFiltrarOrdenar(Map<K, V> map) {
        map.entrySet().stream()
           .filter(e -> e.getValue() != null)               // filtra
           .sorted(Map.Entry.comparingByValue())            // ordena por valor ASC
           .forEach(e -> {
               K k = e.getKey();
               V v = e.getValue();
               // ... tu lógica
           });
    }

    // 6) Ordenar por valor (desc) y recoger a LinkedHashMap
    // CUÁNDO USAR: necesitas un Map ordenado por valor para uso posterior.
    public static <K, V extends Comparable<? super V>> Map<K, V> ordenarPorValorDesc(Map<K, V> map) {
        return map.entrySet().stream()
                  .sorted(Map.Entry.<K, V>comparingByValue().reversed())
                  .collect(Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (a, b) -> a,
                      LinkedHashMap::new
                  ));
    }

    // 7) Eliminar mientras iteras (seguro)
    // CUÁNDO USAR: necesitas borrar según condición SIN lanzar ConcurrentModificationException.
    public static <K, V> void eliminarMientrasIteras(Map<K, V> map, java.util.function.Predicate<Map.Entry<K, V>> pred) {
        for (Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<K, V> e = it.next();
            if (pred.test(e)) {
                it.remove(); // Única forma segura en bucle tradicional
            }
        }
    }

    // 8) Borrado masivo con removeIf (corto y claro)
    // CUÁNDO USAR: eliminar muchas entradas de golpe con predicado.
    public static <K, V> void eliminarConRemoveIf(Map<K, V> map, java.util.function.Predicate<Map.Entry<K, V>> pred) {
        map.entrySet().removeIf(pred); // Internamente maneja el iterador correctamente
    }

    // 9) ConcurrentHashMap.forEach
    // CUÁNDO USAR: lectura/actualización concurrente (muchos hilos). Evita modificar estructura fuera de las operaciones atómicas del propio mapa.
    public static <K, V> void recorrerConcurrentForEach(ConcurrentHashMap<K, V> chm) {
        chm.forEach(1, (k, v) -> {
            // ... lectura/acciones thread-safe a nivel de entradas
        });
        // Otras utilidades: chm.search, chm.reduceKeys/reduceValues (paralelismo interno)
    }

    // 10) Recorrido con Spliterator (avanzado / muy grandes / paralelismo)
    // CUÁNDO USAR: procesamientos muy grandes o particionables; normalmente stream() ya basta.
    public static <K, V> void recorrerConSpliterator(Map<K, V> map) {
        Spliterator<Map.Entry<K, V>> sp = map.entrySet().spliterator();
        sp.forEachRemaining(e -> {
            // ... tu lógica
        });
    }

    // 11) Consejos de elección rápida (referencia)
    public static String guiaRapida() {
        return """
               ✅ entrySet + for-each → clave+valor (default), rápido y claro.
               ✅ values() → solo valores.
               ✅ forEach((k,v)) → acciones simples y concisas.
               ✅ streams → filtrar/transformar/ordenar y recolectar.
               ✅ iterator.remove / removeIf → borrar mientras iteras (sin excepciones).
               ✅ LinkedHashMap → mantener orden de inserción al iterar.
               ✅ TreeMap → iteración ordenada por clave.
               ✅ ConcurrentHashMap → escenarios multi-hilo.
               ⚠️ Evita modificar la estructura del Map dentro de un for-each salvo usando iterator.remove() o removeIf().
               """;
    }
}
