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

