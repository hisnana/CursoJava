ECLIPSE y GIT

La perspectiva es un conjunto de vistas.

¡Perfecto! Aquí tienes una **guía paso a paso para clonar un repositorio de GitHub en Eclipse**, suponiendo que ya tienes Eclipse instalado con soporte para Git (EGit):

---

### ✅ **Requisitos previos**

* Tener **Eclipse** instalado (idealmente una versión como Eclipse IDE for Java Developers).
* Tener el plugin **EGit** (ya viene instalado en la mayoría de versiones actuales de Eclipse).
* Tener la URL del repositorio de **GitHub** que deseas clonar.

---

### 🪜 **Pasos para clonar un repositorio de GitHub en Eclipse**

---

#### 🔹 1. Copia la URL del repositorio en GitHub

* Ve al repositorio que quieres clonar (por ejemplo, `https://github.com/usuario/repositorio.git`).
* Haz clic en el botón verde **"Code"** y copia la URL HTTPS.

---

#### 🔹 2. Abre Eclipse y ve al menú de Git

* En Eclipse, ve a:

  * `File > Import...`
  * O también puedes usar el acceso rápido con `Ctrl+3` y buscar **"Git"**.

---

#### 🔹 3. Selecciona el tipo de importación

* En la ventana de importación:

  * Ve a `Git > Projects from Git` → clic en **Next**.

---

#### 🔹 4. Selecciona la opción de clonación

* Marca la opción **Clone URI** → clic en **Next**.

---

#### 🔹 5. Pega la URL del repositorio

* En el campo **URI**, pega la URL que copiaste de GitHub.
* Los demás campos (Host, Repository path) se llenan automáticamente.
* Si el repositorio es privado, deberás ingresar tu usuario y contraseña/token de GitHub.

→ Haz clic en **Next**.

---

#### 🔹 6. Selecciona la rama

* Elige la(s) rama(s) que deseas clonar (normalmente `main` o `master`).
  → Clic en **Next**.

---

#### 🔹 7. Elige la ubicación local

* Escoge una carpeta local donde se clonará el proyecto.
  → Clic en **Next**.

---

#### 🔹 8. Importa el proyecto

* Eclipse te preguntará cómo quieres importar el proyecto:

  * Si es un proyecto Java o Maven, Eclipse lo detectará automáticamente.
  * Puedes elegir:

    * **Import existing Eclipse project**
    * **Import as general project**
    * **Import as Maven project**, etc.

→ Selecciona la opción adecuada y clic en **Finish**.

---

### 🧩 ¡Listo!

Tu repositorio de GitHub ahora está clonado y abierto en Eclipse. Puedes ver el historial de Git, hacer commits, push, pull, etc., desde la perspectiva **Git** o **Java**.

---

🔄 Flujo de trabajo con Git en Eclipse + repositorio local

Vamos a dividirlo en pasos clave:
🟡 1. Clonar o crear el repositorio en Eclipse

    Si ya clonaste un repositorio desde GitHub → ya tienes una copia local en Eclipse.

    Si creas un proyecto nuevo en Eclipse, puedes inicializarlo como repositorio Git con:

        Botón derecho sobre el proyecto → Team > Share Project... → selecciona Git.

🔵 2. Hacer cambios en tu proyecto

Modifica tu código normalmente: archivos .java, archivos de configuración, etc.
🟣 3. Agregar archivos al staging area

Antes de guardar los cambios en el historial de Git (commit), debes añadir los archivos modificados:

    Botón derecho en el proyecto → Team > Add to Index
    (O desde la vista Git Staging, arrastras los archivos a la sección Staged Changes)

Esto es como decir: “estos archivos quiero incluirlos en el próximo commit”.
🔴 4. Hacer un commit

Una vez tengas los cambios preparados:

    Abre la vista Git Staging (si no la ves, ve a Window > Show View > Other > Git > Git Staging).

    Escribe un mensaje de commit en el campo superior.

    Haz clic en Commit (o Commit and Push si ya quieres subirlo a GitHub directamente).

Esto guarda tus cambios en el repositorio local.
🔁 5. Sincronizar con GitHub
🔼 Subir tus cambios (Push)

    Team > Push to Upstream

    Esto envía tus commits locales al repositorio remoto (GitHub).

🔽 Traer cambios del remoto (Pull)

    Si estás trabajando con otras personas (o desde otro ordenador), debes hacer:

        Team > Pull para traer los últimos cambios del repositorio remoto y fusionarlos con los tuyos.

🧭 Flujo típico diario:

[1] Pull  → Traes lo nuevo de GitHub
[2] Trabajas en tu código
[3] Add   → Preparas los cambios
[4] Commit → Guardas los cambios localmente
[5] Push  → Subes tus cambios a GitHub


