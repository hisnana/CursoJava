# 🧱 Los 4 Pilares de la Programación Orientada a Objetos (POO) en Java

---

## 1. 🧠 Abstracción

**Definición:**  
Oculta la complejidad mostrando solo los detalles relevantes de un objeto.

### 🔧 Ejemplo en Java

```java
abstract class Animal {
    abstract void hacerSonido(); // Método abstracto
}

class Perro extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("Guau guau");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal miPerro = new Perro();
        miPerro.hacerSonido();  // Solo necesitas saber que haceSonido() existe
    }
}
```
---
## 2. 🔒 Encapsulamiento

**Definición:**  
Restringe el acceso directo a los datos de un objeto y los protege mediante métodos (getters y setters).

### 🔧 Ejemplo en Java

```java
class Persona {
    private String nombre; // Campo privado

    public String getNombre() { // Getter
        return nombre;
    }

    public void setNombre(String nombre) { // Setter
        this.nombre = nombre;
    }
}

public class Main {
    public static void main(String[] args) {
        Persona p = new Persona();
        p.setNombre("Ana");
        System.out.println(p.getNombre());
    }
}
```
---
## 3. 🧬 Herencia

**Definición:**  
Permite crear una clase nueva (subclase) basada en una clase existente (superclase).

### 🔧 Ejemplo en Java

```java
class Vehiculo {
    void encender() {
        System.out.println("Vehículo encendido");
    }
}

class Coche extends Vehiculo {
    void tocarBocina() {
        System.out.println("¡BEEP BEEP!");
    }
}

public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche();
        miCoche.encender();      // Método heredado
        miCoche.tocarBocina();   // Método propio
    }
}
```
---
## 4. 🎭 Polimorfismo

**Definición:**  
Permite que un mismo método se comporte de forma diferente en distintas clases.

### 🔧 Ejemplo en Java

```java
class Animal {
    void hacerSonido() {
        System.out.println("Sonido genérico");
    }
}

class Gato extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("Miau");
    }
}

class Perro extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("Guau");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a1 = new Gato();
        Animal a2 = new Perro();

        a1.hacerSonido();  // Miau
        a2.hacerSonido();  // Guau
    }
}
```
---

| Concepto              | Descripción                                                  |
|-----------------------|--------------------------------------------------------------|
| Variable de instancia | Atributo que define el estado de un objeto                   |
| `private`             | Oculta el atributo de otras clases                           |
| Getters y Setters     | Métodos públicos para acceder o modificar variables privadas |
| Encapsulamiento       | Principio para proteger los datos y mantener buen diseño     |


---
| Tipo                  | ¿Dónde se declara?               | ¿A quién pertenece?     | ¿Cuánto vive?                          |
| --------------------- | -------------------------------- | ----------------------- | -------------------------------------- |
| Variable de instancia | Dentro de la clase               | A cada objeto           | Mientras el objeto exista              |
| Variable de clase     | Dentro de la clase, con `static` | A la clase (compartida) | Durante toda la ejecución del programa |
| Variable local        | Dentro de un método o bloque     | Al método/bloque        | Solo mientras se ejecuta el bloque     |
| Parámetro             | En la declaración del método     | Al método               | Solo mientras se ejecuta el método     |

---

# 🔧 Tipos de Métodos en Java

En Java, los métodos son bloques de código que realizan tareas. Según sus características, se clasifican en:

## Métodos de Instancia

- No usan `static`.
- Se llaman desde un **objeto**.
- Pueden acceder a variables de instancia.

```java
public class Persona {
    void saludar() {
        System.out.println("Hola");
    }
}
````
## Métodos Estáticos

- Usan la palabra clave `static`.
- Se llaman desde la **clase**.
- No pueden acceder directamente a variables de instancia.

```java
public class Util {
    static void imprimir() {
        System.out.println("Soy estático");
    }
}
```
## Métodos con Retorno

- Devuelven un valor con `return`.
- Especifican un tipo de retorno, ej: `int`, `String`.

```java
int sumar(int a, int b) {
    return a + b;
}
```
## Métodos con Parámetros

- Reciben datos para procesar.
- Pueden tener varios parámetros.

```java
void saludar(String nombre) {
    System.out.println("Hola " + nombre);
}
```
## Métodos Privados

- Declarados con `private`.
- Solo accesibles dentro de la misma clase.

```java
private void metodoInterno() {
    // Solo uso interno
}
```
## Otros tipos importantes

| Tipo               | Descripción                                  |
|--------------------|----------------------------------------------|
| Constructor        | Método especial para crear objetos           |
| Método abstracto   | Declarado sin cuerpo en clases abstractas    |
| Método sobrecargado| Mismo nombre, diferente lista de parámetros  |
| Método sobrescrito | Redefinido en clase hija usando `@Override` |

## Resumen rápido

| Tipo               | `static` | Se llama desde | Retorna valor? |
|--------------------|----------|----------------|----------------|
| Instancia          | No       | Objeto         | Opcional       |
| Estático           | Sí       | Clase          | Opcional       |
| Con retorno        | Puede    | Objeto/Clase   | Sí             |
| Sin retorno (void) | Puede    | Objeto/Clase   | No             |
| Con parámetros     | Puede    | Objeto/Clase   | Opcional       |
| Privado            | Puede    | Solo clase     | Opcional       |

# 📘 ¿Java pasa por valor o por referencia?

## ✅ Resumen corto
Java **siempre pasa por valor**, incluso cuando se trata de objetos.

> 🔹 Si pasas un tipo primitivo → se pasa **una copia del valor**.  
> 🔹 Si pasas un objeto → se pasa **una copia de la referencia al objeto**.

Esto significa que **puedes modificar el contenido del objeto**, pero **no puedes cambiar la referencia original** dentro del método.

---

## 📦 Analogía: Caja y etiqueta

- Imagina un objeto como una **caja**.
- Una variable es como una **etiqueta** pegada a esa caja.
- En Java, cuando pasas un objeto a un método, lo que se copia es **la etiqueta**, no la caja.

Por eso:
- Puedes cambiar lo que hay dentro de la caja (modificar el objeto).
- Pero si cambias la etiqueta (la referencia), solo afecta a la copia local, no a la original.

---

## 🔍 Tabla comparativa

| Tipo de dato    | ¿Qué se pasa al método?         | ¿Se puede modificar el valor original? |
|-----------------|----------------------------------|-----------------------------------------|
| Primitivo       | Copia del valor                 | ❌ No                                   |
| Objeto          | Copia de la referencia           | ✅ El contenido sí, la referencia no     |

---

## 🧪 Ejemplo en Java

```java
class Persona {
    String nombre;
}

public class Test {
    public static void main(String[] args) {
        Persona p = new Persona();
        p.nombre = "Ana";

        cambiarNombre(p);
        System.out.println(p.nombre); // Imprime "Luis"

        reasignarReferencia(p);
        System.out.println(p.nombre); // Imprime "Luis", no "Carlos"
    }

    static void cambiarNombre(Persona persona) {
        persona.nombre = "Luis"; // ✅ Modifica el contenido del objeto
    }

    static void reasignarReferencia(Persona persona) {
        persona = new Persona();    // ❌ Solo cambia la copia de la referencia
        persona.nombre = "Carlos";
    }
}
26/09/2025

# 🧠 Resumen: Métodos, Clases Abstractas e Interfaces en Java

---

## 📌 1. Clases Abstractas

- Son clases que **no se pueden instanciar**.
- Se utilizan como **base para otras clases**.
- Pueden tener:
  - Métodos abstractos (sin implementación).
  - Métodos con implementación (comunes para las subclases).
  - Atributos, constructores y bloques estáticos.

### 🛠️ Sintaxis:

```java
abstract class Animal {
    abstract void hacerSonido(); // método abstracto

    void dormir() {
        System.out.println("Zzz...");
    }
}
````
### ✔️ Características de las Clases Abstractas:

- Puede tener **atributos y métodos concretos**.
- Una subclase debe **implementar los métodos abstractos** o también ser abstracta.

---

## 📌 2. Métodos Abstractos

- Se definen **sin cuerpo** (solo la firma).
- Obligan a las subclases a proporcionar una implementación.
- Solo pueden existir dentro de una **clase abstracta**.

```java
abstract void hacerSonido(); // no tiene cuerpo
````

### ✔️ Características de los Métodos Abstractos

- Definen un **contrato** que deben cumplir las subclases.
- No pueden tener implementación en la clase abstracta que los define.

---

## 📌 3. Interfaces

- Representan un **contrato** que una clase se compromete a cumplir.
- Se definen con la palabra clave `interface`.
- Variables estaticas y finales sin tenes que especificarlas.

### 🛠️ Sintaxis

```java
interface Volador {
    void volar();
}
````
### ✔️ Características

- Todos los métodos son **públicos y abstractos por defecto** (en versiones anteriores a Java 8).
- Desde **Java 8**, pueden incluir:
  - Métodos `default` (con implementación).
  - Métodos `static`.
- Una clase puede **implementar múltiples interfaces** (herencia múltiple).

---

### 🧪 Ejemplo

```java
class Pajaro implements Volador {
    public void volar() {
        System.out.println("El pájaro vuela");
    }
}
````

### 🆚 Diferencias Principales

| Característica             | Clase Abstracta                      | Interface                                   |
|----------------------------|--------------------------------------|---------------------------------------------|
| Instanciación              | ❌ No                                 | ❌ No                                        |
| Métodos con implementación | ✅ Sí                                 | ✅ Desde Java 8 (`default`, `static`)        |
| Atributos                  | ✅ Sí                                 | 🔒 Solo constantes (`public static final`)   |
| Herencia múltiple          | ❌ No                                 | ✅ Sí                                        |
| Uso                        | Base con lógica parcial              | Contrato a seguir                           |




# ¿Para qué se usan las Interfaces en Java?

Las **interfaces en Java** se usan principalmente para:

## 1. Definir contratos o comportamientos comunes
- Una interfaz especifica un conjunto de métodos que una clase debe implementar.
- Permite asegurar que diferentes clases cumplan con un mismo contrato, sin importar cómo lo hagan internamente.

## 2. Facilitar la herencia múltiple de tipos
- Java no permite que una clase herede de más de una clase, pero **sí puede implementar múltiples interfaces**.
- Esto permite que una clase comparta comportamientos de distintas fuentes.

## 3. Desacoplar el código
- Al programar contra interfaces en lugar de clases concretas, el código es más flexible y fácil de mantener.
- Cambiar la implementación sin afectar al resto del sistema es más sencillo.

## 4. Soportar la programación orientada a interfaces (polimorfismo)
- Puedes tratar objetos de distintas clases que implementan la misma interfaz de manera uniforme.
- Por ejemplo, si varias clases implementan la interfaz `Imprimible`, puedes pasar cualquiera a un método que reciba un `Imprimible`.

---

## Ejemplo sencillo:

```java
interface Volador {
    void volar();
}

class Pajaro implements Volador {
    public void volar() {
        System.out.println("El pájaro vuela");
    }
}

class Avion implements Volador {
    public void volar() {
        System.out.println("El avión vuela");
    }
}

public class Test {
    public static void hacerVolaraTodos(Volador v) {
        v.volar();
    }

    public static void main(String[] args) {
        Pajaro p = new Pajaro();
        Avion a = new Avion();

        hacerVolaraTodos(p);
        hacerVolaraTodos(a);
    }
}
````

# Modificadores de Acceso en Java (por nivel de restricción)

Ordenados desde el más restrictivo al menos restrictivo:

1. **private**
   - Accesible **solo dentro de la misma clase**.
   - No es visible para subclases ni clases del mismo paquete.
   
2. **(default)** _(sin modificador, también llamado package-private)_
   - Accesible **dentro del mismo paquete**, pero **no desde otras clases fuera del paquete**, incluso si son subclases.
   - Si no se especifica ningún modificador, este es el nivel de acceso por defecto.

3. **protected**
   - Accesible **dentro del mismo paquete** y también **por subclases**, incluso si están en otros paquetes.

4. **public**
   - Accesible **desde cualquier parte**, sin importar el paquete o clase.

## Tabla Resumen

| Modificador  | Misma clase | Mismo paquete | Subclases (otro paquete) | Cualquier clase |
|--------------|-------------|---------------|---------------------------|-----------------|
| `private`    | ✅          | ❌            | ❌                        | ❌              |
| _(default)_  | ✅          | ✅            | ❌                        | ❌              |
| `protected`  | ✅          | ✅            | ✅                        | ❌              |
| `public`     | ✅          | ✅            | ✅                        | ✅              |
