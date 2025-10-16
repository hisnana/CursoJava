# 🐎 Carrera de Caballos — Chuleta de examen (Java, Maven, Eclipse + Git)

> **Objetivo:** juego de consola sencillo que simula una carrera de caballos. Cubre **arrays**, **condicionales**, **bucles**, **interfaces**, **herencia** y uso de **ArrayList** y **HashSet**. Proyecto **Maven** listo para ejecutar con `mvn exec:java` y subir desde **Eclipse** al repo **Examen-Octubre**.

---

## 0) Convenciones de nombres (pon lo que pida el profe)

> Si te pide algo como **"ana.arroyo"**, adapta **groupId**, **artifactId** y **paquete base** así:

- **groupId** (dominio inverso o similar): `es.centro.ana.arroyo` *(o)* `org.cursojava.ana.arroyo`
- **artifactId** (nombre del proyecto en minúsculas y con guiones): `examen-octubre` *(o)* `carrera-caballos`
- **Versión**: `1.0.0`
- **Paquete base Java** (coincide con el groupId + módulo): `es.centro.ana.arroyo.carrera`
- **Nombre del proyecto en Eclipse**: puede ser `Examen-Octubre` (mejor sin espacios)
- **Repo GitHub** ya creado**:** `Examen-Octubre` (URL sin espacios, p.ej. `https://github.com/<tu_usuario>/Examen-Octubre`)

**Ejemplo de `pom.xml` con tu nombre**

```xml
<groupId>es.centro.ana.arroyo</groupId>
<artifactId>examen-octubre</artifactId>
<version>1.0.0</version>
...
<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>exec-maven-plugin</artifactId>
      <version>3.2.0</version>
      <configuration>
        <mainClass>es.centro.ana.arroyo.carrera.App</mainClass>
      </configuration>
    </plugin>
  </plugins>
</build>
```

**Si cambias el paquete base**:
1. En Eclipse, renombra el paquete `org.cursojava.carrera` → `Refactor → Rename` → `es.centro.ana.arroyo.carrera`.
2. Eclipse actualizará los `package ...` en las clases.
3. Cambia también `mainClass` en el `pom.xml` para que coincida.

> **Tips rápidos**: evita **espacios** en artifactId y **puntos** en artifactId (mejor guiones). El **groupId** sí lleva puntos. Si el profe te da un nombre concreto, úsalo tal cual en **groupId** o en el **nombre del proyecto**.

---

## 1) Estructura del proyecto (Maven)

```
carrera-caballos/
├─ pom.xml
└─ src/
   └─ main/
      └─ java/
         └─ org/cursojava/carrera/
            ├─ App.java
            ├─ juego/Carrera.java
            ├─ modelo/Animal.java
            ├─ modelo/Caballo.java
            └─ interfaces/Corredor.java
```

### `pom.xml` (Java 17 + exec plugin)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.cursojava</groupId>
  <artifactId>carrera-caballos</artifactId>
  <version>1.0.0</version>
  <name>Carrera de Caballos</name>

  <properties>
    <maven.compiler.release>17</maven.compiler.release>
  </properties>

  <build>
    <plugins>
      <!-- Compilar con Java 17 -->
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
          <release>${maven.compiler.release}</release>
        </configuration>
      </plugin>

      <!-- Ejecutar con: mvn -q exec:java -->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.2.0</version>
        <configuration>
          <mainClass>org.cursojava.carrera.App</mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

---

## 2) Código fuente

### `interfaces/Corredor.java` — (Interface)

```java
package org.cursojava.carrera.interfaces;

public interface Corredor {
    void prepararParaCarrera();
    void correrTurno(java.util.Random rng);
    int getPosicion();
    String getNombre();
}
```

### `modelo/Animal.java` — (Herencia base)

```java
package org.cursojava.carrera.modelo;

public abstract class Animal {
    protected String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

### `modelo/Caballo.java` — (Hereda de Animal, implementa Corredor)

```java
package org.cursojava.carrera.modelo;

import java.util.Random;
import org.cursojava.carrera.interfaces.Corredor;

/**
 * Caballo con velocidad base y posición acumulada.
 * - Usa condicionales para bonificar los turnos.
 * - Usa arrays para tablas simples (p.ej. bonificaciones aleatorias).
 */
public class Caballo extends Animal implements Corredor {

    private int posicion = 0;
    private final int velocidadBase;

    // Array de bonificaciones posibles por turno (demostración de arrays)
    private static final int[] BONOS = {0, 0, 1, 1, 2};

    public Caballo(String nombre, int velocidadBase) {
        super(nombre);
        this.velocidadBase = velocidadBase; // 1..3 recomendado
    }

    @Override
    public void prepararParaCarrera() {
        this.posicion = 0;
    }

    @Override
    public void correrTurno(Random rng) {
        int bono = BONOS[rng.nextInt(BONOS.length)]; // array + rng
        int avance = velocidadBase + rng.nextInt(3) + bono; // 0..2 aleatorio + bono
        if (avance < 0) avance = 0; // condicional de seguridad
        this.posicion += avance;
    }

    @Override
    public int getPosicion() {
        return posicion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (pos=" + posicion + ", base=" + velocidadBase + ")";
    }
}
```

### `juego/Carrera.java` — (Lógica de la carrera)

```java
package org.cursojava.carrera.juego;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.cursojava.carrera.interfaces.Corredor;
import org.cursojava.carrera.modelo.Caballo;

/**
 * La carrera gestiona participantes (ArrayList), asegura nombres únicos (HashSet),
 * y ejecuta el bucle principal con condicionales para comprobar el ganador.
 */
public class Carrera {

    private final int longitudPista;
    private final ArrayList<Caballo> participantes = new ArrayList<>();
    private final Random rng;

    public Carrera(int longitudPista, long semilla) {
        this.longitudPista = longitudPista;
        this.rng = new Random(semilla);
    }

    public void addCaballo(Caballo c) {
        participantes.add(c);
    }

    /**
     * Crea caballos garantizando nombres únicos con HashSet.
     */
    public static List<Caballo> crearParticipantesUnicos(String[] nombres, int[] velocidades) {
        ArrayList<Caballo> lista = new ArrayList<>();
        HashSet<String> usados = new HashSet<>();

        // bucle sobre arrays
        for (int i = 0; i < nombres.length && i < velocidades.length; i++) {
            String nombre = nombres[i];
            if (!usados.contains(nombre)) {       // condicional + HashSet
                usados.add(nombre);
                int velBase = Math.max(1, Math.min(3, velocidades[i])); // clamp 1..3
                lista.add(new Caballo(nombre, velBase));
            }
        }
        return lista;
    }

    public void preparar() {
        for (Caballo c : participantes) { // bucle for-each
            c.prepararParaCarrera();
        }
        System.out.println("Participantes:");
        for (Caballo c : participantes) {
            System.out.println(" - " + c);
        }
        System.out.println();
        System.out.println("¡Comienza la carrera! Meta en " + longitudPista + " metros.");
    }

    /**
     * Ejecuta turnos hasta que al menos un caballo cruce la meta.
     * Devuelve lista de ganadores (empates posibles).
     */
    public List<Caballo> correr() {
        preparar();
        ArrayList<Caballo> ganadores = new ArrayList<>();
        boolean hayGanador = false;

        int turno = 1;
        while (!hayGanador) { // bucle while
            System.out.println("\nTurno " + turno + ":");
            for (Caballo c : participantes) {
                c.correrTurno(rng);
                imprimirPista(c);
                if (c.getPosicion() >= longitudPista) {
                    hayGanador = true;
                }
            }

            // Comprobar ganadores del turno (pos >= meta)
            if (hayGanador) {
                int mejorPos = 0;
                for (Caballo c : participantes) {
                    if (c.getPosicion() >= longitudPista) {
                        if (c.getPosicion() > mejorPos) {
                            mejorPos = c.getPosicion();
                        }
                    }
                }
                for (Caballo c : participantes) {
                    if (c.getPosicion() >= longitudPista && c.getPosicion() == mejorPos) {
                        ganadores.add(c);
                    }
                }
            }

            turno++;
            // pequeña pausa visual opcional (comentar en examen si molesta)
            // try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }

        return ganadores;
    }

    private void imprimirPista(Corredor c) {
        int pos = c.getPosicion();
        if (pos < 0) pos = 0;
        if (pos > longitudPista) pos = longitudPista;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-12s |", c.getNombre()));

        // dibuja la pista con puntos y el caballo con '>'
        for (int i = 0; i < longitudPista; i++) {
            if (i == pos) sb.append('>');
            else sb.append('.');
        }
        sb.append("|");
        System.out.println(sb.toString());
    }
}
```

### `App.java` — (Main)

```java
package org.cursojava.carrera;

import java.util.List;

import org.cursojava.carrera.juego.Carrera;
import org.cursojava.carrera.modelo.Caballo;

public class App {
    public static void main(String[] args) {
        // Datos de entrada (arrays) para demostrar el requisito
        String[] nombres = {"Rocinante", "Pegaso", "Furia", "Trueno"};
        int[] velocidadesBase = {2, 3, 2, 1}; // 1..3 recomendado

        // Crear carrera
        int meta = 40;            // longitud de pista
        long semilla = 42L;       // fija para reproducibilidad
        Carrera carrera = new Carrera(meta, semilla);

        // Instanciar caballos con nombres únicos (HashSet) y añadirlos (ArrayList interno)
        List<Caballo> lista = Carrera.crearParticipantesUnicos(nombres, velocidadesBase);
        for (Caballo c : lista) {
            carrera.addCaballo(c);
        }

        // Correr y mostrar ganadores
        List<Caballo> ganadores = carrera.correr();
        if (ganadores.size() == 1) {
            System.out.println("\nGanador: " + ganadores.get(0).getNombre() + " 🏆");
        } else {
            System.out.print("\nEmpate entre: ");
            for (int i = 0; i < ganadores.size(); i++) {
                System.out.print(ganadores.get(i).getNombre());
                if (i < ganadores.size() - 1) System.out.print(", ");
            }
            System.out.println(" 🏁");
        }
    }
}
```

---

## 3) Cómo ejecutar

```bash
# En la raíz del proyecto
mvn -q clean compile
mvn -q exec:java
```

Salida de ejemplo (recortada):

```
Participantes:
 - Rocinante (pos=0, base=2)
 - Pegaso (pos=0, base=3)
 - Furia (pos=0, base=2)
 - Trueno (pos=0, base=1)

¡Comienza la carrera! Meta en 40 metros.

Turno 1:
Rocinante   |>.......................................|
Pegaso      |....>..................................|
Furia       |..>....................................|
Trueno      |.>.....................................|
...
Ganador: Pegaso 🏆
```

---

## 4) Checklist de requisitos (marcado dentro del código)

- ✅ **Arrays**: `int[] BONOS`, `String[] nombres`, `int[] velocidadesBase`.
- ✅ **Condicionales**: clamps, comprobación de meta, selección de ganadores, etc.
- ✅ **Bucles**: `for`, `for-each`, `while`.
- ✅ **Interfaces**: `Corredor`.
- ✅ **Herencia**: `Animal` → `Caballo`.
- ✅ **ArrayList**: lista de participantes y retorno de ganadores.
- ✅ **HashSet**: asegurar nombres únicos de caballos.

---

## 5) Crear el proyecto **Maven** en **Eclipse** (paso a paso)

> **Versión Eclipse**: cualquiera con soporte Maven (IDE for Enterprise Java / con m2e).

1. **Nuevo proyecto Maven**
   - `File → New → Maven Project`.
   - Marca **Create a simple project (skip archetype selection)** y **Use default Workspace location**.
   - `Next`.
2. **Datos del proyecto**
   - **Group Id**: `org.cursojava`
   - **Artifact Id**: `carrera-caballos`
   - **Version**: `1.0.0`
   - **Packaging**: `jar`
   - **Name**: `Carrera de Caballos`
   - **Description**: `Juego consola - examen`
   - `Finish`.
3. **Configurar Java 17 (si procede)**
   - `Project → Properties → Java Compiler` → marca **Enable project specific settings** → **Compiler compliance level: 17**.
   - `Project → Properties → Java Build Path → Libraries` → verifica **JRE System Library [JavaSE-17]**.
4. **Editar `pom.xml`**
   - Sustituye el contenido por el **POM** de este documento (con `maven-compiler-plugin` y `exec-maven-plugin`).
5. **Crear paquetes y clases**
   - `src/main/java` → botón derecho `New → Package` y crea:
     - `org.cursojava.carrera` (App)
     - `org.cursojava.carrera.juego`
     - `org.cursojava.carrera.modelo`
     - `org.cursojava.carrera.interfaces`
   - Crea las clases e interfaces copiando el código de arriba.
6. **Refrescar y compilar**
   - `Project → Clean` y `Maven → Update Project...` (Alt+F5).
7. **Ejecutar**
   - Opción A: `Run As → Java Application` y elige `org.cursojava.carrera.App`.
   - Opción B: `Run As → Maven build...` → **Goals**: `exec:java`.

---

## 6) Subir a **Git** y unir con repo existente **Examen-Octubre** (Eclipse/EGit)

> Suponemos que ya has creado en GitHub el repositorio **Examen-Octubre** (p.ej. `https://github.com/<tu_usuario>/Examen-Octubre.git`). Preferencia de rama: **master**.

### Opción 1 — Tienes el proyecto en Eclipse y quieres conectarlo al remoto

1. **Inicializa Git en el proyecto**
   - Click derecho sobre el proyecto → `Team → Share Project...` → **Git** → `Next` → **Create** para crear repo `.git` dentro del proyecto → `Finish`.
2. **Añade `.gitignore` (recomendado)**
   - Crea un archivo `.gitignore` en la raíz con el contenido de la sección siguiente.
3. **Primer commit**
   - `Team → Commit...` → selecciona todos los archivos → mensaje: `feat: juego carrera caballos` → **Commit**.
4. **Configura el remoto (origin)**
   - `Team → Remote → Push...`
   - **URI**: `https://github.com/<tu_usuario>/Examen-Octubre.git`
   - **Authentication**: usuario GitHub + **Personal Access Token** como contraseña (GitHub ya no admite password).
   - En **Refspecs**: Source ref `refs/heads/master` → Destination ref `refs/heads/master` → **Add Spec**.
   - Marca **Set upstream**.
   - **Next → Finish**.
5. **Verifica**
   - `Team → Remote → Configure Push to Upstream` debería quedar configurado.
   - En GitHub comprueba archivos en la rama **master**.

> ⚠️ Si tu remoto usa **main** como rama por defecto: puedes empujar a `main` (cambia Destination ref a `refs/heads/main`) **o** crear `master` y luego, en GitHub, `Settings → Branches` cambiar la default a `master`.

### Opción 2 — Clonar primero el repo vacío y luego importar en Eclipse

1. `File → Import... → Git → Projects from Git (with smart import)`.
2. **Clone URI**: pega `https://github.com/<tu_usuario>/Examen-Octubre.git` → `Next` → selecciona la rama **master** (o main) → `Next` → elige el directorio destino → `Finish`.
3. Eclipse detectará el **Maven** y lo importará; si está vacío, crea dentro la estructura mostrada en este README.
4. `Team → Commit...` y `Team → Push to Upstream`.

---

## 7) `.gitignore` recomendado (Maven + Eclipse/IDEA)

```gitignore
# Maven
/target/
!.mvn/wrapper/maven-wrapper.jar

# Eclipse
.classpath
.project
.settings/

# IntelliJ / VS Code
.idea/
*.iml
.vscode/

# OS
.DS_Store
Thumbs.db
```

---

## 8) Alternativa por línea de comandos (si lo necesitas)

```bash
# estando en la carpeta del proyecto
git init
git add .
git commit -m "feat: juego carrera caballos (arrays, cond, bucles, interfaz, herencia, ArrayList, HashSet)"

git branch -M master

git remote add origin https://github.com/<tu_usuario>/Examen-Octubre.git
# si el remoto está en main, sustituye master por main en el push

git push -u origin master
```

---

## 9) Ideas sencillas de mejora (si te sobra tiempo)

- 🏇 **Entrada por argumentos**: nº de caballos, meta, semilla (`args[]` → `Integer.parseInt`).
- 🎲 **Eventos**: cada X turnos, un `bono` extra o “tropezón” (`avance = Math.max(0, avance-1)`).
- 📈 **Resumen final**: ordenar por posición (sin `Comparator`: busca máximo con un bucle).
- 💾 **Persistencia mínima**: escribir el ganador en un `.txt` (`Files`, `Paths`).

---

## 10) Checklist final de examen

- [ ] Proyecto Maven compila (`mvn -q clean compile`).
- [ ] Se ejecuta por consola (`mvn -q exec:java` o `Run As → Java Application`).
- [ ] Uso de **arrays**, **condicionales**, **bucles**, **interface**, **herencia**, **ArrayList**, **HashSet** visible en el código.
- [ ] Commit inicial y **push** al repo **Examen-Octubre** en rama **master**.
- [ ] `.gitignore` aplicado, sin `/target` en el repo.

---

¡Listo! Copia/pega este README en tu repo y sigue los pasos. Mucha suerte en el examen 🧠✨

