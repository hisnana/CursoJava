# 📝 Chuleta clara — SQL y Colecciones en Java (Examen 16)


---

## 1) ¿Cuándo usar `List`, `Set` o `Map`?

* **`List`** → Orden por **índice**(posicion) y **permite duplicados**. `ArrayList` 
	* Ej.: lista de tareas, resultados ordenados.
	
* **`Set`** → **Sin duplicados** (usa igualdad). **No** tiene índice(posicion). `HashSet` 
	* Ej.: emails únicos, etiquetas únicas.
	
* **`Map`** → Pares **clave → valor** (cada **clave** es única). `HashMap`  
	* Ej.: DNI → Persona, código → Curso.

> Regla rápida: si dudas, empieza con **`ArrayList`**, **`HashSet`** o **`HashMap`**.

---

## 2) Implementaciones que más aparecen COLECCIONES

| Necesitas…                                | Estructura      | Notas exprés                           |
| ----------------------------------------- | --------------- | -------------------------------------- |
| Lista normal con acceso por índice        | `ArrayList`     | 95% de casos                           |
| Muchas inserciones/eliminaciones en medio | `LinkedList`    | Poco común                             |
| Conjunto sin orden                        | `HashSet`       | O(1) medio                             |
| Conjunto **con orden de inserción**       | `LinkedHashSet` | Mantiene orden al iterar               |
| Conjunto **ordenado por criterio**        | `TreeSet`       | Requiere `Comparator` o `Comparable`   |
| Diccionario normal (clave→valor)          | `HashMap`       | O(1) medio                             |
| Diccionario **con orden de inserción**    | `LinkedHashMap` | Ideal para mostrar en el orden añadido |
| Diccionario **ordenado por clave**        | `TreeMap`       | Orden natural o `Comparator`           |

---

## 3) Igualdad en colecciones (⚠️ trampa #1)

En `HashSet`/`HashMap` con clases propias **debes** sobrescribir `equals` **y** `hashCode`. Si no, tendrás duplicados raros o búsquedas que fallan.

```java
public final class Student {
  private final int id;
  private final String name;
  // constructor + getters

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student s)) return false;
    return id == s.id; // igualdad por id
  }
  @Override public int hashCode() { return Integer.hashCode(id); }
}
```


---

## 5) Operaciones típicas (copia/pega)

```java
// LIST
List<String> l = new ArrayList<>();
l.add("a"); l.add("b");
l.get(0);                 // "a"
l.contains("a");         // true
l.remove("b");           // por valor
l.remove(0);              // por índice
for (String s : l) { /* ... */ }

// SET
Set<String> s = new HashSet<>();
s.add("a"); s.add("a");  // sigue habiendo 1
s.contains("a");
s.remove("a");
// Quitar duplicados de una lista
List<String> sinDup = new ArrayList<>(new LinkedHashSet<>(l));

// MAP
Map<String,Integer> m = new HashMap<>();
m.put("SQL1", 30);
m.get("SQL1");                      // 30
m.getOrDefault("JAVA1", 0);          // 0 si no está
m.containsKey("SQL1");        //sI CONTIENE LA CLAVE O NO
m.containsValue("SQL1");			//Si contiene el valor o no
m.remove("SQL1");					//Saca al objeto y devuelve el objeto que coincide
for (Map.Entry<String,Integer> e : m.entrySet()) {
  String key = e.getKey(); Integer val = e.getValue();
}  ////Sirve para recorrer un Map y acceder a cada pareja (clave, valor) en cada vuelta del bucle. Así puedes leer, imprimir, sumar, filtrar o actualizar valores usando su clave.
```

---



**Errores típicos**

* Usar `==` en lugar de `.equals` para `String`/objetos.
* Olvidar `equals/hashCode` en `HashSet`/`HashMap`.
* Poner filtros del `LEFT JOIN` en `WHERE` (SQL) y cargarte filas nulas.

---



