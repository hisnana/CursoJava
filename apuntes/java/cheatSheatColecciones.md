# 🗂️ Cheatsheet de Colecciones en Java (claro y compacto)

**Fecha:** 27/10/2025 · Enfoque: básico, sin Streams · Ideal para imprimir o subir a Git ✅

---

## 0) Mapa mental rápido
- **Interfaces clave**: `Collection` → `List`, `Set`, `Queue/Deque`; **paralela** a éstas está `Map`.
- **Elegir**:
  - Orden + acceso por índice → **ArrayList**
  - Muchas inserciones/eliminaciones en extremos → **ArrayDeque** (cola/pila)
  - Sin duplicados → **Set** (HashSet/LinkedHashSet/TreeSet)
  - Clave→Valor → **Map** (HashMap/LinkedHashMap/TreeMap)
  - Prioridades → **PriorityQueue**

> **Regla de oro**: si vas a usar colecciones *hash*, implementa bien `equals()` y `hashCode()` en tus clases.

---

## 1) `List`
| Implementación | Orden | Duplicados | `null` | Acceso índice | Inserciones/ borrados | Complejidad típica |
|---|---|---:|---:|---:|---:|---|
| **ArrayList** | Inserción | Sí | Sí | **O(1)** | Medio: O(n) · Fin: amort. **O(1)** | `get`: O(1), `contains`: O(n)
| **LinkedList** | Inserción | Sí | Sí | O(n) | Extremos: **O(1)** | `get`: O(n), como `Deque`

> **Nota**: `Stack` es legado; usa `ArrayDeque` como pila.

---

## 2) `Set` (sin duplicados)
| Implementación | Orden | `null` | Complejidad típica | Notas |
|---|---|---:|---|---|
| **HashSet** | No definido | 1 elemento `null` | `add/contains/remove`: **O(1)** (promedio) | Más rápido si no necesitas orden.
| **LinkedHashSet** | Orden de inserción | 1 `null` | O(1) promedio | Recorre en el orden de inserción.
| **TreeSet** | **Ordenado** (natural o `Comparator`) | ❌ | `add/contains/remove`: **O(log n)** | No acepta `null` (comparación).
| **EnumSet** | Orden natural de `enum` | ❌ | Muy rápido/compacto | Solo para tipos `enum`.

---

## 3) `Queue` / `Deque`
| Implementación | Tipo | Orden | `null` | Complejidad típica | Operaciones clave |
|---|---|---|---:|---|---|
| **ArrayDeque** | Deque (doble cola) | Inserción | ❌ | Extremos: **O(1)** | `addFirst/addLast`, `pollFirst/pollLast`, `push/pop`
| **LinkedList** | Deque/Lista | Inserción | Sí | Extremos: **O(1)** | También `List`
| **PriorityQueue** | Cola de prioridad | Mínimo en cabeza | ❌ | `offer`: **O(log n)**, `peek`: O(1), `poll`: **O(log n)** | No itera en orden global.

---

## 4) `Map` (clave → valor)
| Implementación | Orden | `null` clave | `null` valor | Complejidad típica | Notas |
|---|---|---:|---:|---|---|
| **HashMap** | No definido | 1 | Sí (múltiples) | `get/put/remove`: **O(1)** (promedio) | Generalista y rápido.
| **LinkedHashMap** | Inserción (o acceso si `accessOrder=true`) | 1 | Sí | O(1) promedio | Útil para LRU básico.
| **TreeMap** | **Ordenado por clave** | ❌ | Sí | `get/put/remove`: **O(log n)** | Claves ordenadas con `Comparator`.
| **EnumMap** | Orden natural de `enum` | ❌ | **Sí** | Muy rápido/compacto | Claves tipo `enum`.

> **Vistas de Map**: `keySet()`, `values()`, `entrySet()` son *colecciones respaldadas* (cambios se reflejan).

---

## 5) ¿Permiten `null`?
- `ArrayList`, `LinkedList`, `HashSet`, `LinkedHashSet`, **sí** (en general).
- `TreeSet`, `PriorityQueue`, `ArrayDeque` **no** admiten `null` (lanzan excepción).
- `HashMap`/`LinkedHashMap`: **1** clave `null`, valores `null` ilimitados.
- `TreeMap`: **no** clave `null`, valores `null` permitidos.
- `EnumSet`: no `null`; `EnumMap`: clave `null` **no**, valor `null` **sí**.

---

## 6) Patrones comunes (sin Streams)
**Evitar duplicados** con `Set`:
```java
Set<String> tags = new LinkedHashSet<>();
tags.add("java");
tags.add("java"); // no se duplica
```

**Agrupar** con `Map<K, List<V>>`:
```java
Map<String, List<Libro>> porAutor = new LinkedHashMap<>();
String autor = "Murakami";
porAutor.computeIfAbsent(autor, k -> new ArrayList<>())
       .add(new Libro("1Q84", "978-…", 2009));
```

**Borrar mientras iteras** (uso correcto de `Iterator`):
```java
Iterator<Alumno> it = alumnos.iterator();
while (it.hasNext()) {
    if (it.next().getNota() < 5) it.remove();
}
```

**Recorrer `Map` por pares**:
```java
for (Map.Entry<String, Integer> e : notas.entrySet()) {
    String alumno = e.getKey();
    Integer nota  = e.getValue();
    // usar alumno/nota
}
```

---

## 7) Conversión entre colecciones
```java
List<String> l = new ArrayList<>(set);
Set<String> s = new LinkedHashSet<>(list);
Queue<String> q = new ArrayDeque<>(list);
Map<String, Integer> m = new LinkedHashMap<>();
```

---

## 8) Concurrencia (apunte rápido)
- **No seguras** por defecto. Opciones:
  - `Collections.synchronizedList(map,set)` → envoltorio sincronizado.
  - `ConcurrentHashMap` para `Map` concurrente.
  - `CopyOnWriteArrayList` lectura intensiva, pocas escrituras.

---

## 9) Mini guía para elegir
- ¿Necesitas **acceso rápido por índice** y recorrer en orden? → **ArrayList**.
- ¿Necesitas **pila/cola** eficiente en extremos? → **ArrayDeque**.
- ¿Necesitas **únicos** (sin duplicados)? → **HashSet**; si quieres **orden de inserción** → **LinkedHashSet**; si **orden natural** → **TreeSet**.
- ¿Necesitas **pares clave→valor**? → **HashMap**; si quieres **orden de inserción** → **LinkedHashMap**; si **claves ordenadas** → **TreeMap**.
- ¿Necesitas **mínimo/máximo inmediato**? → **PriorityQueue**.

---

## 10) Recordatorios útiles
- `for-each` usa internamente `Iterator` (para **leer** es suficiente).
- Si modificas durante el recorrido: `Iterator.remove()`.
- Evita `null` cuando sea posible: usa `computeIfAbsent`, `putIfAbsent`, `merge`.
- Para rendimiento en *hash*: usa `initialCapacity`/`loadFactor` si manejas muchos elementos.

