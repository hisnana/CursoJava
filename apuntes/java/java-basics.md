IDE : INTEGRATED DEVELOPMENT ENVIROMENT
  ejemplos de IDEs: eclipse,visual studio code, net beans, intellij

# Distribuciones Java

---

## JSE (Java Standard Edition)

- **Descripción:** Plataforma básica de Java para desarrollar aplicaciones de escritorio y consola.  
- **Uso:** Aplicaciones estándar en computadoras personales y servidores simples.  
- **Incluye:** Librerías básicas, JVM, API para GUI, manejo de datos, redes, etc.

---

## JEE (Java Enterprise Edition) — ahora Jakarta EE

- **Descripción:** Extensión de JSE para aplicaciones empresariales y web a gran escala.  
- **Uso:** Desarrollo de aplicaciones distribuidas, web, servicios REST/SOAP, aplicaciones empresariales.  
- **Incluye:** Servlets, JSP, EJB, JPA, JMS, seguridad, transacciones, entre otros.

---

## JME (Java Micro Edition)

- **Descripción:** Versión reducida de Java para dispositivos con recursos limitados.  
- **Uso:** Aplicaciones para dispositivos embebidos, móviles antiguos, IoT, sistemas con poca memoria.  
- **Incluye:** APIs específicas para hardware limitado y interfaces reducidas.

---

> 📌 *Cada edición está orientada a diferentes tipos de dispositivos y necesidades de desarrollo.*

# JRE, JDK y JVM

---

## JVM (Java Virtual Machine)

- **Qué es:** Máquina virtual que ejecuta el código Java compilado (bytecode).  
- **Función:** Permite que las aplicaciones Java sean independientes de la plataforma (escribe una vez, corre en cualquier lugar).  
- **Importancia:** Traduce el bytecode a instrucciones específicas del sistema operativo y hardware.

---

## JRE (Java Runtime Environment)

- **Qué es:** Entorno de ejecución que incluye la JVM y las librerías necesarias para ejecutar aplicaciones Java.  
- **Función:** Permite ejecutar programas Java, pero no desarrollarlos.  
- **Incluye:** JVM + librerías estándar + archivos de configuración.

---

## JDK (Java Development Kit)

- **Qué es:** Kit completo para desarrolladores Java.  
- **Función:** Permite crear, compilar y ejecutar aplicaciones Java.  
- **Incluye:** JRE + herramientas de desarrollo (compilador `javac`, debugger, etc.).

---

> 📌 *En resumen:*  
> - **JVM:** Ejecuta el código Java.  
> - **JRE:** Proporciona el entorno para ejecutar Java.  
> - **JDK:** Contiene todo lo necesario para desarrollar y ejecutar Java.

# Características de Java

---

## 1. **Orientado a objetos**  
Java está basado en el paradigma de programación orientada a objetos, facilitando la modularidad, reutilización y mantenimiento del código.

## 2. **Independiente de plataforma (Portable)**  
Gracias a la JVM (Java Virtual Machine), el código Java compilado (bytecode) puede ejecutarse en cualquier sistema operativo sin modificaciones. Esto hace que Java sea altamente **portable** y se pueda usar en diferentes entornos.

## 3. **Seguro**  
Java incluye múltiples características de seguridad, como el manejo automático de memoria, verificación de bytecode y un modelo de seguridad robusto para proteger aplicaciones.

## 4. **Multihilo (Multithreading)**  
Permite la ejecución concurrente de múltiples hilos dentro de un programa, mejorando el rendimiento y la capacidad de respuesta.

## 5. **Automática gestión de memoria**  
Java utiliza un recolector de basura (Garbage Collector) para administrar automáticamente la memoria, liberando recursos que ya no se utilizan.

## 6. **Robusto y confiable**  
Java maneja excepciones y errores de forma efectiva, reduciendo fallos inesperados y facilitando la creación de aplicaciones estables.

## 7. **Lenguaje interpretado y compilado**  
El código fuente Java se compila a bytecode, que luego es interpretado o compilado justo a tiempo (JIT) por la JVM.

## 8. **Fuertemente tipado**  
Java es un lenguaje **fuertemente tipado**, lo que significa que cada variable y expresión tiene un tipo claro y definido, y las conversiones entre tipos están estrictamente controladas para evitar errores.

## 9. **Amplia biblioteca estándar**  
Java proporciona una extensa biblioteca estándar con APIs para manejo de colecciones, redes, interfaces gráficas, bases de datos, y más.

## 10. **Distribuido**  
Java facilita el desarrollo de aplicaciones distribuidas con soporte integrado para comunicación a través de redes (RMI, sockets).

---

> 📌 *Estas características hacen de Java un lenguaje versátil y ampliamente usado en desarrollo de aplicaciones empresariales, móviles y web.*


PROYECTO > PAQUETES > CLASES > METODOS

UNA LIBRERIA CONTIENE .JAR > UN .JAR CONTIENE .CLASS

VARIABLE: asocia un identificado a una direccionde memoria. Tiene que tener un tipo de dato.

CONDICIONALES
| Estructura     | Uso principal                               | ¿Permite múltiples opciones? | ¿Es concisa? |
| -------------- | ------------------------------------------- | ---------------------------- | ------------ |
| `if`           | Una sola condición                          | No                           | Sí           |
| `if - else`    | Una condición + alternativa                 | No                           | Sí           |
| `if - else if` | Evaluar varias condiciones                  | Sí                           | No           |
| `switch`       | Comparar un valor con varias constantes     | Sí                           | Sí           |
| `? :`          | Condicional simple y directo (asignaciones) | No                           | Muy sí       |


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

