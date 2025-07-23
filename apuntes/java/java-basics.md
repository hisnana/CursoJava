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


JAR - JAVA ARCHIVE -> contiene los class

# ¿Qué es `java.lang` en Java?

---

## Definición

- `java.lang` es un **paquete fundamental** del lenguaje Java.
- Contiene las **clases básicas esenciales** que forman la base del lenguaje.
- Está **importado automáticamente** en todos los programas Java, por lo que no es necesario importarlo manualmente.

---

## Clases más importantes en `java.lang`

| Clase           | Descripción                                      |
|-----------------|-------------------------------------------------|
| `Object`        | Clase raíz de todas las clases en Java           |
| `String`        | Manejo de cadenas de texto                        |
| `Math`          | Funciones matemáticas estáticas                   |
| `System`        | Variables y métodos relacionados con el sistema  |
| `Thread`        | Para manejar hilos de ejecución                    |
| `Integer`, `Double`, `Boolean`, ... | Clases envolventes (wrapper) para tipos primitivos |
| `Exception` y subclases | Manejo de excepciones y errores                 |

---

## Importancia

- Proporciona las **funciones básicas** para manipular datos, control del programa, y operaciones comunes.
- Todas las clases en Java heredan de `java.lang.Object`.
- Permite trabajar con tipos primitivos a través de sus clases envolventes (`Integer`, `Double`, etc.).

---


