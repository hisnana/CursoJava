## Docker instalación en la maquina virtual de Linux

✅ Paso 4: Instalar Docker (usando el script oficial, ya que la versión noble aún no está en los repos de Docker)

> [!note] > Es el paso 4 porque es la continuación de las instrucciones de configuración de la maquina virtual

### 4.1 Instalar dependencias necesarias:

sudo apt install ca-certificates curl gnupg lsb-release -y

### 4.2 Descargar y ejecutar el script de instalación oficial de Docker:

curl -fsSL [https://get.docker.com](https://get.docker.com) -o get-docker.sh sudo sh get-docker.sh

### 4.3 Verifica que Docker se instaló correctamente:

docker --version

### 4.4 (Opcional) Usar Docker sin sudo:

sudo usermod -aG docker $**USER**

⚠️ Después de esto, debes cerrar sesión y volver a iniciarla o reiniciar tu máquina para que surta efecto.

---

## [https://hub.docker.com/](https://hub.docker.com/)

Repositorio de imagenes

Hemos filtrado por Docker Official image

## Docker en maquina virtual

Hemos ejecutado los siguientes pasos sudo docker run hello-world

es típicamente el primer comando que se ejecuta para verificar que Docker esté instalado y funcionando correctamente en tu sistema. sudo docker image ls

(o también puedes usar `sudo docker images`) se usa para listar todas las imágenes Docker que tienes descargadas localmente en tu sistema. **REPOSITORY**    **TAG**       **IMAGE** ID       **CREATED**       **SIZE** hello-world   latest    1b44b5a3e06a   6 weeks ago   10.1kB

sudo docker container ls -a

(o también `sudo docker ps -a`) se usa para listar todos los contenedores Docker en tu sistema, incluyendo los que están en ejecución y los que están detenidos. **CONTAINER** ID   **IMAGE**         **COMMAND**    **CREATED**         **STATUS**                     **PORTS**     **NAMES** efd326c90e6f   hello-world   */hello*   4 minutes ago   Exited (0) 4 minutes ago             mystifying_swanson

sudo docker pull ubuntu  se utiliza para descargar la imagen oficial de Ubuntu desde Docker Hub (el repositorio público de imágenes Docker). # Contenedor 1:

## Crear contenedor Docker de Ubuntu con configuración personalizada

Puedes usar el siguiente comando para crear y ejecutar un contenedor de **Ubuntu 22.04** con configuración personalizada:

```bash docker run -d --name ubuntu-dev --hostname devbox -e **MODE**=dev -e TZ=Europe/Madrid -v ./devdata:/data --cpus=*1.0* --memory=*1g* ubuntu:22.04 ### 🔍 Desglose de parámetros

| Opción                   | Descripción                                                        |
|--------------------------|--------------------------------------------------------------------|
| `-d`                     | Ejecuta el contenedor en segundo plano (*detached*)               |
| `--name ubuntu-dev`      | Nombre del contenedor                                              |
| `--hostname devbox`      | Nombre del host dentro del contenedor                              |
| `-e MODE=dev`            | Variable de entorno personalizada (`MODE`)                         |
| `-e TZ=Europe/Madrid`    | Zona horaria configurada a Madrid                                  |
| `-v ./devdata:/data`     | Monta el directorio local `./devdata` en `/data` dentro del contenedor |
| `--cpus=*1.0*`           | Limita el contenedor a 1 CPU                                       |
| `--memory=*1g*`          | Limita el contenedor a 1 GB de RAM                                 |
| `ubuntu:22.04`           | Imagen base utilizada                                              |

# Contenedor 2: 

docker run -d --name ubuntu-test --hostname testbox -e **MODE**=test -v ./testdata:/data --cpus=*0.5* --memory=*512m* ubuntu:22.04 ### 🔍 Desglose de parámetros

| Opción                   | Descripción                                                        |
|--------------------------|--------------------------------------------------------------------|
| `-d`                     | Ejecuta el contenedor en segundo plano (*detached*)               |
| `--name ubuntu-test`     | Nombre del contenedor                                              |
| `--hostname testbox`     | Nombre del host dentro del contenedor                              |
| `-e MODE=test`           | Variable de entorno personalizada (`MODE`)                         |
| `-v ./testdata:/data`    | Monta el directorio local `./testdata` en `/data` dentro del contenedor |
| `--cpus=*0.5*`           | Limita el contenedor a medio CPU                                   |
| `--memory=*512m*`        | Limita el contenedor a 512 MB de RAM                               |
| `ubuntu:22.04`           | Imagen base utilizada                                              |

Hemos creado estos 2 contenedores de Ubuntu. **CONTAINER** ID   **IMAGE**          **COMMAND**       **CREATED**          **STATUS**                      **PORTS**     **NAMES** 2da272b0fdf4   ubuntu:22.04   */bin/bash*   8 seconds ago    Exited (0) 7 seconds ago              ubuntu-test 0aabebca4817   ubuntu:22.04   */bin/bash*   4 minutes ago    Exited (0) 4 minutes ago              ubuntu-dev efd326c90e6f   hello-world    */hello*      24 minutes ago   Exited (0) 24 minutes ago             mystifying_swanson

El profesor ha cambiado los comandos y nos ha pedido que ejecutemos este (sin destruir aun los anteriores 2 containers que hemos creado). # Contenedor 1:

docker run -d --name ubuntu-dev2 --hostname devbox -e **MODE**=dev -e TZ=Europe/Madrid -v ./devdata:/data --cpus=*1.0* --memory=*1g* ubuntu:22.04 sleep infinity

- sleep infinity es un comando que hace que el proceso duerma para siempre.
- Esto mantiene el contenedor en ejecución indefinidamente mientras no lo detengas.
- Así el contenedor no se cierra automáticamente después de iniciarse.
- Esto es útil para:
    - Contenedores en los que quieres entrar (con `docker exec`) y hacer tareas manuales.
    - Contenedores que deben estar “vivos” esperando que hagas algo dentro.
    - Desarrollo o testing interactivo.
    -

sudo docker container ls

Que muestra solo los containers running **CONTAINER** ID   **IMAGE**          **COMMAND**            **CREATED**          **STATUS**          **PORTS**     **NAMES** 85e4daae6a28   ubuntu:22.04   *sleep infinity*   15 seconds ago   Up 14 seconds             ubuntu-dev2

docker exec -it ubuntu-dev2 bash

- `docker exec`: Ejecuta un comando dentro de un contenedor ya en funcionamiento.- `-it`: Abre una sesión interactiva con terminal (te permite usar el bash como si fuera una shell normal).
- `ubuntu-dev2`: Nombre del contenedor donde quieres entrar.
- `bash`: El comando que quieres ejecutar dentro del contenedor (una shell Bash).

Resultado:

Entras interactivamente al contenedor ubuntu-dev2 y puedes ejecutar comandos dentro de su sistema operativo Ubuntu. root@devbox:/#

Para salir de un contenedor: exit

Para eliminar uno hay que tenerlo parado (aqui eliminamos el contenedor ubuntu-dev) docker container rm ubuntu-dev

(aqui eliminamos el contenedor ubuntu-test) sudo docker container rm ubuntu-test

## Docker Oracle XE

La imagen que usaremos es una de un usuario, pero teoricamente podriamos configurarlo con la original docker run -d -p **1521**:**1521** --name oracle-xe -e ORACLE_PASSWORD=password -v oracle-volume:/opt/oracle/oradata gvenzl/oracle-xe

### 🔍 Desglose de parámetros

| Opción                        | Descripción                                                                                          |
|------------------------------|----------------------------------------------------------------------------------------------------|
| `docker run`                 | Comando para ejecutar un contenedor nuevo                                                          |
| `-d`                         | Ejecuta el contenedor en segundo plano (*detached*)                                               |
| `-p 1521:1521`               | Mapea el puerto 1521 del contenedor al host, que es el puerto por defecto de Oracle                 |
| `--name oracle-xe`           | Asigna el nombre `oracle-xe` al contenedor                                                         |
| `-e ORACLE_PASSWORD=password`| Establece la contraseña del usuario admin/oracle (reemplaza `*password*` por una contraseña segura en producción) |
| `-v oracle-volume:/opt/oracle/oradata` | Monta un volumen persistente para guardar los datos de Oracle (en `/opt/oracle/oradata`)         |
| `gvenzl/oracle-xe`           | Imagen utilizada, que contiene Oracle XE                                                           |

instalada ubuntu@Ubuntu-Server-24:~$ sudo docker container ls **CONTAINER** ID   **IMAGE**              **COMMAND**                  **CREATED**          **STATUS**          **PORTS**                                         **NAMES** 3fd74949a770   gvenzl/oracle-xe   *container-entrypoin…*   6 minutes ago    Up 6 minutes    0.0.0.0:**1521**->**1521**/tcp, [::]:**1521**->**1521**/tcp   oracle-xe 85e4daae6a28   ubuntu:22.04       *sleep infinity*         28 minutes ago   Up 28 minutes

comprobado que jecuta docker exec -it oracle-xe bash

accedemos sqlplus system/password@localhost:**1521**/**XEPDB1**

es para conectarte a una base de datos Oracle usando **SQL***Plus, el cliente de línea de comandos de Oracle. **SELECT** * **FROM** DBA_ROLE_PRIVS **WHERE** **GRANTEE** = 'ADMIN_DB';

Te devolverá todas las filas que muestran qué roles han sido otorgados a 'ADMIN_DB'. no rows selected

significa que el usuario o rol `ADMIN_DB` no tiene roles asignados (no se le ha otorgado ningún rol). **CREATE** **USER** getafe **IDENTIFIED** BY password;

- Crea un nuevo usuario llamado getafe en la base de datos Oracle.
- Le asigna la contraseña password para que pueda iniciar sesión.

Solo crear el usuario no le da permisos para hacer nada. Por ejemplo, no podrá conectarse ni crear objetos hasta que le otorgues privilegios. **GRANT** **DBA** TO getafe;

hace que el usuario getafe obtenga el rol **DBA**, que es el rol con privilegios administrativos máximos en Oracle.

sudo docker container stop oracle-xe

Detiene el contenedor llamado oracle-xe. Lo apaga de forma ordenada, pero no lo elimina; queda en estado detenido. sudo docker container start oracle-xe

Inicia el contenedor oracle-xe si estaba detenido, volviéndolo a poner en ejecución con su configuración previa. ## Oracle SQL Developer

Hemos instalado lo siguiente en Windows:

sqldeveloper-24.3.1.**347**.**1826**-x64

- Es una herramienta gratuita de Oracle para trabajar con bases de datos Oracle.
- Permite:
    - Conectarte a bases de datos Oracle.
    - Ejecutar consultas **SQL** y scripts.
    - Administrar usuarios, tablas, índices, etc.
    - Visualizar datos y diseñar esquemas.
- Tiene interfaz gráfica, muy usada para desarrollo y administración.

- Hemos hecho click en el + de Nueva Conexión
- Nombre: oracle_xe
- Tipo de base de datos: Oracle
- Tipo de autenticación: Por defecto
- Usuario: getafe
- Contraseña: password
> - Tal y como creamos con **CREATE** **USER** getafe **IDENTIFIED** BY password;
- Detalles:
- Nombre de puerto: localhost
- Puerto: **1521**
- **SID** : xe
- Nombre del servicio: **XEPDB1**
- [note] Seleccionar Nombre del servicio en lugar de **SID**
En la maquina Virtual mapeamos (Selección Maquina Virtual > Configuración > Red > Reenvio de Puertos ):
Nombre: oraclexe
Tipo: **TPC**
Puerto Anfitrión: **1521**
Puerto Invitado: **1521**