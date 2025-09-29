# 🧺 Tipos de estructuras para guardar datos en Java

Java tiene diferentes formas de **guardar y organizar datos**. Aquí te explico las principales de forma clara:

---

## 1. 📋 Listas (`List`)

- Guarda elementos en orden
- Permite elementos repetidos
- Puedes acceder a los elementos por su posición (índice)

### Ejemplo:
```java
List<String> frutas = new ArrayList<>();
frutas.add("Manzana");
frutas.add("Banana");
```

✅ Úsala cuando:
- Necesites mantener el orden
- Puedas tener elementos repetidos
- Quieras acceder por número de posición

---

## 2. 📦 Conjuntos (`Set`)

- No permite elementos repetidos
- El orden puede variar (según el tipo de `Set`)
- No puedes acceder por índice

### Ejemplo:
```java
Set<String> colores = new HashSet<>();
colores.add("Rojo");
colores.add("Azul");
colores.add("Rojo"); // No se guarda porque ya existe
```

✅ Úsala cuando:
- Quieras asegurar que no haya duplicados

---

## 3. 🔑 Diccionarios (`Map`)

- Guarda datos en pares clave-valor
- Cada clave es única
- Puedes acceder al valor usando la clave

### Ejemplo:
```java
Map<Integer, String> personas = new HashMap<>();
personas.put(1, "Ana");
personas.put(2, "Luis");
```

✅ Úsala cuando:
- Necesites relacionar dos cosas (ej. ID → nombre)
- Quieras buscar datos rápidamente por una clave

---

## 4. 📦 Colecciones (`Collection`)

- Es una interfaz genérica
- Agrupa estructuras como `List`, `Set` y `Queue`
- **No incluye `Map`**, porque `Map` no extiende de `Collection`

### Ejemplo:
```java
Collection<String> items = new ArrayList<>();
items.add("Uno");
items.add("Dos");
```

✅ Úsala cuando:
- Quieras trabajar de forma general con estructuras de datos similares

---

## 🧠 Resumen visual

| Tipo        | Duplicados | Orden | Clave-Valor | Acceso por índice | Ejemplo común     |
|-------------|------------|-------|-------------|--------------------|--------------------|
| `List`      | ✅ Sí       | ✅ Sí | ❌ No        | ✅ Sí               | `ArrayList`        |
| `Set`       | ❌ No       | ❓ Depende | ❌ No    | ❌ No               | `HashSet`          |
| `Map`       | ✅ Valores / ❌ Claves | ❓ Depende | ✅ Sí         | ❌ No               | `HashMap`          |
| `Collection`| Depende     | Depende | ❌ No     | Depende            | `List`, `Set`, etc.|

---
