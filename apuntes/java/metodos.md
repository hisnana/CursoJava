

## 🛠️ Métodos comunes de `String` en Java (ampliado)

| Método                      | Descripción                                                    | Ejemplo de uso                               | Resultado                  |
|-----------------------------|----------------------------------------------------------------|-----------------------------------------------|----------------------------|
| `length()`                  | Devuelve la longitud del string                                | `"Hola".length()`                             | `4`                        |
| `charAt(int index)`         | Devuelve el carácter en la posición dada                       | `"Hola".charAt(1)`                            | `'o'`                      |
| `substring(int begin)`      | Substring desde el índice `begin` hasta el final               | `"Hola".substring(2)`                         | `"la"`                     |
| `substring(int begin, end)` | Substring desde `begin` hasta `end - 1`                        | `"Hola".substring(1, 3)`                      | `"ol"`                     |
| `toUpperCase()`             | Convierte todo el texto a mayúsculas                          | `"java".toUpperCase()`                        | `"JAVA"`                   |
| `toLowerCase()`             | Convierte todo el texto a minúsculas                          | `"JAVA".toLowerCase()`                        | `"java"`                   |
| `equals(String str)`        | Compara si dos strings tienen el mismo contenido (sensible a mayúsculas) | `"Hola".equals("hola")`             | `false`                    |
| `equalsIgnoreCase(String)`  | Compara strings ignorando mayúsculas                         | `"Hola".equalsIgnoreCase("hola")`             | `true`                     |
| `contains(CharSequence)`    | Verifica si contiene la secuencia especificada                | `"Hola mundo".contains("mun")`                | `true`                     |
| `startsWith(String)`        | Verifica si comienza con el texto dado                        | `"Hola".startsWith("Ho")`                     | `true`                     |
| `endsWith(String)`          | Verifica si termina con el texto dado                         | `"Hola".endsWith("la")`                       | `true`                     |
| `replace(char, char)`       | Reemplaza un carácter por otro                                | `"casa".replace('a', 'o')`                    | `"coso"`                   |
| `replace(CharSequence, CharSequence)` | Reemplaza una subcadena por otra              | `"java".replace("ja", "pa")`                  | `"pava"`                   |
| `trim()`                    | Elimina espacios al principio y al final                      | `"  hola  ".trim()`                           | `"hola"`                   |
| `indexOf(char)`             | Devuelve la posición de la primera ocurrencia                 | `"Hola".indexOf('l')`                         | `2`                        |
| `indexOf(String)`           | Devuelve la posición de la subcadena                          | `"Hola mundo".indexOf("mun")`                 | `5`                        |
| `lastIndexOf(char)`         | Devuelve la última posición de un carácter                    | `"banana".lastIndexOf('a')`                   | `5`                        |
| `isEmpty()`                 | Verifica si el string está vacío (`length() == 0`)            | `"".isEmpty()`                                | `true`                     |
| `isBlank()` *(Java 11+)*    | Verifica si está vacío o solo contiene espacios               | `"   ".isBlank()`                             | `true`                     |
| `split(String regex)`       | Divide el string en un array según un patrón                  | `"a,b,c".split(",")`                          | `["a", "b", "c"]`          |
| `join(CharSequence, ...)`   | Une elementos con un delimitador (estático)                   | `String.join("-", "a", "b", "c")`             | `"a-b-c"`                  |
| `repeat(int count)` *(Java 11+)* | Repite el string `n` veces                        | `"ha".repeat(3)`                              | `"hahaha"`                 |

---

✅ **Consejo**: Familiarízate con los más usados como `substring()`, `equals()`, `contains()`, y `replace()`. En entrevistas o ejercicios prácticos son muy comunes.

Las variables locales viven y existen solo dentro de un método y deben inicializarse explícitamente.

Las variables globales (de instancia o estáticas) existen a nivel de clase/objeto, son accesibles desde otros métodos y tienen valores por defecto si no se inicializan.

# Diferencias entre Variable Local y Variable Global en Java

---

| Característica          | Variable Local                             | Variable Global (de instancia / estática)           |
|------------------------|--------------------------------------------|-----------------------------------------------------|
| **Declaración**        | Dentro de un método o bloque                 | Fuera de métodos, dentro de una clase                |
| **Alcance (scope)**    | Solo visible dentro del método o bloque donde se declara | Visible en toda la clase y en los objetos (si es instancia) o en toda la clase (si es estática) |
| **Tiempo de vida**     | Solo durante la ejecución del método         | Toda la vida del objeto (instancia) o del programa (estática) |
| **Inicialización**     | Deben inicializarse explícitamente antes de usarse | Se inicializan automáticamente con valores por defecto si no se asigna valor |
| **Memoria**            | Se almacena en la pila (stack)               | Se almacena en el heap (instancia) o en área estática (static) |
| **Ejemplo**            | `int x = 10;` dentro de un método            | `int edad;` declarado en la clase (no dentro de métodos) |


Los métodos
Reutiliza codigo.
Simplificar codigo.

modificador: public, private, static, etc.

tipoRetorno: tipo de dato que devuelve el método (int, String, void si no devuelve nada).

nombreMetodo: nombre para llamar al método.

parámetros: datos que recibe, separados por comas.

Método sin parámetros y sin retorno

public void saludar() {
    System.out.println("Hola!");
}
Método con parámetros y sin retorno

public void saludarPersona(String nombre) {
    System.out.println("Hola, " + nombre + "!");
}
Método con parámetros y con retorno

public int sumar(int a, int b) {
    return a + b;
}
Método estático

public static double calcularAreaCirculo(double radio) {
    return Math.PI * radio * radio;
}

# 📚 Ejemplos de Métodos Más Conocidos en Java

---

## 1. Métodos de la clase `String`

| Método              | Descripción                     | Ejemplo                             | Resultado                |
|---------------------|---------------------------------|-----------------------------------|--------------------------|
| `length()`          | Devuelve la longitud del texto  | `"Hola".length()`                  | `4`                      |
| `charAt(int)`       | Caracter en índice dado         | `"Hola".charAt(1)`                 | `'o'`                    |
| `substring(int)`    | Subcadena desde índice          | `"Hola".substring(2)`              | `"la"`                   |
| `toUpperCase()`     | Convierte a mayúsculas          | `"java".toUpperCase()`             | `"JAVA"`                 |
| `equals(String)`    | Compara contenido (sensible)   | `"hola".equals("Hola")`            | `false`                  |
| `equalsIgnoreCase()`| Compara ignorando mayúsculas   | `"hola".equalsIgnoreCase("Hola")` | `true`                   |
| `contains(String)`  | Verifica si contiene subcadena | `"Java es genial".contains("gen")`| `true`                   |

---

## 2. Métodos de la clase `Math`

| Método               | Descripción                    | Ejemplo                        | Resultado          |
|----------------------|-------------------------------|--------------------------------|--------------------|
| `abs(int)`           | Valor absoluto                | `Math.abs(-5)`                 | `5`                |
| `max(int, int)`      | Devuelve el mayor             | `Math.max(3, 7)`               | `7`                |
| `min(int, int)`      | Devuelve el menor             | `Math.min(3, 7)`               | `3`                |
| `sqrt(double)`       | Raíz cuadrada                 | `Math.sqrt(16)`                | `4.0`              |
| `pow(double, double)`| Potencia                     | `Math.pow(2, 3)`               | `8.0`              |
| `random()`           | Número aleatorio (0.0-1.0)   | `Math.random()`                | `0.0 <= x < 1.0`   |

---

## 3. Métodos de la clase `ArrayList` (en `java.util`)

| Método               | Descripción                       | Ejemplo                         |
|----------------------|----------------------------------|--------------------------------|
| `add(E e)`           | Añade elemento                   | `lista.add("Hola")`             |
| `get(int index)`     | Obtiene elemento                 | `lista.get(0)`                  |
| `size()`             | Tamaño de la lista               | `lista.size()`                  |
| `remove(int index)`  | Elimina elemento por índice      | `lista.remove(0)`               |
| `contains(Object o)` | Verifica si contiene elemento   | `lista.contains("Hola")`        |
| `clear()`            | Limpia todos los elementos      | `lista.clear()`                 |

---

## 4. Métodos de la clase `System`

| Método             | Descripción                      | Ejemplo                       |
|--------------------|---------------------------------|-------------------------------|
| `out.println()`    | Imprime en consola              | `System.out.println("Hola");`  |
| `currentTimeMillis()` | Tiempo actual en milisegundos  | `System.currentTimeMillis()`   |
| `exit(int)`         | Termina programa con código     | `System.exit(0)`               |

---

## 5. Otros métodos comunes

| Clase         | Método               | Descripción                | Ejemplo                    |
|---------------|----------------------|----------------------------|----------------------------|
| `Scanner`     | `nextLine()`         | Leer línea de entrada      | `scanner.nextLine()`        |
| `Scanner`     | `nextInt()`          | Leer entero                | `scanner.nextInt()`         |
| `StringBuilder` | `append(String)`    | Añade texto                | `sb.append("texto")`        |
| `Collections` | `sort(List<T>)`      | Ordena lista               | `Collections.sort(lista)`   |

---

