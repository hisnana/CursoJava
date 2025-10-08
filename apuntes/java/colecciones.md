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
  # LinkedList
Lista doblemente enlazada, eficiente para inserciones y eliminaciones.

```java
List<String> linkedList = new LinkedList<>();
linkedList.add("C++");
linkedList.add("Go");
linkedList.remove(0);
System.out.println(linkedList);
  
  
  