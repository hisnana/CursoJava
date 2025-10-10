# 📝 Chuleta clara — SQL y Colecciones en Java (Examen 16)

> Referencia rápida y sencilla para el día del examen.
>
> **Tip**: súbelo a tu repo como `apuntes/chuleta_colecciones_y_sql.md`.

---

## 1) ¿Cuándo usar `List`, `Set` o `Map`?

* **`List`** → Orden por **índice** y **permite duplicados**.

  * Ej.: lista de tareas, resultados ordenados.
* **`Set`** → **Sin duplicados** (usa igualdad). **No** tiene índice.

  * Ej.: emails únicos, etiquetas únicas.
* **`Map`** → Pares **clave → valor** (cada **clave** es única).

  * Ej.: DNI → Persona, código → Curso.

> Regla rápida: si dudas, empieza con **`ArrayList`**, **`HashSet`** o **`HashMap`**.

---

## 2) Implementaciones que más aparecen

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

## 4) Ordenación con `Comparator`

```java
record Course(String code, String title, int hours) {}

Comparator<Course> byHoursDescThenCode =
    Comparator.comparingInt(Course::hours).reversed()
              .thenComparing(Course::code);

List<Course> list = new ArrayList<>(List.of(
  new Course("SQL1","SQL Básico",30),
  new Course("JAVA1","Java OOP",40)
));
list.sort(byHoursDescThenCode);     // Ordena la lista

Set<Course> ordenados = new TreeSet<>(byHoursDescThenCode);
ordenados.addAll(list);             // Set ordenado por el comparator
```

**`TreeMap`** funciona igual (ordenado por **clave**): `new TreeMap<>(miComparatorDeClaves)`.

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
m.containsKey("SQL1");
m.remove("SQL1");
for (Map.Entry<String,Integer> e : m.entrySet()) {
  String key = e.getKey(); Integer val = e.getValue();
}
```

---

## 6) Streams: 6 patrones que salvan el examen

```java
// 1) map + filter + toList
List<String> largos = list.stream()
  .map(Course::title)
  .filter(t -> t.length() > 5)
  .toList();

// 2) groupingBy + averaging (media por alumno)
Map<Student, Double> media = enrollments.stream()
  .collect(Collectors.groupingBy(
     Enrollment::student,
     Collectors.averagingDouble(Enrollment::grade)));

// 3) toMap con fusión y orden de inserción
Map<String,Integer> horasPorCodigo = list.stream()
  .collect(Collectors.toMap(
     Course::code, Course::hours,
     (a,b) -> b,                  // si hay duplicado, gana el último
     LinkedHashMap::new));

// 4) top-N por valor
List<Student> top3 = media.entrySet().stream()
  .sorted(Map.Entry.<Student,Double>comparingByValue().reversed())
  .limit(3)
  .map(Map.Entry::getKey)
  .toList();

// 5) contar frecuencias
Map<String, Long> veces = list.stream()
  .collect(Collectors.groupingBy(Course::title, Collectors.counting()));

// 6) flatMap (aplanar lista de listas)
List<String> todos = listas.stream()
  .flatMap(List::stream)
  .toList();
```

**Errores típicos**

* Usar `==` en lugar de `.equals` para `String`/objetos.
* Olvidar `equals/hashCode` en `HashSet`/`HashMap`.
* Poner filtros del `LEFT JOIN` en `WHERE` (SQL) y cargarte filas nulas.

---

## 7) SQL — Plantillas mínimas (Oracle)

**SELECT plantilla** (lo que casi siempre necesitas):

```sql
SELECT c1, c2, fn(c3) AS alias
FROM   t1
[JOIN  t2 ON t1.id = t2.id]
WHERE  condiciones
GROUP  BY c1, c2
HAVING condiciones_agregadas
ORDER  BY 1 ASC, 2 DESC
FETCH FIRST 10 ROWS ONLY; -- (12c+). Alternativa: WHERE ROWNUM <= 10
```

**Filtros útiles**

```sql
WHERE city IN ('MADRID','SEVILLA')
  AND price BETWEEN 100 AND 300
  AND name LIKE 'ANA%'
  AND (col IS NULL OR col = 0)
```

**JOINs**

```sql
-- INNER: sólo coincidencias
a JOIN b ON a.k = b.k
-- LEFT: todo a, tenga o no b
a LEFT JOIN b ON a.k = b.k
-- Filas sin pareja en b (anti-join)
a LEFT JOIN b ON a.k = b.k
WHERE b.k IS NULL;
```

**GROUP BY/HAVING**

```sql
SELECT dept, COUNT(*) n, AVG(sal) media
FROM emp
GROUP BY dept
HAVING COUNT(*) >= 3
ORDER BY media DESC;
```

**Subconsultas**

```sql
-- IN: conjunto concreto
WHERE e.dept_id IN (SELECT id FROM dept WHERE city='MADRID');
-- EXISTS: suele cortar antes
WHERE EXISTS (SELECT 1 FROM dept d WHERE d.id=e.dept_id AND d.city='MADRID');
```

**Funciones que dan puntos**: `NVL(x,def)`, `COALESCE(a,b,c)`, `CASE WHEN`, `SUBSTR`, `INSTR`, `TRUNC(fecha)`, `ROUND`.

**DDL y constraints (mini)**

```sql
CREATE TABLE COURSES (
  ID     NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  CODE   VARCHAR2(10) UNIQUE NOT NULL,
  TITLE  VARCHAR2(100) NOT NULL,
  HOURS  NUMBER CHECK (HOURS BETWEEN 1 AND 300)
);

ALTER TABLE ENROLLMENTS ADD CONSTRAINT FK_ENR_COU
  FOREIGN KEY (COURSE_ID) REFERENCES COURSES(ID) ON DELETE CASCADE;

COMMIT;  -- confirma cambios DML
ROLLBACK; -- deshace cambios no confirmados
```

---

## 8) Checklist de 1 minuto antes de entregar

* [ ] Java: ¿he elegido bien entre **List/Set/Map**?
* [ ] Si uso `HashSet`/`HashMap` con clases propias → **`equals/hashCode`**.
* [ ] ¿Necesito orden? → `LinkedHash*` o `Tree*` con `Comparator`.
* [ ] Streams: ¿puedo resolver con `map/filter/collect` o `groupingBy`?
* [ ] SQL: JOIN vs subconsulta, `HAVING` sólo con agregadas, `FETCH FIRST n`.


