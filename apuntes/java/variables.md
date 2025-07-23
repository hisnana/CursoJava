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

Ejemplos comunes:

```java
String nombre = "Ana";
int[] numeros = {1, 2, 3};
Persona persona = new Persona();
