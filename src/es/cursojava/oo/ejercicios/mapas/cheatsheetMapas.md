# 🧠 Cheatsheet rápido — Listas y Vistas de Map (23/10/2025)

**Leyenda**: 🟢 *mutable/redimensionable* · 🟠 *vista tamaño fijo* · 🔒 *inmutable* · ⚠️ *trampa*

---

## 1) Listas en Java — decisión exprés

- ¿Vas a **añadir/quitar** elementos? → 🟢 `new ArrayList<>(...)`
- ¿Solo **leer/iterar** y quieres crearla **rápido** desde un array u objetos? → 🟠 `Arrays.asList(...)`
- ¿Quieres **inmutable** (Java 9+) y segura (sin `null`)? → 🔒 `List.of(...)`
- ⚠️ **Primitivos**: `Arrays.asList(int[])` → `List<int[]>` (un único elemento). Usa `Integer[]` o `IntStream.of(...).boxed().toList()`.

### Resumen (listas)

| Opción                          | Tipo                       | ¿Tamaño cambia? | ¿Copia elementos? | ¿Comparte respaldo con array? | Notas                                    |
| ------------------------------- | -------------------------- | --------------- | ----------------- | ----------------------------- | ---------------------------------------- |
| 🟠 `Arrays.asList(x...)`        | vista (`Arrays$ArrayList`) | ❌               | ❌                 | ✅                             | Permite `set`, **no** `add/remove/clear` |
| 🟢 `new ArrayList<>(colección)` | lista normal               | ✅               | ✅                 | ❌                             | Independiente y flexible                 |
| 🔒 `List.of(x...)`              | inmutable                  | ❌               | ✅                 | ❌                             | Rechaza `null`, ni `set`                 |

### Mini‑ejemplos

```java
// Vista fija (comparte respaldo)
String[] arr = {"a", "b"};
List<String> l1 = Arrays.asList(arr);
l1.set(0, "x");            // OK
// l1.add("c");            // UnsupportedOperationException
System.out.println(arr[0]);  // "x" (cambió el array)
```

```java
// Lista mutable independiente
List<String> l2 = new ArrayList<>(Arrays.asList("a", "b"));
l2.add("c");                // OK
```

---

## 2) Vistas de `Map` — decisión exprés

- **Solo claves** → `keySet()`
- **Solo valores** → `values()`
- **Clave y valor** (eficiente) → `entrySet()`

### Resumen (vistas de Map)

| Vista        | Tipo devuelto                 | ¿Añadir en la vista? | ¿Eliminar desde la vista?            | ¿Refleja cambios? | Uso típico                                  | Orden de iteración |
| ------------ | ----------------------------- | -------------------- | ------------------------------------ | ----------------- | ------------------------------------------- | ------------------ |
| `keySet()`   | `Set<K>` (vista)              | ❌                    | ✅ `remove(k)`                        | ✅ (bidireccional) | Iterar/comprobar claves                     | Depende del `Map`  |
| `values()`   | `Collection<V>` (vista)       | ❌                    | ✅ `remove(v)` (primera coincidencia) | ✅                 | Agregaciones/estadísticas                   | Depende del `Map`  |
| `entrySet()` | `Set<Map.Entry<K,V>>` (vista) | ❌                    | ✅ `iterator.remove()`                | ✅                 | Recorrer **clave+valor** sin `get(k)` extra | Depende del `Map`  |

### Trampas comunes

- `keySet().remove(k)` **borra la entrada** del mapa.
- `values().remove(v)` **elimina la primera entrada** cuyo valor `equals(v)`.
- Para actualizar valores **in‑place**, usa `entrySet()` y `entry.setValue(...)`.

### Mini‑ejemplos

```java
Map<String,Integer> edades = new HashMap<>();
edades.put("Ana", 30);
edades.put("Luis", 28);

// keySet: iterar claves y eliminar
for (String nombre : edades.keySet()) {
    System.out.println(nombre);
}
edades.keySet().remove("Luis"); // borra la entrada
```

```java
// values: sumar valores y eliminar por valor
Map<String,Integer> m = new HashMap<>(Map.of("Ana",30,"Marta",28,"Luis",28));
int total = 0; for (int e : m.values()) total += e;
m.values().remove(28); // elimina una entrada con 28
```

```java
// entrySet: eficiente para clave+valor y para actualizar
Map<String,Integer> e2 = new HashMap<>(Map.of("Ana",30,"Luis",28));
for (Map.Entry<String,Integer> e : e2.entrySet()) {
    e.setValue(e.getValue() + 1); // subir 1 año
}
Iterator<Map.Entry<String,Integer>> it = e2.entrySet().iterator();
while (it.hasNext()) if (it.next().getValue() > 30) it.remove();
```

---

## 3) Árbol de decisión (ultra‑rápido)

1. ¿Necesitas **mutar tamaño**? → 🟢 `new ArrayList<>()`
2. Si **no**: ¿Solo lectura y partir de literales? → 🔒 `List.of(...)`
3. ¿Partes de **array** y quieres **vista**? → 🟠 `Arrays.asList(...)`
4. `Map` → ¿Qué necesitas?\
   • **Claves** → `keySet()` · **Valores** → `values()` · **Claves+Valores** → `entrySet()`

---

## 4) Recordatorio de rendimiento

- Iterar `entrySet()` evita el coste extra de `get(k)` frente a `keySet()`.
- `Arrays.asList` es O(1) (sin copia); `new ArrayList<>(...)` es O(n) por copia.



