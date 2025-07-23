# 📘 Apuntes: Tipos de Variables en Java

En Java, una variable es un espacio de memoria que almacena un valor. Cada variable debe declararse con un **tipo de dato**.

---

## 🧱 Categorías de Variables

### 1. 📦 **Primitivas (Primitive Types)**
Son los tipos de datos básicos que **no son objetos**. Java tiene 8 tipos primitivos.

| Tipo     | Tamaño   | Rango / Valor                        | Ejemplo              |
|----------|----------|--------------------------------------|----------------------|
| `byte`   | 8 bits   | -128 a 127                           | `byte edad = 25;`    |
| `short`  | 16 bits  | -32,768 a 32,767                    | `short s = 32000;`   |
| `int`    | 32 bits  | -2^31 a 2^31-1                      | `int x = 100;`       |
| `long`   | 64 bits  | -2^63 a 2^63-1                      | `long l = 100000L;`  |
| `float`  | 32 bits  | Decimales de precisión simple       | `float pi = 3.14f;`  |
| `double` | 64 bits  | Decimales de doble precisión        | `double d = 9.81;`   |
| `char`   | 16 bits  | Un solo carácter Unicode            | `char letra = 'A';`  |
| `boolean`| 1 bit    | `true` o `false`                    | `boolean activo = true;` |

---

### 2. 🧩 **Referenciadas (Reference Types)**
Son variables que hacen referencia a **objetos**. No almacenan directamente el valor, sino una referencia a un objeto en memoria.

## ¿Qué es un tipo objeto?

- En Java, un **tipo objeto** es cualquier instancia de una clase.
- A diferencia de los **tipos primitivos** (como `int`, `boolean`, `char`), los objetos almacenan referencias y tienen métodos.

---

## Ejemplos comunes de tipos objeto

| Tipo Objeto          | Descripción                                | Ejemplo de declaración                        |
|----------------------|--------------------------------------------|----------------------------------------------|
| `String`             | Cadena de texto inmutable                   | `String texto = "Hola";`                      |
| `Integer`            | Objeto envolvente para `int`                | `Integer numero = 100;`                        |
| `Double`             | Objeto envolvente para `double`             | `Double decimal = 3.14;`                       |
| `Boolean`            | Objeto envolvente para `boolean`            | `Boolean esVerdadero = true;`                  |
| `ArrayList`          | Lista dinámica de objetos                    | `ArrayList<String> lista = new ArrayList<>();`|
| `Scanner`            | Para leer entrada de usuario                 | `Scanner sc = new Scanner(System.in);`        |
| `Date`               | Fecha y hora                                 | `Date fecha = new Date();`                      |
| `CustomClass`        | Cualquier clase creada por el usuario        | `MiClase obj = new MiClase();`                  |


---

# Características de los `String` en Java y por qué son inmutables

---

## Características de los `String`

- **Inmutables**: Una vez creado, su contenido no puede cambiar.
- **Objeto**: No es un tipo primitivo, sino una clase en `java.lang`.
- **Almacenamiento en pool**: Los literales de `String` se almacenan en un pool especial para optimizar memoria.
- **Soportan muchos métodos**: Como `length()`, `substring()`, `equals()`, entre otros.
- **Seguro para hilos (thread-safe)**: Debido a su inmutabilidad, se pueden compartir entre hilos sin problemas.
- **Se pueden concatenar** usando el operador `+`, pero siempre creando nuevos objetos.

---

## ¿Por qué son inmutables los `String`?

1. **Seguridad**  
   Muchas APIs (como la conexión a bases de datos, sockets) usan `String` para datos sensibles (ej. URLs, nombres de usuario). Si se modificaran, podría afectar la seguridad.

2. **Eficiencia y rendimiento**  
   La inmutabilidad permite que los `String` se compartan en un pool de cadenas (String Pool), evitando duplicados y ahorrando memoria.

3. **Hashcode constante**  
   El valor hash de un `String` se calcula una vez y se almacena. Si el contenido cambiara, afectaría estructuras como `HashMap` o `HashSet` que dependen de valores hash constantes.

4. **Facilita la concurrencia**  
   Los objetos inmutables son seguros para usarse en múltiples hilos sin sincronización adicional.

---

## Ejemplo de inmutabilidad

```java
String s = "Hola";
s = s + " mundo";  // No modifica el objeto original, crea uno nuevo
