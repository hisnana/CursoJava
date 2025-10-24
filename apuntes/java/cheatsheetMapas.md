# 🗺️ Cheatsheet Mapas — Recorridos sin Streams

> Mapa de ejemplo: `Map<Autor, List<Libro>> biblioteca`

## 🔹 A) Solo valores — `values()`

Recorre **solo las listas de libros** (no necesitas la clave).

```java
for (List<Libro> lista : biblioteca.values()) {
    for (Libro l : lista) {
        MiLogger.info(l.getTitulo()); // o l.toString()
    }
}
```

**Útil cuando:** solo te interesan los libros.

---

## 🔹 B) Clave + valor — `entrySet()` (lo más completo)

Recorre **clave y valor a la vez** sin `get()` adicional.

```java
for (Map.Entry<Autor, List<Libro>> e : biblioteca.entrySet()) {
    Autor a = e.getKey();
    List<Libro> lista = e.getValue();

    MiLogger.info(a.getNombre() + " (" + a.getNacionalidad() + "):");
    for (Libro l : lista) {
        MiLogger.info("  - " + l.getTitulo() + " (" + l.getAnio() + ")");
    }
}
```

**Útil cuando:** necesitas **autor y libros**. Es el recorrido más directo/eficiente para ambos.

---

## 🔹 C) Claves y luego valores — `keySet()`

Primero recorres **autores**, luego obtienes su lista con `map.get(autor)`.

```java
for (Autor a : biblioteca.keySet()) {
    List<Libro> lista = biblioteca.get(a);
    MiLogger.info(a.getNombre() + ":");
    for (Libro l : lista) {
        MiLogger.info("  - " + l.getTitulo());
    }
}
```

**Útil cuando:** ya trabajas con claves y no te importa hacer `get()`.

---

## 🔹 Con `Iterator` explícito (para borrado seguro)

Permite **eliminar mientras recorres** sin `ConcurrentModificationException`.

```java
Iterator<Map.Entry<Autor, List<Libro>>> it = biblioteca.entrySet().iterator();
while (it.hasNext()) {
    Map.Entry<Autor, List<Libro>> e = it.next();
    Autor a = e.getKey();
    List<Libro> lista = e.getValue();

    MiLogger.info(a.getNombre() + ":");
    for (int i = 0; i < lista.size(); i++) {
        MiLogger.info("  - " + lista.get(i).getTitulo());
    }

    // Si quisieras eliminar al autor aquí:
    // it.remove();
}
```

**Útil cuando:** vas a **borrar** elementos del `Map` o de sus listas durante el recorrido.

---

## 🧭 Tabla rápida de decisión

| Método       | Qué recorres           | Ventaja principal                | Inconveniente           |
| ------------ | ---------------------- | -------------------------------- | ----------------------- |
| `values()`   | Solo valores (listas)  | Sencillo si solo quieres libros  | No tienes la clave      |
| `entrySet()` | Clave + valor a la vez | Completo y evita `get()` extra   | Ninguno relevante       |
| `keySet()`   | Solo claves            | Natural si ya tratas con claves  | Requiere `get(clave)`   |
| `Iterator`   | Entrada(s) con cursor  | **Borrado seguro** durante bucle | Código algo más verboso |

---

## 📝 Notas rápidas

* `entrySet()` devuelve una **vista respaldada**: eliminar una `Entry` con su iterador **borra del Map**.
* Usa `Iterator.remove()` para eliminar **mientras recorres**.
* `Map` no extiende `Collection`; es su propia jerarquía.
