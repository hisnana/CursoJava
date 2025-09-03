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
