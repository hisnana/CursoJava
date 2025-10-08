# Resumen de Colecciones en Java

Las **colecciones** en Java son estructuras para almacenar y manipular grupos de objetos de forma eficiente. Están definidas en el paquete `java.util` y organizadas en una jerarquía de interfaces y clases.

---

## 1. Principales Interfaces

- **Collection**: Interfaz base para la mayoría de colecciones.
- **List**: Colección ordenada que permite elementos duplicados.
- **Set**: Colección sin elementos duplicados.
- **Queue**: Colección para gestionar elementos en orden (normalmente FIFO).
- **Map**: Estructura para pares clave-valor (no extiende Collection).

---

## 2. Implementaciones Comunes

### List

- **ArrayList**  
  Lista basada en array, acceso rápido por índice.  
  ```java
  List<String> arrayList = new ArrayList<>();
  arrayList.add("Java");

  arrayList.add("Python");
  System.out.println(arrayList.get(0));
  ```
  
  # LinkedList
Lista doblemente enlazada, eficiente para inserciones y eliminaciones.

```java
List<String> linkedList = new LinkedList<>();
linkedList.add("C++");
linkedList.add("Go");
linkedList.remove(0);
System.out.println(linkedList);
```
# LinkedList
Lista doblemente enlazada, eficiente para inserciones y eliminaciones.

# CopyOnWriteArrayList
Lista segura para hilos que copia el array en cada escritura.

```java
Set<String> hashSet = new HashSet<>();
hashSet.add("A");
hashSet.add("B");
hashSet.add("A");
System.out.println(hashSet);
CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
cowList.add("Concurrent");
cowList.add("List");
```
# Set

## HashSet
Conjunto no ordenado, no permite duplicados.

```java
Set<String> hashSet = new HashSet<>();
hashSet.add("A");
hashSet.add("B");
hashSet.add("A");
System.out.println(hashSet);
```

## LinkedHashSet
Conjunto que mantiene el orden de inserción.
```java
// LinkedHashSet
Set<String> linkedHashSet = new LinkedHashSet<>();
linkedHashSet.add("X");
linkedHashSet.add("Y");
System.out.println(linkedHashSet);
````

## TreeSet
Conjunto ordenado (orden natural o comparator).

```java

// TreeSet
Set<Integer> treeSet = new TreeSet<>();
treeSet.add(3);
treeSet.add(1);
treeSet.add(2);
System.out.println(treeSet);
````

# Queue

## LinkedList (implementa Queue)
```java
// Queue con LinkedList
Queue<String> queue = new LinkedList<>();
queue.add("Primero");
queue.add("Segundo");
System.out.println(queue.poll());
````

## PriorityQueue
Cola con prioridad basada en orden natural o comparator.

```java
// PriorityQueue
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.add(5);
pq.add(1);
pq.add(3);
System.out.println(pq.poll());
```

# Map

## HashMap
Mapa no ordenado, acceso rápido por clave.

```java
// HashMap
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("A", 1);
hashMap.put("B", 2);
System.out.println(hashMap.get("A"));
```

## LinkedHashMap
Mapa que mantiene el orden de inserción.
```java
// LinkedHashMap
Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
linkedHashMap.put("X", 10);
linkedHashMap.put("Y", 20);
System.out.println(linkedHashMap);
```

## TreeMap
Mapa ordenado por clave (natural o comparator).
```java
// TreeMap
Map<Integer, String> treeMap = new TreeMap<>();
treeMap.put(2, "B");
treeMap.put(1, "A");
System.out.println(treeMap);
````

## ConcurrentHashMap
Mapa concurrente, seguro para múltiples hilos.

```java
// ConcurrentHashMap
ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<>();
concurrentMap.put("key", "value");
```

# 3. Características Clave

| Colección           | Orden                   | Permite duplicados       | Acceso por índice | Concurrencia             |
|---------------------|-------------------------|-------------------------|-------------------|--------------------------|
| List (ArrayList)    | Sí                      | Sí                      | Sí                | No (salvo CopyOnWrite)   |
| Set (HashSet)       | No (excepto LinkedHashSet) | No                    | No                | No                       |
| Queue (LinkedList)  | Sí (FIFO)               | Sí                      | No                | No                       |
| Map (HashMap)       | No (excepto LinkedHashMap) | Claves no duplicadas  | No                | No (salvo ConcurrentHashMap) |

Buena pregunta. En la tabla de colecciones, "concurrencia" se refiere a si una colección en Java es segura para ser usada por múltiples hilos al mismo tiempo (multi-threading) sin causar errores como:

Datos corruptos

Condiciones de carrera (race conditions)

Excepciones inesperadas (ConcurrentModificationException, por ejemplo)

# 4. Cuándo usar cada una

| Necesidad                     | Colección recomendada               |
|------------------------------|------------------------------------|
| Lista ordenada, acceso rápido | ArrayList                         |
| Inserciones/eliminaciones frecuentes | LinkedList                |
| Sin duplicados, orden no importante | HashSet                   |
| Sin duplicados, orden de inserción | LinkedHashSet              |
| Sin duplicados, orden natural | TreeSet                         |
| Pares clave-valor rápido, sin orden | HashMap                   |
| Pares clave-valor con orden | TreeMap                         |
| Acceso seguro concurrente    | ConcurrentHashMap, CopyOnWriteArrayList |

  
  

  


